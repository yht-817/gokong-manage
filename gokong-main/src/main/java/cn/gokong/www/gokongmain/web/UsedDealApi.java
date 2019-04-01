package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.vo.used_deal.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

/**
 * <p>
 * 二手交易 前端控制器
 * </p>
 *
 * @author ikook
 * @since 2018-10-01
 */
@RestController
@RequestMapping("/v2/usedDeal")
@Api(tags = "二手交易", description = "前端控制器")
public class UsedDealApi extends BaseController{
    @RequestMapping(value = "/queryUsedType", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "查询二手交易分类", httpMethod = "POST", response = QueryUsedTypeOutput.class)
    public Object queryUsedType(@RequestBody(required = false) @Valid RequestData requestData) {
        List<QueryUsedTypeOutput> queryUsedTypeOutputs = usedDealTypeService.queryUsedType();
        return getSuccess(queryUsedTypeOutputs);
    }

    @RequestMapping(value = "/releaseUsedDeal", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "发布二手交易", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "发布者编码",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "usedTitle", value = "标题",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "usedContent", value = "内容",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "usedImg", value = "图片[]",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "typeNo", value = "类型编码",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "cityName", value = "发布城市名称",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "price", value = "价格",paramType = "query",dataType = "String"),
    })
    public Object releaseUsedDeal(String userNo,String usedTitle, String usedContent, MultipartFile[] usedImg, String typeNo, String cityName, BigDecimal price) {
        String usedNo = usedDealService.releaseUsedDeal(userNo, usedTitle, usedContent, usedImg, typeNo, cityName, price);
        return getSuccess(usedNo);
    }

    @RequestMapping(value = "/pageQueryList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "分页查询二手交易列表", httpMethod = "POST", response = PageQueryListOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = false, name = "keyWord", value = "关键字",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = false, name = "cityName", value = "城市名称",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = false, name = "typeNo", value = "类型编码",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "pageNo", value = "当前页面",paramType = "query",dataType = "String",defaultValue = "1"),
            @ApiImplicitParam(required = true, name = "pageSize", value = "页面长度",paramType = "query",dataType = "String",defaultValue = "10"),

    })
    public Object pageQueryList(@RequestBody(required = false) @Valid RequestData<PageQueryListInput> requestData) {
        PageQueryListInput data = requestData.getData();
        List<PageQueryListOutput> pageQueryListOutputs = usedDealService.pageQueryList(data.getCityName(),data.getTypeNo(),data.getKeyWord(),data.getPageNo(),data.getPageSize());
        return getSuccess(pageQueryListOutputs);
    }

    @RequestMapping(value = "/queryDetails", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "查询二手交易详情", httpMethod = "POST", response = QueryDetailsOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "usedNo", value = "二手交易编码",paramType = "query",dataType = "String"),

    })
    public Object queryDetails(@RequestBody(required = false) @Valid RequestData<QueryDetailsInput> requestData) {
        QueryDetailsInput data = requestData.getData();
        QueryDetailsOutput queryDetailsOutput = usedDealService.queryDetails(data.getUserNo(),data.getUsedNo());
        return getSuccess(queryDetailsOutput);
    }

    @RequestMapping(value = "/del", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "删除二手交易信息", httpMethod = "POST", response = QueryDetailsOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "usedNo", value = "二手交易编码",paramType = "query",dataType = "String"),

    })
    public Object del(@RequestBody(required = false) @Valid RequestData<QueryDetailsInput> requestData) {
        QueryDetailsInput data = requestData.getData();
        boolean del = usedDealService.del(data.getUserNo(),data.getUsedNo());
        return getBoolean(del);
    }
}

