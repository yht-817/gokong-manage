package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.vo.Information_evaluate.*;
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
 * 资讯信息表评价记录 前端控制器
 * </p>
 *
 * @author ikook
 * @since 2018-09-15
 */
@RestController
@RequestMapping("/v2/informationEvaluate")
@Api(tags = "资讯评论", description = "前端控制器")
public class InformationEvaluateApi extends BaseController{

    @RequestMapping(value="/pageQueryInformationEvaluate",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页查询资讯评论列表", httpMethod = "POST",response = PageQueryInformationEvaluateOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "informationNo", value = "资讯编码", paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public Object pageQueryInformationEvaluate(@RequestBody(required = false) @Valid RequestData<PageQueryInformationEvaluateInput> requestData, BindingResult bindingResult){
        PageQueryInformationEvaluateInput data = requestData.getData();
        List<PageQueryInformationEvaluateOutput> pageQueryInformationEvaluateOutputs = informationEvaluateService.pageQueryInformationEvaluate(data.getInformationNo(),data.getUserNo(),data.getPageNo(),data.getPageSize());
        return getSuccess(pageQueryInformationEvaluateOutputs);
    }

    @RequestMapping(value="/queryOneEvaluate",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "查询一条评论", httpMethod = "POST",response = PageQueryInformationEvaluateOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "evaluateNo", value = "评论编码", paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
    })
    public Object queryOneEvaluate(@RequestBody(required = false) @Valid RequestData<QueryOneEvaluateInput> requestData, BindingResult bindingResult){
        QueryOneEvaluateInput data = requestData.getData();
        PageQueryInformationEvaluateOutput pageQueryInformationEvaluateOutput = informationEvaluateService.queryOneEvaluate(data.getEvaluateNo(),data.getUserNo());
        return getSuccess(pageQueryInformationEvaluateOutput);
    }

    @RequestMapping(value="/pageQueryInformationChildEvaluate",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页查询资讯子评论列表", httpMethod = "POST",response = PageQueryInformationChildEvaluateOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "evaluateNo", value = "评论编码", paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public Object pageQueryInformationChildEvaluate(@RequestBody(required = false) @Valid RequestData<PageQueryInformationChildEvaluateInput> requestData, BindingResult bindingResult){
        PageQueryInformationChildEvaluateInput data = requestData.getData();
        List<PageQueryInformationChildEvaluateOutput> pageQueryInformationChildEvaluateOutputs = informationEvaluateReplyService.pageQueryInformationChildEvaluate(data.getEvaluateNo(),data.getUserNo(),data.getPageNo(),data.getPageSize());
        return getSuccess(pageQueryInformationChildEvaluateOutputs);
    }

    @RequestMapping(value="/setEvaluateLike",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "设置评论点赞", httpMethod = "POST",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "informationNo", value = "资讯编码", paramType = "query"),
            @ApiImplicitParam(name = "evaluateNo", value = "评论编码", paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "liked", value = "ture:点赞 false:取消点赞", paramType = "query"),
    })
    public Object setEvaluateLike(@RequestBody(required = false) @Valid RequestData<SetEvaluateLikeInput> requestData, BindingResult bindingResult){
        SetEvaluateLikeInput data = requestData.getData();
        boolean setEvaluateLike = informationEvaluatePariseService.setEvaluateLike(data.getInformationNo(),data.getEvaluateNo(),data.getUserNo(),data.isLiked());
        return getBoolean(setEvaluateLike);
    }

    @RequestMapping(value="/releaseEvaluate",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "发布评论", httpMethod = "POST",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "informationNo", value = "资讯编码", paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "evaluateContent", value = "评论内容", paramType = "query"),
    })
    public Object releaseEvaluate(@RequestBody(required = false) @Valid RequestData<ReleaseEvaluateInput> requestData, BindingResult bindingResult){
        ReleaseEvaluateInput data = requestData.getData();
        boolean releaseEvaluate = informationEvaluateService.releaseEvaluate(data.getInformationNo(),data.getUserNo(),data.getEvaluateContent());
        return getBoolean(releaseEvaluate);
    }

    @RequestMapping(value="/replyEvaluate",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "回复评论", httpMethod = "POST",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "informationNo", value = "资讯编码", paramType = "query"),
            @ApiImplicitParam(name = "evaluateNo", value = "评论编码", paramType = "query"),
            @ApiImplicitParam(name = "fromUserNo", value = "回复用户编码", paramType = "query"),
            @ApiImplicitParam(name = "replyContent", value = "回复内容", paramType = "query"),
            @ApiImplicitParam(name = "toUserNo", value = "被回复用户编码", paramType = "query"),
    })
    public Object replyEvaluate(@RequestBody(required = false) @Valid RequestData<ReplyEvaluateInput> requestData, BindingResult bindingResult){
        ReplyEvaluateInput data = requestData.getData();
        boolean replyEvaluate = informationEvaluateReplyService.replyEvaluate(data.getInformationNo(),data.getEvaluateNo(),data.getFromUserNo(),data.getReplyContent(),data.getToUserNo());
        return getBoolean(replyEvaluate);
    }
}

