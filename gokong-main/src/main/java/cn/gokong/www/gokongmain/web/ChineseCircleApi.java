package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.vo.chinese_circle.*;
import cn.gokong.www.gokongmain.vo.city.HotCircle;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 华人圈 前端控制器
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@RestController
@RequestMapping("/v2/chineseCircle")
@Api(tags = "华人圈", description = "ChineseCircleApi")
public class ChineseCircleApi extends BaseController {

    @RequestMapping(value = "/releaseCircle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "发布华人圈", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "lat", value = "纬度", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "lng", value = "经度", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "cityName", value = "城市名", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "instructions", value = "发布内容", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "img", value = "发布图片", paramType = "query", defaultValue = ""),

    })
    public Object releaseCircle(@RequestParam("img") MultipartFile img, @RequestParam String userNo, @RequestParam String cityName, @RequestParam String instructions, @RequestParam String lat, @RequestParam String lng) throws IOException {
        ResponseData responseData = new ResponseData();
        String releaseCircle = chineseCirclevice.releaseCircle(userNo, instructions, img, cityName, lat, lng);
        if (releaseCircle != null) {
            responseData.setCode(200);
            responseData.setData(releaseCircle);
            responseData.setMessage("发布成功");
            return responseData;
        }
        responseData.setCode(500);
        responseData.setMessage("发布失败");
        return responseData;
    }

    @RequestMapping(value = "/setCircleLike", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "华人圈点赞/取消点赞", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "circleNo", value = "华人圈编码", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "like", value = "点赞：true 取消：false", paramType = "query", defaultValue = ""),
    })
    public Object setCircleLike(@RequestBody(required = false) @Valid RequestData<SetCircleLikeInput> requestData, BindingResult bindingResult) {
        SetCircleLikeInput data = requestData.getData();
        boolean setCircleLike = chineseCirclePraiseService.setCircleLike(data.getUserNo(), data.getCircleNo(), data.isLike());
        return getBoolean(setCircleLike);
    }

    @RequestMapping(value = "/pageQueryCircleDetails", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "分页查询华人圈详情", httpMethod = "POST", response = PageQueryCircleDetailsOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "circleNo", value = "华人圈编码", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "pageNo", value = "当前页", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度", paramType = "query"),
    })
    public Object pageQueryCircleDetails(@RequestBody(required = false) @Valid RequestData<PageQueryCircleDetailsInput> requestData, BindingResult bindingResult) {
        PageQueryCircleDetailsInput data = requestData.getData();
        PageQueryCircleDetailsOutput pageQueryCircleDetails = chineseCirclevice.pageQueryCircleDetails(data.getUserNo(), data.getCircleNo(), data.getPageNo(), data.getPageSize());
        return getSuccess(pageQueryCircleDetails);
    }

    /**
     * 举报华人圈
     */
    @RequestMapping(value = "/reportCircleDetails", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "举报华人圈信息", httpMethod = "POST", response = PageQueryCircleDetailsOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "circleNo", value = "华人圈编码", paramType = "query"),
    })
    public Object reportCircleDetails(@RequestBody(required = false) @Valid RequestData<Map<String, String>> requestData) {
        Map<String, String> data = requestData.getData();
        return chineseCirclevice.reportCircleDetails(data.get("userNo"), data.get("circleNo"));
    }

    @RequestMapping(value = "/delCircle", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "删除华人圈", httpMethod = "POST", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "circleNo", value = "华人圈编码", paramType = "query", defaultValue = ""),
    })
    public Object delCircle(@RequestBody(required = false) @Valid RequestData<CircleNoBase> requestData, BindingResult bindingResult) {
        CircleNoBase data = requestData.getData();
        boolean delCircle = chineseCirclevice.delCircle(data.getCircleNo());
        return getBoolean(delCircle);
    }

    @RequestMapping(value = "/pageQueryCircleList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "分页查询华人圈列表", httpMethod = "POST", response = PageQueryCircleDetailsOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query", defaultValue = ""),
            @ApiImplicitParam(name = "pageNo", value = "当前页", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度", paramType = "query"),
            @ApiImplicitParam(name = "log", value = "经度", paramType = "query"),
            @ApiImplicitParam(name = "lat", value = "纬度", paramType = "query"),
    })
    public Object pageQueryCircleList(@RequestBody(required = false) @Valid RequestData<PageQueryCircleListInput> requestData, BindingResult bindingResult) {
        PageQueryCircleListInput data = requestData.getData();
        List<HotCircle> hotCircles = chineseCirclevice.chinesePeople(data.getCityName(), data.getPageNo(), data.getLog(), data.getLat());
        return getSuccess(hotCircles);
    }
}

