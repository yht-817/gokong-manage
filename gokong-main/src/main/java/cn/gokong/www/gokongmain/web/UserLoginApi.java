package cn.gokong.www.gokongmain.web;

import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.base.util.ApiTool;
import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.base.util.LatLngTools;
import cn.gokong.www.gokongmain.domain.UserAuths;
import cn.gokong.www.gokongmain.domain.UserLog;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import cn.gokong.www.gokongmain.vo.login.*;
import cn.gokong.www.gokongmain.vo.user.AcceptAnInvitationInput;
import cn.gokong.www.gokongmain.vo.user.AccountPasswordInput;
import cn.gokong.www.gokongmain.vo.user.UserInfoOutput;
import cn.gokong.www.gokongmain.vo.user.UserNoLoginOutput;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.log.StaticLog;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.security.spec.AlgorithmParameterSpec;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 说明:用户登录/注册API
 *
 * @author ikook
 * @CreateDate 2018/9/10 15:05
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@RestController
@RequestMapping("/v2/userLogin")
@Api(tags = "用户登录/注册API", description = "前端控制器")
public class UserLoginApi extends BaseController {

    @RequestMapping(value = "/userNoLogin", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "根据用户编码查询部分登录信息", httpMethod = "POST", response = UserInfoOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query", defaultValue = ""),
    })
    public Object userNoLogin(@RequestBody(required = false) @Valid RequestData<UserNoBase> requestData, BindingResult bindingResult) {
        UserNoBase data = requestData.getData();
        UserNoLoginOutput userNoLoginOutput = userInfoService.userNoLogin(data.getUserNo());
        return getSuccess(userNoLoginOutput);
    }

    @RequestMapping(value = "/getMobilePrefixs", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取国际电话号码区号列表", httpMethod = "POST", response = CountryMobilePrefixOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "lat", value = "纬度", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = true, name = "lng", value = "经度", paramType = "query", dataType = "String"),
    })
    public Object getMobilePrefixs(@RequestBody(required = false) @Valid RequestData<LatLngInput> requestData, BindingResult bindingResult) {
        LatLngInput data = requestData.getData();
        //根据坐标获取国家
        String nation = LatLngTools.coordinatesToNation(data.getLat(), data.getLng());

        List<CountryMobilePrefixOutput> list = internationalCodeService.getMobilePrefixs();
        list.forEach(countryMobilePrefixOutput -> {
            if (nation.equals(countryMobilePrefixOutput.getZhName())) {
                countryMobilePrefixOutput.setPriority(999);
            }
        });

        CollUtil.sort(list, new Comparator<CountryMobilePrefixOutput>() {
            @Override
            public int compare(CountryMobilePrefixOutput o1, CountryMobilePrefixOutput o2) {
                return String.valueOf(o1.getPriority()).compareTo(String.valueOf(o2.getPriority()));//顺序
            }
        });

        return getSuccess(list);
    }

    @RequestMapping(value = "/acceptAnInvitation", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "接受邀请领取蟠桃", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "邀请者的用户编码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = true, name = "phoneNo", value = "手机号码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = true, name = "phoneCode", value = "手机代码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = true, name = "securityCode", value = "验证码", paramType = "query", dataType = "String"),
    })
    public Object acceptAnInvitation(@RequestBody(required = false) @Valid RequestData<AcceptAnInvitationInput> requestData, BindingResult bindingResult) {
        AcceptAnInvitationInput data = requestData.getData();
        boolean acceptAnInvitation = userInfoService.acceptAnInvitation(data.getUserNo(), data.getPhoneNo(), data.getPhoneCode(), data.getSecurityCode());
        return getBoolean(acceptAnInvitation);
    }

    @RequestMapping(value = "/sendSecurityCode", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "发送验证码接口", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "phoneNo", value = "手机号码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = true, name = "phoneCode", value = "手机代码", paramType = "query", dataType = "String"),
    })
    public Object sendSecurityCode(@RequestBody(required = false) @Valid RequestData<SendSecurityCodeInput> requestData, BindingResult bindingResult) throws ClientException {
        SendSecurityCodeInput data = requestData.getData();
        boolean sendSecurityCode = userInfoService.sendSecurityCode(data.getPhoneNo(), data.getPhoneCode());
        return sendSecurityCode ? getSuccess() : getFail();
    }

    @RequestMapping(value = "/checkSecurityCode", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "校验验证码接口", httpMethod = "POST", response = ResponseData.class, hidden = true)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "phoneNo", value = "手机号码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = true, name = "phoneCode", value = "手机代码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = true, name = "securityCode", value = "验证码", paramType = "query", dataType = "String"),
    })
    public Object checkSecurityCode(@RequestBody(required = false) @Valid RequestData<CheckSecurityCodeInput> requestData, BindingResult bindingResult) throws ClientException {
        CheckSecurityCodeInput data = requestData.getData();
        boolean sendSecurityCode = userInfoService.checkSecurityCode(data.getPhoneNo(), data.getPhoneCode(), data.getSecurityCode());
        return sendSecurityCode ? getSuccess() : getFail();
    }

    /**
     * 用户登陆
     *
     * @param requestData
     * @param bindingResult
     * @return
     */
    @RequestMapping(value = "/securityCodeLogin", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "手机号+短信验证码登陆", httpMethod = "POST", response = UserInfoOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "phoneNo", value = "手机号码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = true, name = "phoneCode", value = "手机代码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = true, name = "securityCode", value = "验证码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = false, name = "userType", value = "用户类型 亚马逊用户:Amazon GoKong用户:GoKong", paramType = "query", dataType = "String"),
    })
    public Object securityCodeLogin(@RequestBody(required = false) @Valid RequestData<AuthCodeLoginInput> requestData, BindingResult bindingResult) {
        AuthCodeLoginInput data = requestData.getData();
        UserInfoOutput userInfoOutput = userInfoService.securityCodeLogin(data.getPhoneNo(), data.getPhoneCode(), data.getSecurityCode(), data.getUserType());
        try {
            if (data.getFansUserNo() != null && data.getFansUserNo() != "") {
                boolean setFocus = fansInfoService.setFocuss(data.getFansUserNo(), userInfoOutput.getUserNo());
                System.out.println("----------是否成为粉丝---------" + setFocus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getSuccess(userInfoOutput);
    }

    @RequestMapping(value = "/thirdLogin", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "三方登陆", httpMethod = "POST", response = UserInfoOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "thirdKey", value = "ID", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = true, name = "thirdType", value = "类型(微信:10320001 QQ:10320002 微博:10320003)", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = true, name = "thirdHead", value = "头像", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = true, name = "thirdName", value = "名称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = true, name = "thirdSex", value = "性别(男:10010001 女:10010002)", paramType = "query", dataType = "String"),
    })
    public Object thirdLogin(@RequestBody(required = false) @Valid RequestData<ThirdLoginInput> requestData, BindingResult bindingResult) {
        ThirdLoginInput data = requestData.getData();
        UserInfoOutput userInfoOutput = userInfoService.thirdLogin(data.getThirdKey(), data.getThirdType(), data.getThirdHead(), data.getThirdName(), data.getThirdSex());
        return getSuccess(userInfoOutput);
    }

    @PostMapping(value = "/applet_login", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "小程序登录", httpMethod = "POST", response = UserInfoOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "CODE凭证", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "rawData", value = "用户非敏感信息", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "signature", value = "签名", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "encrypteData", value = "用户敏感信息", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "iv", value = "解密算法的向量", paramType = "query", defaultValue = ""),
    })
    public ResponseData appletLogin(@RequestBody LoginInput data) {
        UserInfoOutput userInfoOutput = userInfoService.appletLogin(data.getCode(), data.getRawData(), data.getEncrypteData(), data.getIv(), data.getSignature());
        return getSuccess(userInfoOutput);
    }

    @RequestMapping(value = "/thirdLoginBindPhoneNo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "第三方登录绑定手机号码", httpMethod = "POST", response = UserInfoOutput.class, hidden = true)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "thirdKey", value = "第三方key", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = true, name = "phoneNo", value = "手机号码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = true, name = "phoneCode", value = "手机代码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = true, name = "securityCode", value = "验证码", paramType = "query", dataType = "String"),
    })
    public Object thirdLoginBindPhoneNo(@RequestBody(required = false) @Valid RequestData<ThirdLoginBindPhoneNoInput> requestData, BindingResult bindingResult) {
        ThirdLoginBindPhoneNoInput data = requestData.getData();
        UserInfoOutput userInfoOutput = userInfoService.thirdLoginBindPhoneNo(data.getThirdKey(), data.getPhoneNo(), data.getPhoneCode(), data.getSecurityCode());
        return getSuccess(userInfoOutput);
    }

    @RequestMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "用户名、手机号、email+密码登陆", httpMethod = "POST", response = UserInfoOutput.class, hidden = true)
    @ApiImplicitParams({
            @ApiImplicitParam(required = false, name = "mailboxNo", value = "邮箱", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = false, name = "userName", value = "用户名", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = false, name = "phoneNo", value = "电话号码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = true, name = "password", value = "密码", paramType = "query", dataType = "String"),
    })
    public Object login(@RequestBody(required = false) @Valid RequestData<AccountPasswordInput> requestData, BindingResult bindingResult) {
        AccountPasswordInput data = requestData.getData();
        UserInfoOutput userInfoOutput = null;
        //邮箱登录
        if (ObjectUtil.isNotNull(data.getMailboxNo())) {
            userInfoOutput = userInfoService.findByMailBoxNoAndPassword(data.getMailboxNo(), DigestUtil.md5Hex(data.getPassword()));
        }
        //电话号码登录
        if (ObjectUtil.isNotNull(data.getPhoneNo())) {
            userInfoOutput = userInfoService.findByPhoneNoAndPassword(data.getPhoneNo(), DigestUtil.md5Hex(data.getPassword()));
        }
        //用户名登录
        if (ObjectUtil.isNotNull(data.getUserName())) {
            userInfoOutput = userInfoService.findByUserNameAndPassword(data.getUserName(), DigestUtil.md5Hex(data.getPassword()));
        }
        //认证成功
        if (ObjectUtil.isNull(userInfoOutput)) {
            //认证失败
            throw new GrilException("用户名或密码错误");
        }
        return getSuccess(userInfoOutput);
    }

    /**
     * 获取用户表的经纬度信息
     */
    @RequestMapping(value = "/userstates", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "用户在线的统计", httpMethod = "POST", notes = "用户在线的统计")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "logCode", value = "经度", paramType = "query"),
            @ApiImplicitParam(name = "latCode", value = "纬度", paramType = "query"),
    })
    public Object userStates(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, String> userdata = requestData.getData();
        return userInfoService.addUserState(userdata);
    }


    /**
     * 获取用户得openid
     */
    @RequestMapping(value = "/queryOpenid", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "获取用户得openid", httpMethod = "POST", notes = "获取用户得openid")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "code编码", paramType = "query")
    })
    public Object queryOpenid(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        ResponseData responseData = new ResponseData();
        Map<String, String> data = requestData.getData();
        String code = data.get("code");
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("appid", "wx3d011869f986cd83");
        paramMap.put("secret", "ba5e4feacf0818c4e87ebb3565d1d340");
        paramMap.put("js_code", code);
        paramMap.put("grant_type", "authorization_code");
        String rep = HttpUtil.post("https://api.weixin.qq.com/sns/jscode2session", paramMap);
        StaticLog.info("rep:{}", rep);
        responseData.setCode(200);
        responseData.setData(JSONObject.parseObject(rep));
        responseData.setMessage("成功");
        return responseData;
    }

    /**
     * 获取用户得openid
     */
    @RequestMapping(value = "/loginLog", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "用户登陆日志", httpMethod = "POST", notes = "用户登陆日志")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query")
    })
    public Object loginLog(@RequestBody(required = false) RequestData<Map<String, String>> requestData) throws ParseException {
        ResponseData responseData = new ResponseData();
        Map<String, String> data = requestData.getData();
        String userno = data.get("userNo");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
        QueryWrapper<UserLog> wrapper = new QueryWrapper<>();
        wrapper.eq("user_no", userno);
        wrapper.eq("user_login_date", df.format(new Date()));
        UserLog logs = userLogService.getOne(wrapper);
        if (ObjectUtil.isNull(logs)) {
            // 判断当前是否当前登陆是否是连续7天登陆
            // 获取七天前的日期
            String seven = DataBaseTool.getfewDaysTime(7);
            seven = df.format(df.parse(seven));
            // 查询七天的数据
            int seveninfo = userLogService.querySeven(userno, seven);
            if (seveninfo >= 7) {
                userAccountService.addUserAccount(userno, new BigDecimal(50));
                // 添加用户增加
                userAccountLogService.inserlog("10050010", new BigDecimal(50), userno, userno, "登陆收益");
            } else {
                userAccountService.addUserAccount(userno, new BigDecimal(6));
                // 添加用户增加
                userAccountLogService.inserlog("10050010", new BigDecimal(6), userno, userno, "登陆收益");
            }
            UserLog userLog = new UserLog();
            userLog.setId(DataBaseTool.createId());
            userLog.setUserNo(userno);
            userLog.setUserLoginDate(df.format(new Date()));
            // 插入
            boolean loginlog = userLogService.save(userLog);
            responseData.setData(loginlog);
        }
        responseData.setCode(200);
        responseData.setMessage("成功");
        return responseData;
    }


    /**
     * 小程序解密
     */
    @RequestMapping(value = "/decryptionLogin", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "解密参数", httpMethod = "POST", notes = "解密参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ik", value = "ik", paramType = "query"),
            @ApiImplicitParam(name = "iv", value = "iv", paramType = "query"),
            @ApiImplicitParam(name = "keyVal", value = "keyVal(加密参数)", paramType = "query")
    })
    public Object decryptionLogin(@RequestBody(required = false) RequestData<Map<String, String>> requestData) throws Exception {
        ResponseData responseData = new ResponseData();
        String ik = requestData.getData().get("ik");
        String iv = requestData.getData().get("iv");
        String keyVal = requestData.getData().get("keyVal");
        byte[] ikv = Base64.getDecoder().decode(ik);
        byte[] ivv = Base64.getDecoder().decode(iv);
        byte[] keyValv = Base64.getDecoder().decode(keyVal);
        // 进行解密
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(ivv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(ikv, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        //解析解密后的字符串
        String data = new String(cipher.doFinal(keyValv), "UTF-8");
        responseData.setData(JSONObject.parse(data));
        responseData.setCode(200);
        return responseData;
    }

    /**
     * 小程序获取unionId
     */
    @RequestMapping(value = "/queryUnionId", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "解密参数", httpMethod = "POST", notes = "解密参数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "ik", value = "ik", paramType = "query"),
            @ApiImplicitParam(name = "iv", value = "iv", paramType = "query"),
            @ApiImplicitParam(name = "keyVal", value = "keyVal(加密参数)", paramType = "query")
    })
    public Object queryUnionId(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        ResponseData responseData = new ResponseData();
        String ik = requestData.getData().get("ik");
        String iv = requestData.getData().get("iv");
        String keyVal = requestData.getData().get("keyVal");
        try {
            String result = ApiTool.decrypt(keyVal, ik, iv);
            if (null != result && result.length() > 0) {
                JSONObject userInfoJSON = JSONObject.parseObject(result);
                System.out.println("------" + userInfoJSON.toString());
                Map userInfo = new HashMap();
                userInfo.put("openId", userInfoJSON.get("openId"));
                userInfo.put("nickName", userInfoJSON.get("nickName"));
                userInfo.put("gender", userInfoJSON.get("gender"));
                userInfo.put("city", userInfoJSON.get("city"));
                userInfo.put("province", userInfoJSON.get("province"));
                userInfo.put("country", userInfoJSON.get("country"));
                userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
                // 解密unionId & openId;
                userInfo.put("unionId", userInfoJSON.get("unionId"));
                responseData.setData(userInfo);
                responseData.setCode(200);
                return responseData;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        responseData.setCode(500);
        return responseData;
    }

    /**
     * 第三方绑定手机号码，然后如果手机号码以前被绑定过就把以前得手机号码用户编码替换成现在得第三方得用户编码
     */
    @RequestMapping(value = "/thirdBindPhoneNo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "第三方绑定手机号码", httpMethod = "POST", notes = "第三方绑定手机号码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "thirdKey", value = "微信UnionID", paramType = "query"),
            @ApiImplicitParam(name = "phoneNo", value = "电话号码", paramType = "query"),
            @ApiImplicitParam(name = "phoneCode", value = "手机代码", paramType = "query"),
            @ApiImplicitParam(name = "securityCode", value = "验证码", paramType = "query"),
    })
    public Object thirdBindPhoneNo(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        ResponseData responseData = new ResponseData();
        String phoneNo = requestData.getData().get("phoneNo");
        String phoneCode = requestData.getData().get("phoneCode");
        String securityCode = requestData.getData().get("securityCode");
        String thirdKey = requestData.getData().get("thirdKey");
        if (thirdKey.substring(0, 2).equals("yh")) {
            UserAuths userdata = userAuthsService.findThirdInfo(thirdKey);
            thirdKey = userdata.getThirdKey();
        }
        if (ObjectUtil.isNotNull(phoneNo) && ObjectUtil.isNotNull(phoneCode) && ObjectUtil.isNotNull(securityCode) && ObjectUtil.isNotNull(thirdKey)) {
            // 验证验证码是否正确
            boolean sendSecurityCode = userInfoService.checkSecurityCode(phoneNo, phoneCode, securityCode);
            StaticLog.info("验证码：" + sendSecurityCode);
            if (sendSecurityCode) {
                // 进行操作
                //先查询当前微信UnionID对应得用户编码
                UserAuths userAuths = userAuthsService.findByThirdKeyAndThirdType(requestData.getData().get("thirdKey"), "10320001");
                // 根据电话查询基本信息
                UserInfoOutput byUserNoInfo = userInfoService.findByPhoneNoInfo(phoneNo);
                if (ObjectUtil.isNull(byUserNoInfo)) {
                    // 说明当前手机号码以前没有注册过，然后把用户第三方得用户编码进行补全电话号码
                    boolean updateInfo = userInfoService.updateInfoPhone(phoneNo, phoneCode, userAuths.getUserNo());
                    if (updateInfo) {
                        if (requestData.getData().get("fansUserNo") != null && requestData.getData().get("fansUserNo") != "") {
                            boolean setFocus = fansInfoService.setFocuss(requestData.getData().get("fansUserNo"), userAuths.getUserNo());
                            System.out.println("----------是否成为粉丝---------" + setFocus);
                        }
                        responseData.setCode(200);
                        responseData.setMessage("成功");
                        responseData.setData(userAuths.getUserNo());
                        return responseData;
                    } else {
                        responseData.setCode(500);
                        responseData.setMessage("稍后重试");
                        return responseData;
                    }
                } else {
                    if (!userAuths.getUserNo().equals(byUserNoInfo.getUserNo())) {
                        // 把电话号码得用户编码替换成第三方得用户编码
                        boolean updateInfo = userInfoService.updateInfo(userAuths.getUserNo(), byUserNoInfo.getUserNo());
                        if (updateInfo) {
                            if (requestData.getData().get("fansUserNo") != null && requestData.getData().get("fansUserNo") != "") {
                                boolean setFocus = fansInfoService.setFocuss(requestData.getData().get("fansUserNo"), byUserNoInfo.getUserNo());
                                System.out.println("----------是否成为粉丝---------" + setFocus);
                            }
                            responseData.setCode(200);
                            responseData.setMessage("成功");
                            responseData.setData(byUserNoInfo.getUserNo());
                            return responseData;
                        } else {
                            responseData.setCode(500);
                            responseData.setMessage("稍后重试");
                            return responseData;
                        }
                    } else {
                        responseData.setCode(200);
                        responseData.setMessage(byUserNoInfo.getUserNo());
                        return responseData;
                    }
                }
            } else {
                responseData.setCode(500);
                responseData.setMessage("验证码失效");
                return responseData;
            }
        }
        responseData.setCode(500);
        responseData.setMessage("参数不能为空");
        return responseData;
    }


}
