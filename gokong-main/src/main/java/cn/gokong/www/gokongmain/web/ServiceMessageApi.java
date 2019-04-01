package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.gokongmain.vo.base.PageQueryUserNoBase;
import cn.gokong.www.gokongmain.vo.service_message.PageQueryServiceMessageOutput;
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
import java.util.List;

/**
 * <p>
 * 服务通知模板 前端控制器
 * </p>
 *
 * @author ikook
 * @since 2018-09-13
 */
@RestController
@RequestMapping("/v2/serviceMessage")
@Api(tags = "服务消息", description = "ServiceMessageApi")
public class ServiceMessageApi extends BaseController{

    @RequestMapping(value="/pageQueryServiceMessage",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页查询服务消息", httpMethod = "POST",response = PageQueryServiceMessageOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public Object pageQueryServiceMessage(@RequestBody(required = false) @Valid RequestData<PageQueryUserNoBase> requestData, BindingResult bindingResult){
        PageQueryUserNoBase data = requestData.getData();
        List<PageQueryServiceMessageOutput> pageQueryServiceMessageOutputs = chatServiceMessageService.pageQueryServiceMessage(data.getUserNo(),data.getPageNo(),data.getPageSize());
        return getSuccess(pageQueryServiceMessageOutputs);
    }
}

