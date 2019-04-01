package cn.gokong.www.gokongmain.web;


import cn.gokong.www.gokongmain.vo.mobile_version.QueryMobileVersionOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * APP版本更新控制 前端控制器
 * </p>
 *
 * @author tom
 * @since 2018-11-20
 */
@RestController
@RequestMapping("/v2/mobileVersionAmazon")
@Api(tags = "亚马逊APP版本升级", description = "MobileVersionAmazonContrroller")
public class MobileVersionAmazonContrroller extends BaseController{

    @GetMapping(value = "/queryMobileVersion", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ApiOperation(value = "获取APP版本信息", response = QueryMobileVersionOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "v", value = "版本号码", paramType = "query"),
    })
    public Object queryMobileVersion(String v) throws Exception {
        QueryMobileVersionOutput queryMobileVersionOutput =  mobileVersionAmazonService.queryMobileVersion(v);
        return getSuccess(queryMobileVersionOutput);
    }
}

