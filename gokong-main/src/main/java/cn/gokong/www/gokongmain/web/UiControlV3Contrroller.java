package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/v3/uiControl")
@Api(tags = "首页控制层", description = "UiControlContrroller")
public class UiControlV3Contrroller extends BaseController {
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
        Map<String, Object> datameg = uiControlService.findHomePageV3(userNo, cityNo, pageNo, logno, latno);
        return datameg;
    }
}

