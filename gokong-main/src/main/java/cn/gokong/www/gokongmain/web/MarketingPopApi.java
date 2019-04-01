package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.gokongmain.vo.marketing_pop.QueryMarketingPopOutput;
import io.swagger.annotations.Api;
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
 * 营销弹窗设置表 前端控制器
 * </p>
 *
 * @author ikook
 * @since 2018-09-19
 */
@RestController
@RequestMapping("/v2/marketingPopSet")
@Api(tags = "营销弹窗", description = "MarketingPopApi")
public class MarketingPopApi extends BaseController{

    @RequestMapping(value="/queryMarketingPop",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "查询营销弹窗", httpMethod = "POST",response = QueryMarketingPopOutput.class)
    public Object queryMarketingPop(@RequestBody(required = false) @Valid RequestData requestData, BindingResult bindingResult){
        QueryMarketingPopOutput queryMarketingPopOutput = marketingPopSetService.queryMarketingPop();
        return getSuccess(queryMarketingPopOutput);
    }
}

