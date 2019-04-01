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
 * 二级服务范围 前端控制器
 * <p>
 * 显示城市的控制代码
 *
 * </p>
 *
 * @author tom
 * @since 2018-09-13
 */
@RestController
@RequestMapping("/v2/onetomorecity")
@Api(tags = "城市列表", description = "ServiceScopeTwoContrroller")
public class ServiceScopeTwoContrroller extends BaseController {
    /**
     * 获取城市列表
     *
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/listmorecity", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "首页的城市列表", httpMethod = "POST", notes = "首页的城市列表", response = ResponseData.class)
    public Object getCityList(@RequestBody(required = false) @Valid RequestData requestData) {
        return serviceScopeTwoService.getCityList();
    }

    @RequestMapping(value = "/findcity", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "搜索城市", httpMethod = "POST", notes = "搜索城市", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "cityName", value = "获取城市名称", paramType = "query"),
    })
    public Object findCity(@RequestBody(required = false) @Valid RequestData<Map<String, String>> requestData) {
        String cityName = requestData.getData().get("cityName");
        return serviceScopeTwoService.findCity(cityName);
    }


    /**
     * 牛人选择城市
     *
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/queryServiceCity", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "牛人选择城市", httpMethod = "POST", notes = "牛人选择城市", response = ResponseData.class)
    public Object queryServiceCity(@RequestBody(required = false) @Valid RequestData requestData) {
        ResponseData responseData = new ResponseData();
        responseData.setData(serviceScopeTwoService.queryServiceCity());
        responseData.setCode(200);
        responseData.setMessage("成功");
        return responseData;
    }


}

