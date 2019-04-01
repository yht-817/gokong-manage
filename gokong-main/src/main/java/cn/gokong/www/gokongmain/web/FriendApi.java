package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.domain.BlacFriendInfo;
import cn.gokong.www.gokongmain.vo.base.PageQuerySearchUserNoBase;
import cn.gokong.www.gokongmain.vo.friend.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 好友邀请表 前端控制器
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@RestController
@RequestMapping("/v2/friendApi")
@Api(tags = "好友管理(添加/审核/查询好友列表/删除好友)", description = "FriendApi")
public class FriendApi extends BaseController {

    @RequestMapping(value = "/applyFriend", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "申请添加好友", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query", defaultValue = "yh15367309978639"),
            @ApiImplicitParam(name = "friendUserNo", value = "好友编码", paramType = "query"),
            @ApiImplicitParam(name = "verifyInfo", value = "验证消息", paramType = "query", defaultValue = "验证消息"),
    })
    public Object applyFriend(@RequestBody(required = false) @Valid RequestData<ApplyFriendInput> requestData, BindingResult bindingResult) {
        ApplyFriendInput data = requestData.getData();
        boolean applyFriend = friendInviteService.applyFriend(data.getUserNo(), data.getFriendUserNo(), data.getVerifyInfo());
        return getBoolean(applyFriend);
    }

    @RequestMapping(value = "/auditFriendApply", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "审核好友请求", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "inviteNo", value = "申请编码", paramType = "query"),
            @ApiImplicitParam(name = "auditState", value = "状态(同意:10150002 拒绝:10150003)", paramType = "query", defaultValue = "10150002"),
    })
    public Object auditFriendApply(@RequestBody(required = false) @Valid RequestData<AuditFriendInviteInput> requestData, BindingResult bindingResult) {
        AuditFriendInviteInput data = requestData.getData();
        boolean auditFriendApply = friendInviteService.auditFriendApply(data.getInviteNo(), data.getAuditState());
        return getBoolean(auditFriendApply);
    }

    @RequestMapping(value = "/delFriend", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "删除好友", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "inviteNo", value = "申请编码", paramType = "query"),
    })
    public Object delFriend(@RequestBody(required = false) @Valid RequestData<DelFriendInput> requestData, BindingResult bindingResult) {
        DelFriendInput data = requestData.getData();
        boolean delFriend = friendInviteService.delFriend(data.getInviteNo());
        return getBoolean(delFriend);
    }

    @RequestMapping(value = "/pageQueryFriend", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "分页查询好友列表", httpMethod = "POST", response = PageQueryFriendOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "keyWord", value = "搜索关键词", paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度", paramType = "query"),
    })
    public Object pageQueryFriend(@RequestBody(required = false) @Valid RequestData<PageQuerySearchUserNoBase> requestData, BindingResult bindingResult) {
        PageQuerySearchUserNoBase data = requestData.getData();
        List<PageQueryFriendOutput> pageQueryFriendOutputs = friendInviteService.pageQueryFriend(data.getKeyWord(), data.getUserNo(), data.getPageNo(), data.getPageSize());
        return getSuccess(pageQueryFriendOutputs);
    }

    @RequestMapping(value = "/blackList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "获取黑名单集合", httpMethod = "POST", response = PageQueryFriendOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNos", value = "用户编码集合", paramType = "query"),
    })
    public Object blackList(@RequestBody(required = false) @Valid RequestData<BlackList> requestData, BindingResult bindingResult) {
        BlackList data = requestData.getData();
        List<Map<String, String>> userBlack = data.getUserNos();
        List<BlacFriendInfo> queryBlackListOutputs = friendInviteService.queryBlackList(userBlack);
        return getSuccess(queryBlackListOutputs);
    }


    /**
     * 查询当前两个用户是否是好友
     */
    @RequestMapping(value = "/queryFriendRelation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "查询当前两个用户是否是好友", httpMethod = "POST", response = PageQueryFriendOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "当前用户编码", paramType = "query"),
            @ApiImplicitParam(name = "visitorNo", value = "是否是好友编码", paramType = "query"),
    })
    public Object queryFriendRelation(@RequestBody(required = false) @Valid RequestData<Map<String, String>> requestData, BindingResult bindingResult) {
        ResponseData responseData = new ResponseData();
        Map<String, String> data = requestData.getData();
        String userNo = data.get("userNo");
        String visitorNo = data.get("visitorNo");
        boolean isFriend = friendInviteService.isFriend(userNo, visitorNo);
        responseData.setCode(200);
        responseData.setData(isFriend);
        responseData.setMessage("好友关系");
        return responseData;
    }

}

