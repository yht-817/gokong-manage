package cn.gokong.www.gokongmain.web;

import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.base.util.HttpClientTool;
import cn.gokong.www.gokongmain.vo.user_collection.PageQueryInformationCollectionOutput;
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
import java.util.HashMap;
import java.util.Map;

/**
 * 获取天气的信息
 */

@RestController
@RequestMapping("/v2/weather")
@Api(tags = "天气预报", description = "WeatherController")
public class WeatherController {

    @RequestMapping(value = "/weatherdata", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "获取天气数据(实时数据)", httpMethod = "POST", response = PageQueryInformationCollectionOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "log", value = "经度", paramType = "query"),
            @ApiImplicitParam(name = "lat", value = "纬度", paramType = "query"),
    })
    public Object findWeather(@RequestBody(required = false) @Valid RequestData<Map<String, String>> requestData) {
        ResponseData responseData = new ResponseData();
        Map<String, Object> data = new HashMap<>();
        String url = "http://api.caiyunapp.com/v2/bDiOBUhYbbBWByK6/" + requestData.getData().get("log") + "," + requestData.getData().get("lat") + "/realtime.jsonp?callback=MYCALLBACK";
        String urlf = "https://api.caiyunapp.com/v2/bDiOBUhYbbBWByK6/" + requestData.getData().get("log") + "," + requestData.getData().get("lat") + "/forecast.jsonp?callback=MYCALLBACK";
        Map<String, Object> datarealtime = HttpClientTool.requestGet(url);
        Map<String, Object> dataforecast = HttpClientTool.requestGet(urlf);
        if (datarealtime != null) {
            data.put("realtime", datarealtime);
        }
        if (dataforecast != null) {
            data.put("forecast", dataforecast);
        }
        responseData.setData(data);
        if (datarealtime == null && dataforecast == null) {
            responseData.setCode(500);
        }
        responseData.setCode(200);
        return responseData;
    }
}
