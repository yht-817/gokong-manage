package cn.gokong.www.gokongmain.web;

import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * 精选的业务逻辑
 */
@RestController
@RequestMapping("/v2/choice")
@Api(tags = "精选的业务/精选API", description = "ChoiceController")
public class ChoiceController extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(ChoiceController.class);

    /**
     * 获取精选的展示列表数据
     */
    @RequestMapping(value = "/getTopList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "顶部列表", httpMethod = "POST", notes = "顶部精选列表", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "city", value = "获取城市名称", paramType = "query"),
    })
    @ResponseBody
    public Object getTopList(@RequestBody(required = false) @Valid RequestData<Map<String, String>> requestData) {
        Map<String, String> citymap = requestData.getData();
        String city = citymap.get("city");
        return choiceService.findTopMiddleInfo(city);
    }

    /**
     * 首页底部精选分页列表信息(关键字搜索)
     */
    @RequestMapping(value = "/getBottomList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "底部分页列表", httpMethod = "POST", notes = "底部分页列表", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "city", value = "获取城市名称", paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "页码", paramType = "query"),
            @ApiImplicitParam(name = "keyWord", value = "关键字", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页数量", paramType = "query"),
    })
    public Object getBottomList(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, String> data = requestData.getData();
        Object bottom = choiceService.findBottom(data);
        return getSuccess(bottom);
    }

    /**
     * 点开精选的 查看详情的信息
     */
    @RequestMapping(value = "/finddetails", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "查看精选的详细结果", httpMethod = "POST", notes = "查看精选的详细结果", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "resourceNo", value = "资源编码", paramType = "query"),
            @ApiImplicitParam(name = "collectionNo", value = "当前的精选是否收藏（0：表示没有收藏，1：表示收藏）", paramType = "query"),
    })
    public Object getDetailsChioce(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        Map<String, String> data = requestData.getData();
        return choiceService.findDetails(data);
    }
}