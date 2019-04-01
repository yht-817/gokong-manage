package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.vo.merchants.PageQueryMerchantsInput;
import cn.gokong.www.gokongmain.vo.merchants.PageQueryMerchantsOutput;
import cn.gokong.www.gokongmain.vo.merchants.QueryMerchantsDetailsInput;
import cn.gokong.www.gokongmain.vo.merchants.QueryMerchantsDetailsOutput;
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
 * 商户数据 前端控制器
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
@RestController
@RequestMapping("/v2/merchants")
@Api(tags = "商户（留学移民/语言培训/金融贷款/搬家物流/理财金融）", description = "MerchantsApi")
public class MerchantsApi extends BaseController{

    @RequestMapping(value="/pageQueryStudyingAbroadMerchants",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "分页查询留学移民商户数据", httpMethod = "POST",response = PageQueryMerchantsOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = false, name = "keyWord", value = "关键字",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "cityName", value = "城市名称",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "pageNo", value = "当前页面",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "pageSize", value = "页面长度",paramType = "query",dataType = "String"),
    })
    public Object pageQueryStudyingAbroadMerchants(@RequestBody(required = false) @Valid RequestData<PageQueryMerchantsInput> requestData, BindingResult bindingResult){
        PageQueryMerchantsInput data = requestData.getData();
        List<PageQueryMerchantsOutput> pageQueryMerchants = merchantsService.pageQueryMerchants(data.getCityName(),data.getKeyWord(),data.getPageNo(), data.getPageSize(), SysCodeEnum.CODE_10450001.getCode());
        return getSuccess(pageQueryMerchants);
    }

    @RequestMapping(value="/pageQueryLanguageTrainingMerchants",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "分页查询语言培训商户数据", httpMethod = "POST",response = PageQueryMerchantsOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = false, name = "keyWord", value = "关键字",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "cityName", value = "城市名称",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "pageNo", value = "当前页面",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "pageSize", value = "页面长度",paramType = "query",dataType = "String"),
    })
    public Object pageQueryLanguageTrainingMerchants(@RequestBody(required = false) @Valid RequestData<PageQueryMerchantsInput> requestData, BindingResult bindingResult){
        PageQueryMerchantsInput data = requestData.getData();
        List<PageQueryMerchantsOutput> pageQueryMerchants = merchantsService.pageQueryMerchants(data.getCityName(),data.getKeyWord(),data.getPageNo(), data.getPageSize(), SysCodeEnum.CODE_10450002.getCode());
        return getSuccess(pageQueryMerchants);
    }

    @RequestMapping(value="/pageQueryFinancialLoansMerchants",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "分页查询金融贷款商户数据", httpMethod = "POST",response = PageQueryMerchantsOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = false, name = "keyWord", value = "关键字",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "cityName", value = "城市名称",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "pageNo", value = "当前页面",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "pageSize", value = "页面长度",paramType = "query",dataType = "String"),
    })
    public Object pageQueryFinancialLoansMerchants(@RequestBody(required = false) @Valid RequestData<PageQueryMerchantsInput> requestData, BindingResult bindingResult){
        PageQueryMerchantsInput data = requestData.getData();
        List<PageQueryMerchantsOutput> pageQueryMerchants = merchantsService.pageQueryMerchants(data.getCityName(),data.getKeyWord(),data.getPageNo(), data.getPageSize(), SysCodeEnum.CODE_10450003.getCode());
        return getSuccess(pageQueryMerchants);
    }

    @RequestMapping(value="/pageQueryMoveTheLogisticsMerchants",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "分页查询搬家物流商户数据", httpMethod = "POST",response = PageQueryMerchantsOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = false, name = "keyWord", value = "关键字",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "cityName", value = "城市名称",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "pageNo", value = "当前页面",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "pageSize", value = "页面长度",paramType = "query",dataType = "String"),
    })
    public Object pageQueryMoveTheLogisticsMerchants(@RequestBody(required = false) @Valid RequestData<PageQueryMerchantsInput> requestData, BindingResult bindingResult){
        PageQueryMerchantsInput data = requestData.getData();
        List<PageQueryMerchantsOutput> pageQueryMerchants = merchantsService.pageQueryMerchants(data.getCityName(),data.getKeyWord(),data.getPageNo(), data.getPageSize(), SysCodeEnum.CODE_10450004.getCode());
        return getSuccess(pageQueryMerchants);
    }

    @RequestMapping(value="/pageQueryTravelTicketMerchants",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "分页查询理机票旅行数据", httpMethod = "POST",response = PageQueryMerchantsOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = false, name = "keyWord", value = "关键字",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "cityName", value = "城市名称",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "pageNo", value = "当前页面",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "pageSize", value = "页面长度",paramType = "query",dataType = "String"),
    })
    public Object pageQueryTravelTicketMerchants(@RequestBody(required = false) @Valid RequestData<PageQueryMerchantsInput> requestData, BindingResult bindingResult){
        PageQueryMerchantsInput data = requestData.getData();
        List<PageQueryMerchantsOutput> pageQueryMerchants = merchantsService.pageQueryMerchants(data.getCityName(),data.getKeyWord(),data.getPageNo(), data.getPageSize(), SysCodeEnum.CODE_10450005.getCode());
        return getSuccess(pageQueryMerchants);
    }



    @RequestMapping(value="/queryMerchantsDetails",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "获取商户详情", httpMethod = "POST",response = QueryMerchantsDetailsOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "merchantsNo", value = "商户编码",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码",paramType = "query",dataType = "String"),
    })
    public Object queryMerchantsDetails(@RequestBody(required = false) @Valid RequestData<QueryMerchantsDetailsInput> requestData, BindingResult bindingResult){
        QueryMerchantsDetailsInput data = requestData.getData();
        QueryMerchantsDetailsOutput getMerchantsDetails = merchantsService.queryMerchantsDetails(data.getUserNo(), data.getMerchantsNo());
        return getSuccess(getMerchantsDetails);
    }

}

