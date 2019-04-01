package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.vo.base.PageQuerySearchUserNoBase;
import cn.gokong.www.gokongmain.vo.user_home.DelUserHomeImgInput;
import cn.gokong.www.gokongmain.vo.base.PageQueryUserNoBase;
import cn.gokong.www.gokongmain.vo.user.*;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.log.StaticLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户基础信息表 前端控制器
 * </p>
 *
 * @author iKook
 * @since 2018-09-08
 */
@RestController
@RequestMapping("/v2/userInfo")
@Api(tags = "用户基础信息", description = "前端控制器")
public class UserInfoApi extends BaseController {


    @RequestMapping(value = "/queryUserHome", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "查询用户主页", httpMethod = "POST", response = QueryUserHomeOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "被访问者编码", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "visitorNo", value = "访问者编码", paramType = "query", defaultValue = ""),
    })
    public Object queryUserHome(@RequestBody(required = false) @Valid RequestData<UserHomeInput> requestData, BindingResult bindingResult) {
        UserHomeInput data = requestData.getData();
        QueryUserHomeOutput queryUserHomeOutput = userHomePageService.queryUserHome(data.getUserNo(), data.getVisitorNo());
        return getSuccess(queryUserHomeOutput);
    }

    @RequestMapping(value = "/setUserHomeImg", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "添加/修改个人主页图片", httpMethod = "POST", response = QueryUserHomeOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query", defaultValue = ""),
    })
    public Object addUserHomeImg(@RequestParam("img") MultipartFile img, @RequestParam String userNo) {
        ResponseData responseData = new ResponseData();
        // 查询当前用户的个人主页图片那些字段为空，然后根据角标存入数据库
        // ,home_photo2,home_photo3,home_photo4,home_photo5,home_photo6,home_photo7,home_photo8,home_photo9
        Map<String, String> shopIndex = userHomePageService.findImgIndex(userNo);
        int imgNo = 1;
        if (shopIndex != null) {
            if (ObjectUtil.isNull(shopIndex.get("home_photo1")) || shopIndex.get("home_photo1").equals("")) {
                imgNo = 1;
            } else if (ObjectUtil.isNull(shopIndex.get("home_photo2")) || shopIndex.get("home_photo2").equals("")) {
                imgNo = 2;
            } else if (ObjectUtil.isNull(shopIndex.get("home_photo3")) || shopIndex.get("home_photo3").equals("")) {
                imgNo = 3;
            } else if (ObjectUtil.isNull(shopIndex.get("home_photo4")) || shopIndex.get("home_photo4").equals("")) {
                imgNo = 4;
            } else if (ObjectUtil.isNull(shopIndex.get("home_photo5")) || shopIndex.get("home_photo5").equals("")) {
                imgNo = 5;
            } else if (ObjectUtil.isNull(shopIndex.get("home_photo6")) || shopIndex.get("home_photo6").equals("")) {
                imgNo = 6;
            } else if (ObjectUtil.isNull(shopIndex.get("home_photo7")) || shopIndex.get("home_photo7").equals("")) {
                imgNo = 7;
            } else if (ObjectUtil.isNull(shopIndex.get("home_photo8")) || shopIndex.get("home_photo8").equals("")) {
                imgNo = 8;
            } else if (ObjectUtil.isNull(shopIndex.get("home_photo9")) || shopIndex.get("home_photo9").equals("")) {
                imgNo = 9;
            }
            StaticLog.info("用户编码：" + userNo + "图片索引：" + imgNo);
            String addUserHomeImg = userHomePageService.addUserHomeImg(userNo, imgNo, img);
            if (addUserHomeImg != null) {
                responseData.setMessage("成功");
                responseData.setCode(200);
                responseData.setData(addUserHomeImg);
                return responseData;
            }
        }else {
            String addUserHomeImg = userHomePageService.addUserHomeImg(userNo, imgNo, img);
            if (addUserHomeImg != null) {
                responseData.setMessage("成功");
                responseData.setCode(200);
                responseData.setData(addUserHomeImg);
                return responseData;
            }
        }
        responseData.setMessage("失败");
        responseData.setCode(500);
        return responseData;
    }

    @RequestMapping(value = "/delUserHomeImg", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "删除个人主页图片", httpMethod = "POST", response = QueryUserHomeOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "imgNo", value = "图片index", paramType = "query", defaultValue = "1"),
    })
    public Object delUserHomeImg(@RequestBody(required = false) @Valid RequestData<DelUserHomeImgInput> requestData, BindingResult bindingResult) {
        DelUserHomeImgInput data = requestData.getData();
        boolean addUserHomeImg = userHomePageService.delUserHomeImg(data.getUserNo(), data.getImgNo());
        return addUserHomeImg ? getSuccess() : getFail();
    }

    @RequestMapping(value = "/setUserHomeCover", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "设置用户主页封面", httpMethod = "POST", response = QueryUserHomeOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "imgNo", value = "封面标识", paramType = "query", defaultValue = "1"),
    })
    public Object setUserHomeCover(@RequestBody(required = false) @Valid RequestData<DelUserHomeImgInput> requestData, BindingResult bindingResult) {
        DelUserHomeImgInput data = requestData.getData();
        boolean setUserHomeCover = userHomePageService.setUserHomeCover(data.getUserNo(), data.getImgNo());
        return setUserHomeCover ? getSuccess() : getFail();
    }


    @RequestMapping(value = "/updateUserInfo", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "修改用户信息", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = false, name = "nickName", value = "用户昵称", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = false, name = "sexNo", value = "用户性别", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = false, name = "userSign", value = "用户签名", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = false, name = "userLocal", value = "常住地", paramType = "query", dataType = "String"),
    })
    public Object updateUserInfo(@RequestBody(required = false) @Valid RequestData<UpdateUserInfoInput> requestData, BindingResult bindingResult) {
        UpdateUserInfoInput data = requestData.getData();
        boolean updateUserInfo = userInfoService.updateUserInfo(data.getUserNo(), data.getNickName(), data.getSexNo(), data.getUserSign(), data.getUserLocal());
        return updateUserInfo ? getSuccess() : getFail();
    }

    @RequestMapping(value = "/resetPasswd", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "找回密码-重置密码", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "phoneNo", value = "手机号码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = true, name = "phoneCode", value = "区号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = true, name = "password", value = "新密码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = true, name = "securityCode", value = "验证码", paramType = "query", dataType = "String"),
    })
    public Object resetPasswd(@RequestBody(required = false) @Valid RequestData<ResetPasswdInput> requestData, BindingResult bindingResult) {
        ResetPasswdInput data = requestData.getData();
        boolean resetPasswd = userInfoService.resetPasswd(data.getPhoneNo(), data.getPhoneCode(), data.getPassword(), data.getSecurityCode());
        return resetPasswd ? getSuccess() : getFail();
    }

    @RequestMapping(value = "/updateUserHead", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "修改用户头像", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query", dataType = "String"),
            @ApiImplicitParam(required = true, name = "img", value = "头像", paramType = "query", dataType = "String"),
    })
    public Object updateUserHead(@RequestParam("img") MultipartFile img, @RequestParam String userNo) {
        String updateUserHead = userInfoService.updateUserHead(userNo, img);
        ResponseData responseData = new ResponseData();
        if (updateUserHead != null) {
            responseData.setData(updateUserHead);
            responseData.setCode(200);
            responseData.setMessage("头像修改成功");
            return responseData;
        }
        responseData.setCode(500);
        responseData.setMessage("头像修改失败");
        return responseData;
    }

    @RequestMapping(value = "/pageQueryUserFocus", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "分页获取用户粉丝", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "pageNo", value = "当前页", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(required = true, name = "pageSize", value = "开始页面", paramType = "query", defaultValue = "10"),
    })
    public Object pageQueryUserFocus(@RequestBody(required = false) @Valid RequestData<PageQueryUserNoBase> requestData, BindingResult bindingResult) {
        PageQueryUserNoBase data = requestData.getData();
        List<QueryUserOutput> queryUserOutputs = fansInfoService.pageQueryUserFocus(data.getUserNo(), data.getPageNo(), data.getPageSize());
        return getSuccess(queryUserOutputs);
    }

    @RequestMapping(value = "/pageSearchUser", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "分页检索用户列表", httpMethod = "POST", response = PageSearchUserOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(required = false, name = "keyWord", value = "检索内容", paramType = "query"),
            @ApiImplicitParam(required = true, name = "pageNo", value = "当前页", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(required = true, name = "pageSize", value = "开始页面", paramType = "query", defaultValue = "10"),
    })
    public Object pageSearchUser(@RequestBody(required = false) @Valid RequestData<PageQuerySearchUserNoBase> requestData, BindingResult bindingResult) {
        PageQuerySearchUserNoBase data = requestData.getData();
        List<PageSearchUserOutput> pageSearchUserOutputs = userInfoService.pageSearchUser(data.getUserNo(),data.getKeyWord(),data.getPageNo(), data.getPageSize());
        return getSuccess(pageSearchUserOutputs);
    }

}

