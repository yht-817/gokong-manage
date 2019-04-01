package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.gokongmain.vo.merchants.PageQueryMerchantsOutput;
import cn.gokong.www.gokongmain.vo.merchants_evaluate.PageQueryListInput;
import cn.gokong.www.gokongmain.vo.merchants_evaluate.PageQueryListOutput;
import cn.gokong.www.gokongmain.vo.merchants_evaluate.ReleaseMerchantsEvaluateInput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 商户评价表 前端控制器
 * </p>
 *
 * @author ikook
 * @since 2018-09-14
 */
@RestController
@RequestMapping("/v2/merchantsEvaluate")
@Api(tags = "商户评价（发布评价）", description = "MerchantsApi")
public class MerchantsEvaluateApi extends BaseController{

    @RequestMapping(value="/releaseMerchantsEvaluate",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "发布评价", httpMethod = "POST",response = PageQueryMerchantsOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "merchantsNo", value = "商户编码",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "evaluateContent", value = "评价内容",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "evaluateScore", value = "评分",paramType = "query",dataType = "String"),
    })
    public Object releaseMerchantsEvaluate(@RequestBody(required = false) @Valid RequestData<ReleaseMerchantsEvaluateInput> requestData, BindingResult bindingResult){
        ReleaseMerchantsEvaluateInput data = requestData.getData();
        boolean releaseMerchantsEvaluate = merchantsEvaluateService.releaseMerchantsEvaluate(data.getUserNo(), data.getMerchantsNo(),data.getEvaluateContent(),data.getEvaluateScore());
        return getBoolean(releaseMerchantsEvaluate);
    }

    @RequestMapping(value="/pageQueryList",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "分页查询评论列表", httpMethod = "POST",response = PageQueryMerchantsOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "merchantsNo", value = "商户编码",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "pageNo", value = "当前页",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "pageSize", value = "页面长度",paramType = "query",dataType = "String"),
    })
    public Object pageQueryList(@RequestBody(required = false) @Valid RequestData<PageQueryListInput> requestData, BindingResult bindingResult){
        PageQueryListInput data = requestData.getData();
        List<PageQueryListOutput> pageQueryListOutputs =  merchantsEvaluateService.pageQueryList(data.getMerchantsNo(),data.getPageNo(),data.getPageSize());
        return getSuccess(pageQueryListOutputs);
    }
}

