package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.easemob.dao.IMUser;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.base.jedis.HashUtil;
import cn.gokong.www.base.jedis.KeysUtil;
import cn.gokong.www.base.jwt.JwtUtil;
import cn.gokong.www.base.sms.SmsDao;
import cn.gokong.www.base.util.ApiTool;
import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.dao.UserInfoMapper;
import cn.gokong.www.gokongmain.domain.*;
import cn.gokong.www.gokongmain.enums.DefualtEnum;
import cn.gokong.www.gokongmain.enums.ResponseEnum;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.service.*;
import cn.gokong.www.gokongmain.vo.login.UserInfoEncryptedData;
import cn.gokong.www.gokongmain.vo.user.PageSearchUserOutput;
import cn.gokong.www.gokongmain.vo.user.UserInfoOutput;
import cn.gokong.www.gokongmain.vo.user.UserNoLoginOutput;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import cn.ikeek.www.weixin.login.bean.Code2Session;
import cn.ikeek.www.weixin.login.dao.WeiXinDao;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户基础信息表 服务实现类
 * </p>
 *
 * @author iKook
 * @since 2018-09-08
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    UserAuthsService userAuthsService;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    UserAccountLogService userAccountLogService;

    @Autowired
    PlatformAccountService platformAccountService;

    @Autowired
    PlatformAccountLogService platformAccountLogService;

    @Autowired
    MarketingSendSetService marketingSendSetService;

    @Autowired
    UserHomePageService userHomePageService;

    @Autowired
    UploadService uploadService;

    /**
     * 根据邮箱和密码认证用户
     *
     * @param mailboxNo 邮箱
     * @param passWord  密码
     * @return
     */
    @Override
    public UserInfoOutput findByMailBoxNoAndPassword(String mailboxNo, String passWord) {
        UserInfoOutput userInfoOutput = baseMapper.findByMailBoxNoAndPassword(mailboxNo, passWord);
        if (ObjectUtil.isNotNull(userInfoOutput)) {
            //修改最后登录日期
            updateLastLoginDate(userInfoOutput.getUserNo());
        }
        return userInfoOutput;
    }

    /**
     * 根据电话号码和密码认证用户
     *
     * @param phoneNo  电话号码
     * @param passWord 密码
     * @return
     */
    @Override
    public UserInfoOutput findByPhoneNoAndPassword(String phoneNo, String passWord) {
        UserInfoOutput userInfoOutput = baseMapper.findByPhoneNoAndPassword(phoneNo, passWord);
        if (ObjectUtil.isNotNull(userInfoOutput)) {
            //修改最后登录日期
            updateLastLoginDate(userInfoOutput.getUserNo());
        }
        return userInfoOutput;
    }

    /**
     * 根据用户名和密码认证用户
     *
     * @param userName 用户名
     * @param passWord 密码
     * @return
     */
    @Override
    public UserInfoOutput findByUserNameAndPassword(String userName, String passWord) {
        UserInfoOutput userInfoOutput = baseMapper.findByUserNameAndPassword(userName, passWord);
        if (ObjectUtil.isNotNull(userInfoOutput)) {
            //修改最后登录日期
            updateLastLoginDate(userInfoOutput.getUserNo());
        }
        return userInfoOutput;
    }

    /**
     * 发送验证码
     *
     * @param phoneNo   电话号码
     * @param phoneCode 手机代码
     * @return
     */
    @Override
    public boolean sendSecurityCode(String phoneNo, String phoneCode) throws ClientException {
        //生成6位随机验证码
        String code = RandomUtil.randomNumbers(4);
        boolean sendState = false;
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        phoneNo = phoneCode + phoneNo;
        //大陆电话号码
        if ("0086".equals(phoneCode)) {
            sendState = SmsDao.sendSms(phoneNo, map, "SMS_136387226");
        } else {
            //海外电话号码
            sendState = SmsDao.sendSms(phoneNo, map, "SMS_148613603");
        }
        if (sendState) {
            KeysUtil.set(phoneNo, code, 5 * 60);
        }
        return sendState;
    }

    /**
     * 校验验证码
     *
     * @param phoneNo      电话号码
     * @param phoneCode    电话代码
     * @param securityCode 验证码
     * @return
     */
    @Override
    public boolean checkSecurityCode(String phoneNo, String phoneCode, String securityCode) throws GrilException {
        // TODO 测试使用
        if (securityCode.equals("1111")) {
            return true;
        }
        phoneNo = phoneCode + phoneNo;
        boolean isok = false;
        //验证当前key是否存在
        if (!KeysUtil.exists(phoneNo)) {
            throw new GrilException("验证码过期");
        }
        //验证当前验证码是否匹配
        if (KeysUtil.get(phoneNo).equals(securityCode)) {
            isok = true;
        } else {
            throw new GrilException("验证码错误");
        }

        //删除当前验证码
        KeysUtil.del(phoneNo);
        return isok;
    }

    /**
     * 第三方登录
     *
     * @param thirdKey  第三方KEY
     * @param thirdType 第三方类型
     * @param thirdHead 头像
     * @param thirdName 名称
     * @param thirdSex  性别
     * @return
     */
    @Override
    @Transactional
    public UserInfoOutput thirdLogin(String thirdKey, String thirdType, String thirdHead, String thirdName, String thirdSex) {
        UserAuths userAuths = userAuthsService.findByThirdKeyAndThirdType(thirdKey, thirdType);
        UserInfoOutput userInfoOutput = null;
        //如果第三方信息不为空
        if (ObjectUtil.isNotNull(userAuths)) {
            //查询用户信息
            userInfoOutput = findByUserNoInfo(userAuths.getUserNo());
        } else {
            /*Map map = new HashMap();
            map.put("thirdType", thirdType);
            map.put("thirdHead", thirdHead);
            map.put("thirdName", thirdName);
            map.put("thirdSex", thirdSex);
            HashUtil.hmset(thirdKey, map);
            throw new GrilException(ResponseEnum.WANT_PHONENO_BIND);*/
            //保存用户信息
            UserInfo userInfo = new UserInfo();
            userInfo.setUserNo(DataBaseTool.createNo("yh"));
            userInfo.setNickName(thirdName);
            userInfo.setUserHead(thirdHead);
            userInfo.setSexNo(thirdSex);
            userInfo.setPhoneNo(thirdKey);
            boolean saveUserInfo = saveUserInfo(userInfo);
            if (!saveUserInfo) {
                throw new GrilException("保存用户失败");
            }
            //保存第三方信息
            userAuths = new UserAuths();
            userAuths.setUserNo(userInfo.getUserNo());
            userAuths.setThirdKey(thirdKey);
            userAuths.setThirdType("10320001");
            boolean saveUserAuths = userAuthsService.saveUserAuths(userAuths);
            if (!saveUserAuths) {
                throw new GrilException("保存第三方信息失败");
            }

            //查询用户信息
            userInfoOutput = findByUserNoInfo(userInfo.getUserNo());
            return userInfoOutput;
        }
        return userInfoOutput;
    }

    /**
     * 第三方登录绑定手机号码
     *
     * @param thirdKey     第三方KEY
     * @param phoneNo      电话号码
     * @param phoneCode    区号
     * @param securityCode 验证码
     * @return
     */
    @Override
    @Transactional
    public UserInfoOutput thirdLoginBindPhoneNo(String thirdKey, String phoneNo, String phoneCode, String securityCode) {
        //校验验证码
        checkSecurityCode(phoneNo, phoneCode, securityCode);

        //判断当前电话号码是否已绑定其他第三方账号
        boolean checkPhoneNoIsBind = checkPhoneNoIsBind(phoneNo);
        if (checkPhoneNoIsBind) {
            throw new GrilException("当前电话号码已被其他账号绑定");
        }

        if (!KeysUtil.exists(thirdKey)) {
            throw new GrilException(ResponseEnum.SESSION_TIMEOUT);
        }

        Map<String, String> map = HashUtil.hgetAll(thirdKey);

        //保存用户信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserNo(DataBaseTool.createNo("yh"));
        userInfo.setNickName(map.get("thirdName"));
        userInfo.setUserHead(map.get("thirdHead"));
        userInfo.setSexNo(map.get("thirdSex"));
        userInfo.setPhoneNo(phoneNo);
        boolean saveUserInfo = saveUserInfo(userInfo);
        if (!saveUserInfo) {
            throw new GrilException("保存用户失败");
        }

        //保存第三方信息
        UserAuths userAuths = new UserAuths();
        userAuths.setUserNo(userInfo.getUserNo());
        userAuths.setThirdKey(thirdKey);
        userAuths.setThirdType(map.get("thirdType"));
        boolean saveUserAuths = userAuthsService.saveUserAuths(userAuths);
        if (!saveUserAuths) {
            throw new GrilException("保存第三方信息失败");
        }

        //查询用户信息
        UserInfoOutput userInfoOutput = findByUserNoInfo(userInfo.getUserNo());
        return userInfoOutput;
    }

    /**
     * 校验电话号码是否已被绑定
     *
     * @param phoneNo 电话号码
     * @return true:已绑定 false：未绑定
     */
    @Override
    public boolean checkPhoneNoIsBind(String phoneNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("phone_no", phoneNo);
        UserInfo userInfo = getOne(wrapper);
        if (ObjectUtil.isNotNull(userInfo)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 保存用户信息
     *
     * @param userInfo 用户信息
     * @return
     */
    @Override
    public boolean saveUserInfo(UserInfo userInfo) {
        userInfo.setId(DataBaseTool.createId());
        userInfo.setRegionDate(DataBaseTool.createDate());
        if (StrUtil.isEmpty(userInfo.getUserType())) {
            userInfo.setUserType("GoKong");
        }
        userInfo.setUserState(SysCodeEnum.CODE_10030001.getCode());
        //保存用户信息
        boolean save = save(userInfo);
        if (!save) {
            throw new GrilException("保存用户信息失败");
        }

        if (!SysCodeEnum.CODE_10020002.getMsg().equalsIgnoreCase(userInfo.getUserType())) {
            //添加环信账号
            boolean user = IMUser.createUser(userInfo.getUserNo(), userInfo.getUserNo());
            if (!user) {
                throw new GrilException("添加环信账号失败");
            }
            //修改环信昵称
            boolean updateUserNickName = IMUser.updateUserNickName(userInfo.getUserNo(), userInfo.getNickName());
            if (!updateUserNickName) {
                throw new GrilException("修改环信昵称失败");
            }
        }

        return true;
    }

    /**
     * 根据用户编码查询用户登录信息
     *
     * @param userNo 用户编码
     * @return
     */
    @Override
    public UserInfoOutput findByUserNoInfo(String userNo) {
        UserInfoOutput byUserNoInfo = baseMapper.findByUserNoInfo(userNo);

        if (ObjectUtil.isNull(byUserNoInfo)) {
            throw new GrilException("编码为:" + userNo + "的用户信息为空");
        } else {
            //修改最后登录日期
            updateLastLoginDate(userNo);
        }

        return byUserNoInfo;
    }

    /**
     * 根据用户编码修改用户最后登录日期
     *
     * @param userNo 用户编码
     * @return
     */
    @Override
    public boolean updateLastLoginDate(String userNo) {
        //修改最后登录日期
        UserInfo byUserNo = findByUserNo(userNo);
        byUserNo.setLastLoginDate(DataBaseTool.createDate());
        return saveOrUpdate(byUserNo);
    }

    /**
     * 根据电话号码查询用户登录信息
     *
     * @param phoneNo 电话号码
     * @return
     */
    @Override
    public UserInfoOutput findByPhoneNoInfo(String phoneNo) {
        UserInfoOutput byUserNoInfo = baseMapper.findByPhoneNoInfo(phoneNo);

        if (ObjectUtil.isNotNull(byUserNoInfo)) {
            //修改最后登录日期
            updateLastLoginDate(byUserNoInfo.getUserNo());
        }
        return byUserNoInfo;
    }

    /**
     * 验证码登录
     *
     * @param phoneNo      电话号码
     * @param phoneCode    区号
     * @param securityCode 验证码
     * @param userType     用户类型
     * @return
     */
    @Override
    @Transactional
    public UserInfoOutput securityCodeLogin(String phoneNo, String phoneCode, String securityCode, String userType) {
        //检验验证码
        checkSecurityCode(phoneNo, phoneCode, securityCode);

        //查询用户信息
        UserInfoOutput userInfoOutput = findByPhoneNoInfo(phoneNo);

        if (ObjectUtil.isNull(userInfoOutput)) {
            //当前电话号码还未注册 立即注册
            UserInfo userInfo = new UserInfo();
            userInfo.setUserNo(DataBaseTool.createNo("yh"));
            userInfo.setPhoneNo(phoneNo);
            userInfo.setUserHead(DefualtEnum.CODE_10010001.getUrl());
            userInfo.setNickName(DataBaseTool.createNickName());
            userInfo.setPhoneCode(phoneCode);
            userInfo.setUserType(userType);

            //保存用户信息
            saveUserInfo(userInfo);

            //初始化个人账户信息
            UserAccount userAccount = new UserAccount();
            userAccount.setId(DataBaseTool.createId());
            userAccount.setUserNo(userInfo.getUserNo());
            userAccount.setAccountAmount(new BigDecimal(0));
            userAccount.setCashAccountAmount(new BigDecimal(0));
            userAccount.setForzenAccountAmount(new BigDecimal(0));
            userAccountService.save(userAccount);

            //初始化个人主页信息
            UserHomePage userHomePage = new UserHomePage();
            userHomePage.setId(DataBaseTool.createId());
            userHomePage.setUserNo(userInfo.getUserNo());
            userHomePageService.save(userHomePage);

            //赠送蟠桃
            //sendPeento(userInfo.getUserNo(), SysCodeEnum.CODE_10260001, SysCodeEnum.CODE_10050001, false);

            //查询用户信息
            userInfoOutput = findByUserNoInfo(userInfo.getUserNo());


        } else {
            if (!SysCodeEnum.CODE_10020002.getMsg().equalsIgnoreCase(userType) && SysCodeEnum.CODE_10020002.getMsg().equalsIgnoreCase(userInfoOutput.getUserType())) {
                //添加环信账号
                boolean user = IMUser.createUser(userInfoOutput.getUserNo(), userInfoOutput.getUserNo());
                if (!user) {
                    throw new GrilException("环信账号上限");
                }
                //修改环信昵称
                boolean updateUserNickName = IMUser.updateUserNickName(userInfoOutput.getUserNo(), userInfoOutput.getNickName());
                if (!updateUserNickName) {
                    throw new GrilException("修改环信昵称失败");
                }

                UserInfo byUserNo = findByUserNo(userInfoOutput.getUserNo());
                byUserNo.setUserType(SysCodeEnum.CODE_10020001.getMsg());
                saveOrUpdate(byUserNo);
            }
        }


        String token = JwtUtil.buildJWT(userInfoOutput.getUserNo());
        KeysUtil.set(userInfoOutput.getUserNo(), token);
        userInfoOutput.setToken(token);
        return userInfoOutput;
    }

    /**
     * 修改用户信息
     *
     * @param userNo    用户编码
     * @param nickName  用户昵称
     * @param sexNo     用户性别
     * @param userSign  用户签名
     * @param userLocal 常住地
     * @return
     */
    @Override
    @Transactional
    public boolean updateUserInfo(String userNo, String nickName, String sexNo, String userSign, String userLocal) {
        //修改环信信息
        IMUser.updateUserNickName(userNo, nickName);

        UserInfo userInfo = findByUserNo(userNo);
        userInfo.setNickName(nickName);
        userInfo.setSexNo(sexNo);
        userInfo.setUserSign(userSign);
        userInfo.setUserLocal(userLocal);
        return saveOrUpdate(userInfo);
    }

    /**
     * 找回密码-重置密码
     *
     * @param phoneNo      电话号码
     * @param phoneCode    国际区号
     * @param password     新密码
     * @param securityCode 验证码
     * @return
     */
    @Override
    @Transactional
    public boolean resetPasswd(String phoneNo, String phoneCode, String password, String securityCode) {
        //校验验证码
        checkSecurityCode(phoneNo, phoneCode, securityCode);

        //查询用户信息
        UserInfo userInfo = findByPhoneNo(phoneNo);

        userInfo.setPassword(DigestUtil.md5Hex(password));
        return saveOrUpdate(userInfo);
    }

    /**
     * 根据用户编码查询用户信息
     *
     * @param userNo 用户编码
     * @return
     */
    @Override
    public UserInfo findByUserNo(String userNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_no", userNo);
        UserInfo userInfo = getOne(wrapper);

        if (ObjectUtil.isNull(userInfo)) {
            throw new GrilException("用户编码为:" + userNo + "的用户不存在");
        }
        return userInfo;
    }

    /**
     * 根据电话号码查询用户信息
     *
     * @param phoneNo 电话号码
     * @return
     */
    @Override
    public UserInfo findByPhoneNo(String phoneNo) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("phone_no", phoneNo);
        UserInfo userInfo = getOne(wrapper);

        if (ObjectUtil.isNull(userInfo)) {
            throw new GrilException("电话号码为:" + phoneNo + "的用户不存在");
        }
        return userInfo;
    }

    /**
     * 修改用户头像信息
     *
     * @param userNo   用户编码
     * @param userHead 用户头像地址
     * @return
     */
    public String updateUserHead(String userNo, MultipartFile userHead) {
        String userhead = uploadService.ftpImg(userHead, "1");
        if (userhead != null) {
            UserInfo userInfo = findByUserNo(userNo);
            userInfo.setUserHead(userhead);
            boolean tf = saveOrUpdate(userInfo);
            if (tf) {
                return userhead;
            }
            return null;
        }
        return null;
    }

    /**
     * 赠送蟠桃
     *
     * @param userNo       用户编码
     * @param sendType     赠送类型
     * @param changeMode   资金变动方式
     * @param isWithdrawal 是否可提现
     * @return
     */
    @Override
    public boolean sendPeento(String userNo, SysCodeEnum sendType, SysCodeEnum changeMode, boolean isWithdrawal) {
        //一.查询平台总金额
        PlatformAccount platformAccount = platformAccountService.getOne(null);
        if (ObjectUtil.isNull(platformAccount)) {
            StaticLog.error("未查询到平台金额");
            return false;
        }

        //二.获取营销表当前时间段赠送金额
        MarketingSendSet marketingSendSet = marketingSendSetService.queryMarketingSendConfig(new Date(), sendType.getCode());
        if (ObjectUtil.isNull(marketingSendSet)) {
            StaticLog.error("未查询到当前时间段内的营销套餐");
            return false;
        }

        //平台金额
        BigDecimal accountAmount = platformAccount.getAccountAmount();
        //赠送金额
        BigDecimal sendAmount = marketingSendSet.getSendAmount();
        //平台金额 < 赠送金额
        if (accountAmount.compareTo(sendAmount) == -1) {
            StaticLog.error("平台赠送金额不足");
            return false;
        }

        //变动平台金额
        platformAccount.setAccountAmount(accountAmount.subtract(sendAmount));
        platformAccountService.saveOrUpdate(platformAccount);

        //添加平台账户变动日志
        PlatformAccountLog platformAccountLog = new PlatformAccountLog();
        platformAccountLog.setId(DataBaseTool.createId());
        platformAccountLog.setUserNo(userNo);
        platformAccountLog.setChangeAmount(sendAmount.negate());
        platformAccountLog.setChangeDate(DataBaseTool.createDate());
        platformAccountLog.setChangeMode(changeMode.getCode());
        platformAccountLog.setChangeNo(marketingSendSet.getSendNo());
        platformAccountLog.setChangeRemark(changeMode.getMsg());
        platformAccountLogService.save(platformAccountLog);

        //更新个人账户余额
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_no", userNo);
        UserAccount userAccount = userAccountService.getOne(wrapper);

        if (ObjectUtil.isNotNull(userAccount)) {
            //是否可提现（如果可以提现 就加入到可提现的账户 否则加入到 不可提现的账户）
            if (isWithdrawal) {
                userAccount.setCashAccountAmount(userAccount.getCashAccountAmount().add(sendAmount));
            } else {
                userAccount.setAccountAmount(userAccount.getAccountAmount().add(sendAmount));
            }
            userAccountService.saveOrUpdate(userAccount);

            //添加个人账户变动日志
            UserAccountLog userAccountLog = new UserAccountLog();
            userAccountLog.setId(DataBaseTool.createId());
            userAccountLog.setUserNo(userNo);
            userAccountLog.setChangeDate(DataBaseTool.createDate());
            userAccountLog.setChangeAmount(sendAmount);
            userAccountLog.setChangeMode(changeMode.getCode());
            userAccountLog.setChangeRemark(changeMode.getMsg());
            userAccountLog.setChangeNo(marketingSendSet.getSendNo());
            userAccountLogService.save(userAccountLog);
        }
        return true;
    }

    /**
     * 绑定手机号码
     *
     * @param userNo       用户编码
     * @param phoneNo      电话号码
     * @param phoneCode    手机代码
     * @param securityCode 验证码
     * @return
     */
    @Override
    public boolean bindPhoneNo(String userNo, String phoneNo, String phoneCode, String securityCode) {
        //校验验证码
        checkSecurityCode(phoneNo, phoneCode, securityCode);

        //查询手机号码是否已被

        return false;
    }

    /**
     * 采集用户的信息
     *
     * @param userdata
     */
    public ResponseData addUserState(Map<String, String> userdata) {
        ResponseData responseData = new ResponseData();
        String userNo = userdata.get("userNo");
        Date date = DataBaseTool.createDate();
        String log = userdata.get("logCode");
        String lat = userdata.get("latCode");
        if (ObjectUtil.isNotNull(log)) {
            int up = this.baseMapper.addUserStates(userNo, date, log, lat);
            if (up > 0) {
                responseData.setCode(200);
                return responseData;
            }
        }
        responseData.setCode(201);
        return responseData;
    }

    /**
     * 接受邀请
     *
     * @param userNo       邀请者编码
     * @param phoneNo      电话号码
     * @param phoneCode    电话代码
     * @param securityCode 验证码
     * @return
     */
    @Override
    public boolean acceptAnInvitation(String userNo, String phoneNo, String phoneCode, String securityCode) {
        //校验验证码
        checkSecurityCode(phoneNo, phoneCode, securityCode);

        //判断用户是否已经领取
        UserInfo userInfo = baseMapper.findByPhoneNo(phoneNo);

        if (ObjectUtil.isNotNull(userInfo)) {
            throw new GrilException("您已领取过蟠桃，请下载APP查看详情");
        }

        //注册用户
        securityCodeLogin(phoneNo, phoneCode, securityCode, null);

        //赠送邀请者蟠桃
        sendPeento(userNo, SysCodeEnum.CODE_10260002, SysCodeEnum.CODE_10050003, false);

        return true;
    }

    /**
     * 分页检索用户列表
     *
     * @param userNo   用户编码
     * @param keyword  检索内容
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Override
    public List<PageSearchUserOutput> pageSearchUser(String userNo, String keyword, Integer pageNo, Integer pageSize) {

        List<PageSearchUserOutput> pageSearchUserOutputs = baseMapper.pageSearchUser(userNo, keyword, pageNo, pageSize);

        //如果是自己就不能加好友
        pageSearchUserOutputs.forEach(pageSearchUserOutput -> {
            if (pageSearchUserOutput.getUserNo().equals(userNo)) {
                pageSearchUserOutput.setFriend(true);
            }
        });

        return pageSearchUserOutputs;
    }

    /**
     * 根据用户编码查询部分登录信息
     *
     * @param userNo 用户编码
     * @return
     */
    @Override
    public UserNoLoginOutput userNoLogin(String userNo) {
        UserNoLoginOutput userNoLoginOutput = baseMapper.userNoLogin(userNo);
        // 查询用户余额
        BigDecimal bigDecimal = baseMapper.queryAccountAmount(userNo);
        // 查询用户蟠桃余额
        if (ObjectUtil.isNotNull(bigDecimal)) {
            userNoLoginOutput.setAccountAmount(bigDecimal);
        } else {
            userNoLoginOutput.setAccountAmount(new BigDecimal(0));
        }
        // 查询当前用户是否绑定手机号码
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("user_no", userNo);
        UserInfo userInfo = getOne(wrapper);
        if (ObjectUtil.isNotNull(userInfo.getPhoneCode())) {
            userNoLoginOutput.setBooleanP(true);
        } else {
            userNoLoginOutput.setBooleanP(false);
        }
        // 查询微信绑定信息是否存在
        UserAuths userAuths = userAuthsService.findThirdInfo(userNo);
        if (ObjectUtil.isNull(userAuths)) {
            userNoLoginOutput.setUserNo("");
        }
        return userNoLoginOutput;
    }

    /**
     * 随机查询用户
     *
     * @param userType 用户类型
     * @return
     */
    @Override
    public UserInfo findRandomUserInfo(String userType) {
        return baseMapper.findRandomUserInfo(userType);
    }

    @Override
    public UserInfoOutput appletLogin(String code, String rawData, String encrypteData, String iv, String signature) {
        //获取openid
        Code2Session code2Session = WeiXinDao.code2Session(code);

        if (code2Session == null) {
            throw new GrilException("请求数据格式不正确");
        }
        StaticLog.info("rawData:{}", rawData);

        StaticLog.info("sessionkey========================================{}", code2Session.toString());

        String data = ApiTool.decrypt(encrypteData, code2Session.getSession_key(), iv);

        UserInfoEncryptedData userInfoEncryptedData = JSONUtil.toBean(data, UserInfoEncryptedData.class);

        //校验参数签名
        String sign = SecureUtil.sha1(rawData + code2Session.getSession_key());

        if (sign.equals(signature)) {
            StaticLog.info("参数校验通过");
            //查询本地用户信息
            if (userInfoEncryptedData == null) {
                throw new GrilException("第三方信息查询为空");
            }
            return thirdLogin(userInfoEncryptedData.getUnionId(), "10320001", userInfoEncryptedData.getAvatarUrl(), userInfoEncryptedData.getNickName(), userInfoEncryptedData.getGender());
        } else {
            throw new GrilException("签名不正确");
        }
    }

    /**
     * @param userNo      第三方用户编码
     * @param userPhoneNo 电话号码得登陆
     * @return
     */
    public boolean updateInfo(String userNo, String userPhoneNo) {
        // 删除userinfo得数据
        int detle = baseMapper.deleteInfo(userNo);
        int data = baseMapper.updateInfo(userNo, userPhoneNo);
        if (data > 0) {
            return true;
        }
        return false;
    }

    /**
     * @param phoneNo   手机号码
     * @param phoneCode 国家代码
     * @param userNo    第三方用户编码
     * @return
     */
    public boolean updateInfoPhone(String phoneNo, String phoneCode, String userNo) {
        int data = baseMapper.updateInfoPhone(phoneNo, phoneCode, userNo);
        if (data > 0) {
            return true;
        }
        return false;
    }
}
