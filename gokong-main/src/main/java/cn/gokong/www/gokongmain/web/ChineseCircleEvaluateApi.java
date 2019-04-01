package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.vo.chinese_circle_evaluate.*;
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
 * 华人圈评论API 前端控制器
 * </p>
 *
 * @author ikook
 * @since 2018-09-18
 */
@RestController
@RequestMapping("/chineseCircleEvaluate")
@Api(tags = "华人圈评论API", description = "ChineseCircleEvaluateApi")
public class ChineseCircleEvaluateApi extends BaseController{

    @RequestMapping(value="/pageQueryCircleEvaluate",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页查询华人圈评论列表", httpMethod = "POST",response = PageQueryCircleEvaluateOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "circleNo", value = "华人圈编码", paramType = "query",defaultValue = "circle153691554694556811"),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query",defaultValue = "yh15367309978639"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public Object pageQueryCircleEvaluate(@RequestBody(required = false) @Valid RequestData<PageQueryCircleEvaluateInput> requestData, BindingResult bindingResult){
        PageQueryCircleEvaluateInput data = requestData.getData();
        List<PageQueryCircleEvaluateOutput> pageQueryCircleEvaluateOutputs = chineseCircleEvaluateService.pageQueryCircleEvaluate(data.getCircleNo(),data.getUserNo(),data.getPageNo(),data.getPageSize());
        return getSuccess(pageQueryCircleEvaluateOutputs);
    }

    @RequestMapping(value="/setEvaluateLike",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "设置评论点赞", httpMethod = "POST",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "evaluateNo", value = "评论编码", paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query",defaultValue = "yh15367309978639"),
            @ApiImplicitParam(name = "liked", value = "ture:点赞 false:取消点赞", paramType = "query",defaultValue = "true"),
    })
    public Object setEvaluateLike(@RequestBody(required = false) @Valid RequestData<SetEvaluateLikeInput> requestData, BindingResult bindingResult){
        SetEvaluateLikeInput data = requestData.getData();
        boolean setEvaluateLike = chineseCircleEvaluatePariseService.setEvaluateLike(data.getEvaluateNo(),data.getUserNo(),data.isLiked());
        return getBoolean(setEvaluateLike);
    }

    @RequestMapping(value="/releaseEvaluate",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "发布评论", httpMethod = "POST",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "circleNo", value = "华人圈编码", paramType = "query",defaultValue = "circle153691554694556811"),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query",defaultValue = "yh15367309978639"),
            @ApiImplicitParam(name = "evaluateContent", value = "评论内容", paramType = "query",defaultValue = "测试评论"),
    })
    public Object releaseEvaluate(@RequestBody(required = false) @Valid RequestData<ReleaseEvaluateInput> requestData, BindingResult bindingResult){
        ReleaseEvaluateInput data = requestData.getData();
        boolean releaseEvaluate = chineseCircleEvaluateService.releaseEvaluate(data.getCircleNo(),data.getUserNo(),data.getEvaluateContent());
        return getBoolean(releaseEvaluate);
    }

    @RequestMapping(value="/replyEvaluate",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "回复评论", httpMethod = "POST",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "evaluateNo", value = "评论编码", paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "replyContent", value = "回复内容", paramType = "query"),
    })
    public Object replyEvaluate(@RequestBody(required = false) @Valid RequestData<ReplyEvaluateInput> requestData, BindingResult bindingResult){
        ReplyEvaluateInput data = requestData.getData();
        boolean replyEvaluate = chineseCircleEvaluateService.replyEvaluate(data.getEvaluateNo(),data.getUserNo(),data.getReplyContent());
        return getBoolean(replyEvaluate);
    }
}

