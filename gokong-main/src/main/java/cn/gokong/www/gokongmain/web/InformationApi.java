package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.vo.base.InformationNoBase;
import cn.gokong.www.gokongmain.vo.base.PageQuerySearchBase;
import cn.gokong.www.gokongmain.vo.base.PageQuerySearchUserNoBase;
import cn.gokong.www.gokongmain.vo.information.*;
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
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 资讯信息表 前端控制器
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@RestController
@RequestMapping("/v2/information")
@Api(tags = "资讯信息", description = "前端控制器")
public class InformationApi extends BaseController {


    @RequestMapping(value = "/pageQueryHomeInformation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "分页查询推荐资讯列表", httpMethod = "POST", response = PageQueryInformationHomeOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyWord", value = "搜索关键词", paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度", paramType = "query", defaultValue = "10"),
    })
    public Object pageQueryHomeInformation(@RequestBody(required = false) @Valid RequestData<PageQuerySearchBase> requestData, BindingResult bindingResult) {
        PageQuerySearchBase data = requestData.getData();
        List<PageQueryInformationHomeOutput> pageQueryChineseCircleOutputs = informationService.pageQueryHomeInformation(data.getKeyWord(), data.getPageNo(), data.getPageSize());
        return getSuccess(pageQueryChineseCircleOutputs);
    }

    @RequestMapping(value = "/pageQueryHomeFansInformation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "分页查询我关注资讯列表", httpMethod = "POST", response = PageQueryInformationHomeOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "keyWord", value = "搜索关键词", paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页", paramType = "query", defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度", paramType = "query", defaultValue = "10"),
    })
    public Object pageQueryHomeFansInformation(@RequestBody(required = false) @Valid RequestData<PageQuerySearchUserNoBase> requestData, BindingResult bindingResult) {
        PageQuerySearchUserNoBase data = requestData.getData();
        List<PageQueryInformationHomeOutput> pageQueryChineseCircleOutputs = informationService.pageQueryHomeFansInformation(data.getKeyWord(), data.getUserNo(), data.getPageNo(), data.getPageSize());
        return getSuccess(pageQueryChineseCircleOutputs);
    }

    @RequestMapping(value = "/queryInformationDetails", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "查询资讯详情", httpMethod = "POST", response = QueryInformationDetailsOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "informationNo", value = "搜索关键词", paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "当前页", paramType = "query", defaultValue = "1"),
    })
    public Object queryInformationDetails(@RequestBody(required = false) @Valid RequestData<InformationDetailsInput> requestData, BindingResult bindingResult) {
        InformationDetailsInput data = requestData.getData();
        QueryInformationDetailsOutput queryInformationDetailsOutput = informationService.queryInformationDetails(data.getUserNo(), data.getInformationNo());
        return getSuccess(queryInformationDetailsOutput);
    }

    @RequestMapping(value = "/releaseInformation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "发布资讯", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "informationMode", value = "资讯模式(10230001:原创 10230002:转载)", paramType = "query", defaultValue = "10230002"),
            @ApiImplicitParam(name = "userNo", value = "发布人ID", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "informationName", value = "资讯标题", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "informationPhoto", value = "资讯图片(10400001:矩形图片 10400002:方形图片)", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "photoSizeType", value = "图片尺寸类型", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "informationContent", value = "资讯内容", paramType = "query", defaultValue = ""),
    })
    public Object releaseInformation(@RequestBody(required = false) @Valid RequestData<ReleaseInformationInput> requestData, BindingResult bindingResult) throws IOException {
        ReleaseInformationInput data = requestData.getData();
        Map<String, String> releaseInformation = informationService.releaseInformation(data.getUserNo(), data.getInformationMode(), data.getInformationName(), data.getInformationPhoto(), data.getInformationContent(), data.getPhotoSizeType());
        return getSuccess(releaseInformation);
    }

    @RequestMapping(value = "/delInformation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "删除资讯", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "informationNo", value = "搜索关键词", paramType = "query"),
    })
    public Object delInformation(@RequestBody(required = false) @Valid RequestData<InformationNoBase> requestData, BindingResult bindingResult) throws IOException {
        InformationNoBase data = requestData.getData();
        boolean delInformation = informationService.delInformation(data.getInformationNo());
        return getBoolean(delInformation);
    }

    @RequestMapping(value = "/queryArticleContent", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "获取URL链接内容", httpMethod = "POST", response = QueryArticleContentOuput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "地址链接", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "类型(1:微信文章 2:本地文章)", paramType = "query"),
    })
    public Object queryArticleContent(@RequestBody(required = false) @Valid RequestData<QueryArticleContentInput> requestData, BindingResult bindingResult) throws IOException {
        QueryArticleContentInput data = requestData.getData();
        QueryArticleContentOuput queryArticleContentOuput = informationService.queryArticleContent(data.getUrl(), data.getType());
        return getSuccess(queryArticleContentOuput);
    }

    @RequestMapping(value = "/updateInformation", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "修改资讯", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "informationNo", value = "资讯编码", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "informationMode", value = "资讯模式(10230001:原创 10230002:转载)", paramType = "query", defaultValue = "10230002"),
            @ApiImplicitParam(name = "informationName", value = "资讯标题", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "informationPhoto", value = "资讯图片", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "photoSizeType", value = "图片尺寸类型(10400001:矩形图片 10400002:方形图片)", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "informationContent", value = "资讯内容", paramType = "query", defaultValue = ""),
    })
    public Object updateInformation(@RequestBody(required = false) @Valid RequestData<UpdateInformationInput> requestData, BindingResult bindingResult) throws IOException {
        UpdateInformationInput data = requestData.getData();
        Map<String, String> updateInformation = informationService.updateInformation(data.getInformationNo(), data.getInformationMode(), data.getInformationName(), data.getInformationPhoto(), data.getInformationContent(), data.getPhotoSizeType());
        return getSuccess(updateInformation);
    }


    /**
     * 资讯直接用链接发布
     */
    @RequestMapping(value = "/queryArticleContentS", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "资讯直接用链接发布", httpMethod = "POST", response = QueryArticleContentOuput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "地址链接", paramType = "query"),
            @ApiImplicitParam(name = "type", value = "类型(1:微信文章 2:本地文章)", paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
    })
    public Object queryArticleContentS(@RequestBody(required = false) @Valid RequestData<QueryArticleContentInputS> requestData, BindingResult bindingResult) throws IOException {
        QueryArticleContentInputS data = requestData.getData();
        QueryArticleContentOuput queryArticleContentOuput = informationService.queryArticleContent(data.getUrl(), null);
        // 然后直接进行发布处理
        Map<String, String> releaseInformation = informationService.releaseInformation(data.getUserNo(), "10230002", queryArticleContentOuput.getTitle(), queryArticleContentOuput.getTitleImg(), queryArticleContentOuput.getContent(), "10400001");
        // 转载成功获得12个悟空比
        userAccountService.addUserAccount(data.getUserNo(), new BigDecimal(6));
        // 添加用户增加
        userAccountLogService.inserlog("10050010", new BigDecimal(6), releaseInformation.get("informationNo"), data.getUserNo(), "转载收益");
        return getSuccess(releaseInformation);
    }
}

