package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.vo.base.PageQueryUserNoBase;
import cn.gokong.www.gokongmain.vo.chinese_circle.PageQueryChineseCircleOutput;
import cn.gokong.www.gokongmain.vo.chioce.PageQueryChoiceOutput;
import cn.gokong.www.gokongmain.vo.merchants.PageQueryMerchantsOutput;
import cn.gokong.www.gokongmain.vo.used_deal.PageQueryListOutput;
import cn.gokong.www.gokongmain.vo.user_collection.PageQueryInformationCollectionOutput;
import cn.gokong.www.gokongmain.vo.user_collection.SetUserCollectionInput;
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
 * 收藏信息表 前端控制器
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
@RestController
@RequestMapping("/v2/collectionInfo")
@Api(tags = "用户收藏信息", description = "前端控制器")
public class UserCollectionApi extends BaseController {

    @RequestMapping(value="/pageQueryInformationCollection",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页获取资讯收藏", httpMethod = "POST",response = PageQueryInformationCollectionOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public Object pageQueryInformationCollection(@RequestBody(required = false) @Valid RequestData<PageQueryUserNoBase> requestData, BindingResult bindingResult){
        PageQueryUserNoBase data = requestData.getData();
        List<PageQueryInformationCollectionOutput> pageQueryInformationCollectionOutputs = collectionInfoService.pageQueryInformationCollection(data.getUserNo(), data.getPageNo(), data.getPageSize());
        return getSuccess(pageQueryInformationCollectionOutputs);
    }

    @RequestMapping(value="/pageQueryChoiceCollection",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页获取精选收藏", httpMethod = "POST",response = PageQueryChoiceOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public Object pageQueryChoiceCollection(@RequestBody(required = false) @Valid RequestData<PageQueryUserNoBase> requestData, BindingResult bindingResult){
        PageQueryUserNoBase data = requestData.getData();
        List<PageQueryChoiceOutput> pageQueryChoiceCollection = collectionInfoService.pageQueryChoiceCollection(data.getUserNo(), data.getPageNo(), data.getPageSize());
        return getSuccess(pageQueryChoiceCollection);
    }

    @RequestMapping(value="/pageQueryMerchantsCollection",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页获取商户收藏", httpMethod = "POST",response = PageQueryMerchantsOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public Object pageQueryMerchantsCollection(@RequestBody(required = false) @Valid RequestData<PageQueryUserNoBase> requestData, BindingResult bindingResult){
        PageQueryUserNoBase data = requestData.getData();
        List<PageQueryMerchantsOutput> pageQueryMerchantsCollection = collectionInfoService.pageQueryMerchantsCollection(data.getUserNo(), data.getPageNo(), data.getPageSize());
        return getSuccess(pageQueryMerchantsCollection);
    }

    @RequestMapping(value="/pageQueryChineseCircleCollection",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页获取华人圈收藏", httpMethod = "POST",response = PageQueryChineseCircleOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public Object pageQueryChineseCircleCollection(@RequestBody(required = false) @Valid RequestData<PageQueryUserNoBase> requestData, BindingResult bindingResult){
        PageQueryUserNoBase data = requestData.getData();
        List<PageQueryChineseCircleOutput> pageQueryChineseCircleOutputs = collectionInfoService.pageQueryChineseCircleCollection(data.getUserNo(), data.getPageNo(), data.getPageSize());
        return getSuccess(pageQueryChineseCircleOutputs);
    }

    @RequestMapping(value="/pageQueryUsedDealCollection",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页获取二手交易收藏", httpMethod = "POST",response = PageQueryListOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public Object pageQueryUsedDealCollection(@RequestBody(required = false) @Valid RequestData<PageQueryUserNoBase> requestData, BindingResult bindingResult){
        PageQueryUserNoBase data = requestData.getData();
        List<PageQueryListOutput> pageQueryListOutputs = collectionInfoService.pageQueryUsedDealCollection(data.getUserNo(), data.getPageNo(), data.getPageSize());
        return getSuccess(pageQueryListOutputs);
    }

    @RequestMapping(value="/pageQueryCompanyRecruitmentCollection",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页获取招聘收藏", httpMethod = "POST",response = cn.gokong.www.gokongmain.vo.company_recruitment.PageQueryListOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public Object pageQueryCompanyRecruitmentCollection(@RequestBody(required = false) @Valid RequestData<PageQueryUserNoBase> requestData, BindingResult bindingResult){
        PageQueryUserNoBase data = requestData.getData();
        List<cn.gokong.www.gokongmain.vo.company_recruitment.PageQueryListOutput> pageQueryListOutputs = collectionInfoService.pageQueryCompanyRecruitmentCollection(data.getUserNo(), data.getPageNo(), data.getPageSize());
        return getSuccess(pageQueryListOutputs);
    }

    @RequestMapping(value="/setUserCollection",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "设置用户收藏", httpMethod = "POST",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "contentNo", value = "内容编码(资讯编码、精选编码 商户编码)",paramType = "query"),
            @ApiImplicitParam(name = "collectionType", value = "收藏类型 资讯收藏:10110001 精选收藏:10110002 商户收藏:10110003 华人圈收藏：10110004 二手收藏：10110005 招聘收藏：10110006",paramType = "query"),
            @ApiImplicitParam(name = "collection", value = "类型：收藏：true，取消：false",paramType = "query"),
    })
    public Object setUserCollection(@RequestBody(required = false) @Valid RequestData<SetUserCollectionInput> requestData, BindingResult bindingResult){
        SetUserCollectionInput data = requestData.getData();
        boolean setUserCollection = collectionInfoService.setUserCollection(data.getUserNo(), data.getContentNo(), data.getCollectionType(), data.isCollection());
        return setUserCollection?getSuccess():getFail();
    }

}

