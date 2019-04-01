package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 * 城市概览
 * </p>
 *
 * @author tom
 * @since 2018-10-11
 */
@RestController
@RequestMapping("/v2/cityOverview")
@Api(tags = "城市概览", description = "CityOverviewContrroller")
public class CityOverviewContrroller extends BaseController {

    /**
     * 显示当前城市的信息
     */
    @RequestMapping(value = "/getCityOverview", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "城市概览的列表", httpMethod = "POST", notes = "城市概览的列表", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "city", value = "获取城市名称", paramType = "query"),
    })
    @ResponseBody
    public Object getTopList(@RequestBody(required = false) @Valid RequestData<Map<String, String>> requestData) {
        String cityname = requestData.getData().get("city");
        return cityOverviewService.queryCityOverview(cityname);
    }

}

