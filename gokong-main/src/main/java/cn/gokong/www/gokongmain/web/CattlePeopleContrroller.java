package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.domain.CattlePeople;
import cn.gokong.www.gokongmain.vo.user.QueryUserAccountOutput;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.log.StaticLog;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tom
 * @since 2018-09-20
 */
@RestController
@RequestMapping("/v2/cattlePeople")
@Api(tags = "牛人的信息", description = "CattlePeopleContrroller")
public class CattlePeopleContrroller extends BaseController {


    /**
     * 查询当前用户的牛人信息
     */
    @RequestMapping(value = "/findcattlepeopledetail", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "牛人自己修改详情", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
    })
    public Object findCattlePeople(@RequestBody(required = false) @Valid RequestData<Map<String, String>> requestData) {
        ResponseData responseData = new ResponseData();
        String userno = requestData.getData().get("userNo");
        if (ObjectUtil.isNotNull(userno)) {
            CattlePeople cattlePeople = cattlePeopleService.findCattlePeopleDetail(userno);
            responseData.setCode(200);
            responseData.setData(cattlePeople);
            responseData.setMessage("参数返回成功");
            return responseData;
        }
        responseData.setCode(500);
        responseData.setMessage("数据不能为空");
        return responseData;
    }

    @RequestMapping(value = "/updatecattlepeople", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "修改信息", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "cattlePeopleAbstract", value = "牛人的简介", paramType = "query"),
            @ApiImplicitParam(name = "cattlePeoplePhoto", value = "牛人的图片链接", paramType = "query"),
            @ApiImplicitParam(name = "cattleWorth", value = "牛人自己的价值", paramType = "query"),
    })
    public Object updateCattlePeople(@RequestBody(required = false) @Valid RequestData<CattlePeople> requestData) {
        CattlePeople cattlePeople = requestData.getData();
        ResponseData responseData = cattlePeopleService.updateCattlePeople(cattlePeople);
        return responseData;
    }

    /**
     * 用户添加牛人为好友
     */
    @RequestMapping(value = "/addCattleFriend", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "添加牛人为好友", httpMethod = "POST", notes = "添加牛人为好友", response = ResponseData.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "失败"),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "friendNo", value = "牛人用户编码", paramType = "query"),
    })
    public Object flatPeachPay(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        String userno = requestData.getData().get("userNo");
        String friendno = requestData.getData().get("friendNo");
        return cattlePeopleService.addCattleFriend(userno, friendno);
    }

    /**
     * 用户自动申请加入牛人信息
     */
    @RequestMapping(value = "/addcattlePeople", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "申请加入牛人信息", httpMethod = "POST", notes = "申请加入牛人信息", response = ResponseData.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "失败"),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "cattlePeopleName", value = "牛人姓名", paramType = "query"),
            @ApiImplicitParam(required = true, name = "cattlePeopleAbstract", value = "牛人简介", paramType = "query"),
            @ApiImplicitParam(required = true, name = "cattlePeopleLabel", value = "牛人标签", paramType = "query"),
            @ApiImplicitParam(required = true, name = "cattlePeopleAddress", value = "牛人地址", paramType = "query"),
            @ApiImplicitParam(required = true, name = "cattlePeoplePhoto", value = "牛人头像", paramType = "query"),
            @ApiImplicitParam(required = true, name = "cattleWorth", value = "牛人身价", paramType = "query"),
    })
    public Object addcattlePeople(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        String userno = requestData.getData().get("userNo");
        String cattlePeopleName = requestData.getData().get("cattlePeopleName");
        String cattlePeopleAbstract = requestData.getData().get("cattlePeopleAbstract");
        String cattlePeopleLabel = requestData.getData().get("cattlePeopleLabel");
        String cattlePeopleAddress = requestData.getData().get("cattlePeopleAddress");
        String cattlePeoplePhoto = requestData.getData().get("cattlePeoplePhoto");
        String cattleWorth = requestData.getData().get("cattleWorth");
        return cattlePeopleService.addcattlePeople(userno, cattlePeopleName, cattlePeopleAbstract, cattlePeopleLabel, cattlePeopleAddress, cattlePeoplePhoto, cattleWorth);
    }

    /**
     * 上传牛人添加信息得照片
     */
    @RequestMapping(value = "/updateCattlePeoplePhoto", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "上传牛人添加信息得照片", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "img", value = "牛人照片", paramType = "query", dataType = "String"),
    })
    public Object updateCattlePeoplePhoto(@RequestParam("img") MultipartFile img) {
        // 上传当前用户得牛人照片
        String path = uploadService.ftpImg(img, "1");
        ResponseData responseData = new ResponseData();
        if (ObjectUtil.isNotNull(path)) {
            responseData.setData(path);
            responseData.setCode(200);
            responseData.setMessage("上传头像成功");
            return responseData;
        }
        responseData.setData(null);
        responseData.setCode(500);
        responseData.setMessage("上传头像失败");
        return responseData;
    }


    // 更具用户编码查询当前牛人得信息
    @RequestMapping(value = "/querycattlePeopleInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "更具用户编码查询当前牛人得信息", httpMethod = "POST", notes = "更具用户编码查询当前牛人得信息", response = ResponseData.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "失败"),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query")
    })
    public Object querycattlePeopleInfo(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, Object> msg = new HashMap<>();
        ResponseData responseData = new ResponseData();
        String userno = requestData.getData().get("userNo");
        CattlePeople cattlePeople = cattlePeopleService.querycattlePeopleInfo(userno);
        if (ObjectUtil.isNotNull(cattlePeople)) {
            msg.put("exist", true);
            msg.put("cattlePeople", cattlePeople);
        } else {
            msg.put("exist", false);
            msg.put("cattlePeople", cattlePeople);
        }
        responseData.setCode(200);
        responseData.setData(msg);
        return responseData;
    }


    // 根据用户编码查询当前用户是否申请过牛人
    @RequestMapping(value = "/cattlePeopleExist", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "根据用户编码查询当前用户是否申请过牛人", httpMethod = "POST", notes = "根据用户编码查询当前用户是否申请过牛人", response = ResponseData.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "失败"),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query")
    })
    public Object cattlePeopleExist(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        ResponseData responseData = new ResponseData();
        String userno = requestData.getData().get("userNo");
        CattlePeople cattlePeople = cattlePeopleService.querycattlePeopleInfo(userno);
        if (ObjectUtil.isNotNull(cattlePeople)) {
            responseData.setData(true);
        } else {
            responseData.setData(false);
        }
        responseData.setCode(200);
        responseData.setMessage("信息查询成功");
        return responseData;
    }


    /**
     * 用悟空币进行添加牛人信息支付
     */
    @RequestMapping(value = "/addCattle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "用悟空币进行添加牛人信息支付", httpMethod = "POST", notes = "用悟空币进行添加牛人信息支付", response = ResponseData.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "失败"),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "friendNo", value = "牛人用户编码", paramType = "query")
    })
    @Transactional(rollbackFor = Exception.class)
    public Object addCattle(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        ResponseData responseData = new ResponseData();
        // 生成支付订单号
        String orderNo = DataBaseTool.createNo("JHY");
        String userNo = requestData.getData().get("userNo");
        String friendNo = requestData.getData().get("friendNo");
        // 查询当前牛人的信息
        CattlePeople cattlePeople = cattlePeopleService.findCattlePeopleDetail(friendNo);
        // 然后先扣除用户的钱（牛人同意后把金额转到牛人账户下面，没有同意转回用户的账户上面）
        // 查看当前金额是否够支付
        QueryUserAccountOutput queryUserAccountOutput = userAccountService.queryUserAccount(userNo);
        if (cattlePeople.getCattleWorth().compareTo(new BigDecimal(0)) == 0) {
            // 说明牛人是免费得
            boolean addUserCattle = friendInviteService.applyFriend(userNo, friendNo, orderNo);
            if (addUserCattle) {
                responseData.setCode(200);
                responseData.setMessage("等待好友牛人同意！");
                responseData.setData(true);
                return responseData;
            }
            responseData.setCode(500);
            responseData.setMessage("牛人添加失败，服务器忙！");
            return responseData;
        }
        if (queryUserAccountOutput.getAccountAmount().compareTo(cattlePeople.getCattleWorth()) == -1) {
            responseData.setCode(200);
            responseData.setMessage("悟空币不足，请充值再来！");
            responseData.setData(false);
            return responseData;
        }
        // 插入用户交易的金额日志
        boolean inserUserAccountLog = userAccountLogService.inserlog("10050011", cattlePeople.getCattleWorth(), orderNo, userNo, "牛人认识");
        if (inserUserAccountLog) {
            // 减去交易的金额
            int sub = userAccountService.subtractUserAccount(userNo, cattlePeople.getCattleWorth());
            StaticLog.info("减去用户的钱：" + sub);
            if (sub > 0) {
                boolean addUserCattle = friendInviteService.applyFriend(userNo, friendNo, orderNo);
                if (addUserCattle) {
                    responseData.setCode(200);
                    responseData.setMessage("等待好友牛人同意！");
                    responseData.setData(true);
                    return responseData;
                }
            }
        }
        responseData.setCode(500);
        responseData.setMessage("牛人添加失败，服务器忙！");
        return responseData;
    }


}

