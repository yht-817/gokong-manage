package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.vo.local_activity_apply.AuditApplyInput;
import cn.gokong.www.gokongmain.vo.local_activity_apply.PageQueryActivityMemberInput;
import cn.gokong.www.gokongmain.vo.local_activity_apply.PageQueryActivityMemberOutput;
import cn.gokong.www.gokongmain.vo.local_activity_apply.UserApplyInput;
import cn.hutool.log.StaticLog;
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
 * 同城活动用户申请表 前端控制器
 * </p>
 *
 * @author ikook
 * @since 2018-10-05
 */
@RestController
@RequestMapping("/v2/localActivityApply")
@Api(tags = "同城活动申请管理", description = "LocalActivityApplyApi")
public class LocalActivityApplyApi extends BaseController {

    @RequestMapping(value = "/userApply", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "活动报名", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityNo", value = "活动编码", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "phone", value = "联系电话", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "introduce", value = "自我介绍", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "sex", value = "性别（男 女）", paramType = "query", defaultValue = ""),
    })
    public Object userApply(@RequestBody(required = false) @Valid RequestData<UserApplyInput> requestData, BindingResult bindingResult) {
        UserApplyInput data = requestData.getData();
        String userApply = localActivityApplyService.userApply(data.getActivityNo(), data.getUserNo(), data.getPhone(), data.getIntroduce(), data.getSex());
        return getSuccess(userApply);
    }

    @RequestMapping(value = "/auditApply", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "审核申请", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyNo", value = "申请编码", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "auditState", value = "审核状态", paramType = "query", defaultValue = ""),
    })
    public Object auditApply(@RequestBody(required = false) @Valid RequestData<AuditApplyInput> requestData, BindingResult bindingResult) {
        AuditApplyInput data = requestData.getData();
        boolean auditApply = localActivityApplyService.auditApply(data.getApplyNo(), data.getUserNo(), data.isAuditState());
        return getBoolean(auditApply);
    }

    @RequestMapping(value = "/pageQueryActivityMember", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "分页查询活动成员列表", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "activityNo", value = "活动编码", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "pageNo", value = "当前页", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "pageSize", value = "页面长度", paramType = "query", defaultValue = ""),
    })
    public Object pageQueryActivityMember(@RequestBody(required = false) @Valid RequestData<PageQueryActivityMemberInput> requestData, BindingResult bindingResult) {
        PageQueryActivityMemberInput data = requestData.getData();
        List<PageQueryActivityMemberOutput> pageQueryActivityMemberOutputs = localActivityApplyService.pageQueryActivityMember(data.getActivityNo(), data.getPageNo(), data.getPageSize());
        return getSuccess(pageQueryActivityMemberOutputs);

    }

    @RequestMapping(value = "/deleteActivityApply", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "删除支付（活动）申请", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyNo", value = "申请活动编码", paramType = "query", defaultValue = ""),
    })
    public Object deleteActivityApply(@RequestBody(required = false) @Valid RequestData<Map<String, String>> requestData, BindingResult bindingResult) {
        ResponseData responseData = new ResponseData();
        String applyNo = requestData.getData().get("applyNo");
        int deleteactivityapply = localActivityApplyService.deleteActivityApply(applyNo);
        StaticLog.info("删除活动申请的条数：{}" + deleteactivityapply + "活动申请编码：" + applyNo);
        responseData.setMessage("删除信息成功");
        responseData.setCode(200);
        return responseData;

    }
}

