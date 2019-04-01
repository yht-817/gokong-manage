package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.gokongmain.vo.base.PageQuerySearchUserNoBase;
import cn.gokong.www.gokongmain.vo.group.*;
import cn.gokong.www.gokongmain.vo.information.PageQueryInformationHomeOutput;
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

/**
 * <p>
 * 好友群用户信息 前端控制器
 * </p>
 *
 * @author ikook
 * @since 2018-09-13
 */
@RestController
@RequestMapping("/v2/friendGroupUser")
@Api(tags = "用户群(申请/邀请/审核/退出/查询群成员)", description = "UserGroupApi")
public class UserGroupApi extends BaseController {

    @RequestMapping(value="/batchDelGroupUser",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "批量删除群成员", httpMethod = "POST",response = PageQueryInformationHomeOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "群主编码",paramType = "query"),
            @ApiImplicitParam(name = "groupNo", value = "群聊编码",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "userNos", value = "用户编码列表",paramType = "query"),
    })
    public Object batchDelGroupUser(@RequestBody(required = false) @Valid RequestData<BatchDelGroupUserInput> requestData, BindingResult bindingResult){
        BatchDelGroupUserInput data = requestData.getData();
        boolean batchDelGroupUser = friendGroupUserService.batchDelGroupUser(data.getUserNo(),data.getGroupNo(),data.getUserNos());
        return getBoolean(batchDelGroupUser);
    }

    @RequestMapping(value="/inviteJoin",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "邀请加入", httpMethod = "POST",response = PageQueryInformationHomeOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "邀请方的用户编码",paramType = "query"),
            @ApiImplicitParam(name = "groupNo", value = "群聊编码",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "userNos", value = "用户编码列表",paramType = "query"),
    })
    public Object inviteJoin(@RequestBody(required = false) @Valid RequestData<InviteUserJoinGroupInput> requestData, BindingResult bindingResult){
        InviteUserJoinGroupInput data = requestData.getData();
        boolean inviteJoin = friendGroupUserService.inviteJoin(data.getUserNo(),data.getGroupNo(),data.getUserNos());
        return getBoolean(inviteJoin);
    }

    @RequestMapping(value="/applyJoin",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "申请加入", httpMethod = "POST",response = PageQueryInformationHomeOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "groupNo", value = "群聊编码",paramType = "query"),
            @ApiImplicitParam(name = "verifyInfo", value = "验证信息",paramType = "query"),
    })
    public Object applyJoin(@RequestBody(required = false) @Valid RequestData<ApplyJoinGroupInput> requestData, BindingResult bindingResult){
        ApplyJoinGroupInput data = requestData.getData();
        boolean applyJoin = friendGroupUserService.applyJoin(data.getUserNo(),data.getGroupNo(),data.getVerifyInfo());
        return getBoolean(applyJoin);
    }

    @RequestMapping(value="/pageQueryGroupUser",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页查询群成员列表", httpMethod = "POST",response = QueryGroupUserOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupNo", value = "群编码",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "content", value = "检索名称",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "开始页面",paramType = "query",defaultValue = "10"),
    })
    public Object pageQueryGroupUser(@RequestBody(required = false) @Valid RequestData<PageQueryGroupUserInput> requestData, BindingResult bindingResult){
        PageQueryGroupUserInput data = requestData.getData();
        List<QueryGroupUserOutput> queryGroupUserOutputs = friendGroupUserService.pageQueryGroupUser(data.getGroupNo(), data.getContent(), data.getPageNo(), data.getPageSize());
        return getSuccess(queryGroupUserOutputs);
    }

    @RequestMapping(value="/auditInvite",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "审核请求", httpMethod = "POST",response = QueryGroupUserOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "inviteNo", value = "申请编码",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "auditState", value = "审核状态(同意:true 拒绝:false)",paramType = "query",defaultValue = ""),
    })
    public Object auditInvite(@RequestBody(required = false) @Valid RequestData<AuditGroupInviteInput> requestData, BindingResult bindingResult){
        AuditGroupInviteInput data = requestData.getData();
        boolean auditInvite = friendGroupUserService.auditInvite(data.getInviteNo(), data.isAuditState());
        return getBoolean(auditInvite);
    }

    @RequestMapping(value="/exitGroup",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "退出群聊", httpMethod = "POST",response = PageQueryInformationHomeOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "groupNo", value = "群聊编码",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
    })
    public Object exitGroup(@RequestBody(required = false) @Valid RequestData<ExitGroupInput> requestData, BindingResult bindingResult){
        ExitGroupInput data = requestData.getData();
        boolean exitGroup = friendGroupUserService.exitGroup(data.getUserNo(),data.getGroupNo());
        return getBoolean(exitGroup);
    }

    @RequestMapping(value="/pageQueryInviteGroupUser",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页查询邀请群成员列表", httpMethod = "POST",response = PageQueryInviteGroupUserOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "搜索者编码",paramType = "query",defaultValue = "yh201807101948141454"),
            @ApiImplicitParam(name = "groupNo", value = "群编码",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "content", value = "检索内容",paramType = "query",defaultValue = "秋"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public Object pageQueryInviteGroupUser(@RequestBody(required = false) @Valid RequestData<PageQueryInviteGroupUserInput> requestData, BindingResult bindingResult){
        PageQueryInviteGroupUserInput data = requestData.getData();
        List<PageQueryInviteGroupUserOutput> pageQueryInviteGroupUserOutputs = friendGroupUserService.pageQueryInviteGroupUser(data.getGroupNo(), data.getUserNo(), data.getContent(), data.getPageNo(), data.getPageSize());
        return getSuccess(pageQueryInviteGroupUserOutputs);
    }

    @RequestMapping(value="/pageQueryUserGroup",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页获取用户群列表", httpMethod = "POST",response = PageQueryUserGroupOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query",defaultValue = "yh15367309978639"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "开始页面",paramType = "query",defaultValue = "10"),
            @ApiImplicitParam(name = "keyWord", value = "搜索关键词", paramType = "query"),
    })
    public Object pageQueryUserGroup(@RequestBody(required = false) @Valid RequestData<PageQuerySearchUserNoBase> requestData, BindingResult bindingResult){
        PageQuerySearchUserNoBase data = requestData.getData();
        List<PageQueryUserGroupOutput> pageQueryUserGroupOutputs = friendGroupUserService.pageQueryUserGroup(data.getKeyWord(),data.getUserNo(), data.getPageNo(), data.getPageSize());
        return getSuccess(pageQueryUserGroupOutputs);
    }

}

