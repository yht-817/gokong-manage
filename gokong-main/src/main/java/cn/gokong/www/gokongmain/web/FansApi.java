package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.vo.fans.SetFocusInput;
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
 * 粉丝表 前端控制器
 * </p>
 *
 * @author ikook
 * @since 2018-09-14
 */
@RestController
@RequestMapping("/v2/fans")
@Api(tags = "关注（添加关注/取消关注）", description = "FansApi")
public class FansApi extends BaseController{

    @RequestMapping(value="/setFocus",produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "设置关注", httpMethod = "POST",response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "被关注者编码",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "fansUserNo", value = "粉丝编码",paramType = "query",dataType = "String"),
            @ApiImplicitParam(required = true, name = "focus", value = "类型 true：关注 false：取消",paramType = "query",dataType = "String"),
    })
    public Object setFocus(@RequestBody(required = false) @Valid RequestData<SetFocusInput> requestData, BindingResult bindingResult){
        SetFocusInput data = requestData.getData();
        boolean setFocus = fansInfoService.setFocus(data.getUserNo(), data.getFansUserNo(),data.isFocus());
        return getBoolean(setFocus);
    }
}

