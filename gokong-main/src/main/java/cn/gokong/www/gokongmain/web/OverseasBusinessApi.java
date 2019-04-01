package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.vo.base.PageQuerySearchBase;
import cn.gokong.www.gokongmain.vo.overseas_business.JoinInput;
import cn.gokong.www.gokongmain.vo.overseas_business.PageQueryListOutput;
import cn.gokong.www.gokongmain.vo.overseas_business.QueryDetailsInput;
import cn.gokong.www.gokongmain.vo.overseas_business.QueryDetailsOutput;
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
 * 海外创业 前端控制器
 * </p>
 *
 * @author ikook
 * @since 2018-09-25
 */
@RestController
@RequestMapping("/v2/overseasBusiness")
@Api(tags = "海外创业", description = "OverseasBusinessApi")
public class OverseasBusinessApi extends BaseController{

    @RequestMapping(value="/pageQueryList",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页查询列表", httpMethod = "POST",response = PageQueryListOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyWord", value = "检索内容",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public Object pageQueryServiceMessage(@RequestBody(required = false) @Valid RequestData<PageQuerySearchBase> requestData, BindingResult bindingResult){
        PageQuerySearchBase data = requestData.getData();
        List<PageQueryListOutput> pageQueryListOutputs = overseasBusinessService.pageQueryList(data.getKeyWord(),data.getPageNo(),data.getPageSize());
        return getSuccess(pageQueryListOutputs);
    }

    @RequestMapping(value="/queryDetails",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "查询详情", httpMethod = "POST",response = QueryDetailsOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "businessNo", value = "创业编码",paramType = "query",defaultValue = ""),
    })
    public Object queryDetails(@RequestBody(required = false) @Valid RequestData<QueryDetailsInput> requestData, BindingResult bindingResult){
        QueryDetailsInput data = requestData.getData();
        QueryDetailsOutput queryDetails = overseasBusinessService.queryDetails(data.getUserNo(),data.getBusinessNo());
        return getSuccess(queryDetails);
    }

    @RequestMapping(value="/join",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "参加海外创业", httpMethod = "POST",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "businessNo", value = "创业编码",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "name", value = "姓名",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "phone", value = "联系电话",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "city", value = "加盟城市",paramType = "query",defaultValue = ""),
    })
    public Object join(@RequestBody(required = false) @Valid RequestData<JoinInput> requestData, BindingResult bindingResult){
        JoinInput data = requestData.getData();
        boolean join = overseasBusinessApplyService.join(data.getUserNo(),data.getBusinessNo(),data.getName(),data.getPhone(),data.getCity());
        return getBoolean(join);
    }
}

