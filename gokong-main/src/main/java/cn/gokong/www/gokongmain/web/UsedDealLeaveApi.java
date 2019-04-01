package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.vo.used_deal_leave.PageQueryListInput;
import cn.gokong.www.gokongmain.vo.used_deal_leave.PageQueryListOutput;
import cn.gokong.www.gokongmain.vo.used_deal_leave.ReleaseLeaveInput;
import cn.gokong.www.gokongmain.vo.used_deal_leave.ReplyLeaveInput;
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
 * 二手交易留言 前端控制器
 * </p>
 *
 * @author ikook
 * @since 2018-10-01
 */
@RestController
@RequestMapping("/v2/usedDealLeave")
@Api(tags = "二手交易留言", description = "前端控制器")
public class UsedDealLeaveApi extends BaseController{

    @RequestMapping(value = "/releaseLeave", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "发布留言", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "usedNo", value = "二手交易编码",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "leaveContent", value = "留言内容",paramType = "query",dataType = "String"),

    })
    public Object releaseLeave(@RequestBody(required = false) @Valid RequestData<ReleaseLeaveInput> requestData, BindingResult bindingResult) {
        ReleaseLeaveInput data = requestData.getData();
        boolean releaseLeave = usedDealLeaveService.releaseLeave(data.getUserNo(),data.getUsedNo(),data.getLeaveContent());
        return getBoolean(releaseLeave);
    }

    @RequestMapping(value = "/replyLeave", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "回复留言", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "leaveNo", value = "留言编码",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "leaveContent", value = "留言内容",paramType = "query",dataType = "String"),

    })
    public Object replyLeave(@RequestBody(required = false) @Valid RequestData<ReplyLeaveInput> requestData, BindingResult bindingResult) {
        ReplyLeaveInput data = requestData.getData();
        boolean replyLeave = usedDealLeaveService.replyLeave(data.getUserNo(),data.getLeaveNo(),data.getLeaveContent());
        return getBoolean(replyLeave);
    }

    @RequestMapping(value = "/pageQueryList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "分页查询留言列表", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "usedNo", value = "二手交易编码",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "pageNo", value = "当前页",paramType = "query",dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(required = true, name = "pageSize", value = "页面长度",paramType = "query",dataType = "String",defaultValue = "10"),

    })
    public Object pageQueryList(@RequestBody(required = false) @Valid RequestData<PageQueryListInput> requestData) {
        PageQueryListInput data = requestData.getData();
        List<PageQueryListOutput> pageQueryListOutputs = usedDealLeaveService.pageQueryList(data.getUsedNo(),data.getPageNo(),data.getPageSize());
        return getSuccess(pageQueryListOutputs);
    }
}

