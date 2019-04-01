package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.gokongmain.vo.mobile_version.QueryMobileVersionOutput;
import cn.gokong.www.gokongmain.vo.mobile_version.QueryMobileVersionInput;
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

/**
 * <p>
 * APP版本升级 前端控制器
 * </p>
 *
 * @author ikook
 * @since 2018-09-26
 */
@RestController
@RequestMapping("/v2/mobileVersion")
@Api(tags = "APP版本升级", description = "MerchantsApi")
public class MobileVersionApi extends BaseController{

    @RequestMapping(value = "/queryMobileVersion", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "获取APP版本信息", response = QueryMobileVersionOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "appName", value = "APP名称（ios android）", paramType = "query", defaultValue = "android"),
            @ApiImplicitParam(name = "appType", value = "APP类型（正式版：1 测试版：0）", paramType = "query", defaultValue = "1"),
    })
    public Object queryMobileVersion(@RequestBody(required = false) @Valid RequestData<QueryMobileVersionInput> requestData, BindingResult bindingResult) throws Exception {
        QueryMobileVersionInput data = requestData.getData();
        QueryMobileVersionOutput queryMobileVersionOutput =  mobileVersionService.queryMobileVersion(data.getAppName(),data.getAppType(),requestData.getV());
        return getSuccess(queryMobileVersionOutput);
    }
}

