package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.gokongmain.vo.setting.BindPhoneNoInput;
import cn.gokong.www.gokongmain.vo.user.QueryUserHomeOutput;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * <p>
 * 用户设置 前端控制器
 * </p>
 *
 * @author Kook
 * @since 2018-09-08
 */
@RestController
@RequestMapping("/v2/setting")
@Api(tags = "用户设置", description = "SettingApi",hidden = true)
public class SettingApi extends BaseController{

    @RequestMapping(value="/bindPhoneNo",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "绑定手机号码", httpMethod = "POST",response = QueryUserHomeOutput.class,hidden = true)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "phoneNo", value = "手机号码",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "phoneCode", value = "手机代码",paramType = "query",defaultValue = ""),
            @ApiImplicitParam(name = "securityCode", value = "验证码",paramType = "query",defaultValue = ""),
    })
    public Object bindPhoneNo(@RequestBody(required = false) @Valid RequestData<BindPhoneNoInput> requestData, BindingResult bindingResult){
        BindPhoneNoInput data = requestData.getData();
        boolean bindPhoneNo = userInfoService.bindPhoneNo(data.getUserNo(), data.getPhoneNo(),data.getPhoneCode(),data.getSecurityCode());
        return getBoolean(bindPhoneNo);
    }
}

