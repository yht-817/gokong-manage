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
 * <p>
 * 前端控制器
 * </p>
 *
 * @author tom
 * @since 2018-09-12
 */
@RestController
@RequestMapping("/v2/uiControl")
@Api(tags = "首页控制层", description = "UiControlContrroller")
public class UiControlContrroller extends BaseController {
    private final Logger logger = LoggerFactory.getLogger(UiControlContrroller.class);

    /**
     * 获取首页的显示数据
     */
    @RequestMapping(value = "/homepage", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "首页的控制区", httpMethod = "POST", notes = "首页的控制区", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "cityName", value = "成都名称", paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页数编码", paramType = "query"),
            @ApiImplicitParam(name = "log", value = "经度", paramType = "query"),
            @ApiImplicitParam(name = "lat", value = "纬度", paramType = "query"),
    })
    public Object getTopList(@RequestBody(required = false) @Valid RequestData<Map<String, String>> requestData) {
        Map<String, String> citymap = requestData.getData();
        String cityNo = citymap.get("cityName");
        String pageNo = citymap.get("pageNo");
        String logno = citymap.get("log");
        String latno = citymap.get("lat");
        String userNo = citymap.get("userNo");
        Map<String, Object> datameg = uiControlService.findHomePage(userNo, cityNo, pageNo, logno, latno);
        return datameg;
    }


    /**
     * 同城牛人 分页查询
     */
    @RequestMapping(value = "/cattlepeople", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "查看更多牛人和搜索查询", httpMethod = "POST", notes = "查看更多牛人和搜索查询", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cityName", value = "城市名", paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页数编码", paramType = "query"),
            @ApiImplicitParam(name = "userName", value = "根据姓名搜索", paramType = "query"),
    })
    public Object findCattlePeople(@RequestBody(required = false) @Valid RequestData<Map<String, String>> requestData) {
        Map<String, String> citymap = requestData.getData();
        String cityNo = citymap.get("cityName");
        String pageNo = citymap.get("pageNo");
        String userName = citymap.get("userName");
        return uiControlService.findCattlePeople(cityNo, pageNo, userName);
    }

    /**
     *  查询牛人的详情
     */
    /**
     * 同城牛人 分页查询
     */
    @RequestMapping(value = "/details", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "查看牛人的详情", httpMethod = "POST", notes = "查看牛人的详情", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "登陆用户的用户编码", paramType = "query"),
            @ApiImplicitParam(name = "cattelUserNo", value = "牛人的用户编码", paramType = "query"),
    })
    public Object findCattlePeopleDetails(@RequestBody(required = false) @Valid RequestData<Map<String, String>> requestData) {
        return uiControlService.findCattlePeopleDetails(requestData);
    }

    /**
     * 我的页面按钮入口
     */
    @RequestMapping(value = "/myUI", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "我的页面入口中心按钮", httpMethod = "POST", notes = "我的页面入口中心按钮", response = ResponseData.class)
    public Object myUI(@RequestBody(required = false) @Valid RequestData<Map<String, String>> requestData) {
        return uiControlService.myUI();
    }


}

