package cn.gokong.www.gokongmain.web;

import cn.gokong.www.gokongmain.vo.user_collection.PageQueryInformationCollectionOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 说明:微信JSAPI
 *
 * @author ikook
 * @CreateDate 2018/12/13 15:00
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@RestController
@RequestMapping("/v2/weixin_jssdk")
@Api(tags = "微信JSAPI", description = "WeatherController")
public class WeiXinJsSdkApi extends BaseController{

    @GetMapping(value = "/getSignature", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取签名", httpMethod = "GET", response = PageQueryInformationCollectionOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "url", value = "URL链接", paramType = "query"),
    })
    public Object getSignature(String url) {
        return weiXinJsSdkService.getSignature(url);
    }

}
