package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.gokongmain.vo.base.PageQueryUserNoBase;
import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import cn.gokong.www.gokongmain.vo.user_account.PageQueryAccountLogOutput;
import cn.gokong.www.gokongmain.vo.recharge_set_meal.QueryRechargeSetMealOutput;
import cn.gokong.www.gokongmain.vo.user.QueryUserAccountOutput;
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
 * 我的钱包 前端控制器
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
@RestController
@RequestMapping("/v2/myWalletApi")
@Api(tags = "我的钱包", description = "前端控制器")
public class MyWalletApi extends BaseController {

    @RequestMapping(value="/queryUserAccount",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取用户账户余额", httpMethod = "POST",response = QueryUserAccountOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
    })
    public Object queryUserAccount(@RequestBody(required = false) @Valid RequestData<UserNoBase> requestData, BindingResult bindingResult){
        UserNoBase data = requestData.getData();
        QueryUserAccountOutput userAccount = userAccountService.queryUserAccount(data.getUserNo());
        return getSuccess(userAccount);
    }

    @RequestMapping(value="/queryRechargeSetMealList",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "获取充值套餐列表", httpMethod = "POST",response = QueryRechargeSetMealOutput.class)
    public Object queryRechargeSetMealList(@RequestBody(required = false) @Valid RequestData requestData, BindingResult bindingResult){
        List<QueryRechargeSetMealOutput> queryRechargeSetMeal = rechargeSetMealService.queryRechargeSetMealList();
        return getSuccess(queryRechargeSetMeal);
    }

    @RequestMapping(value="/pageQueryAccountLogList",produces= MediaType.APPLICATION_JSON_UTF8_VALUE,method = RequestMethod.POST)
    @ApiOperation(value = "分页查询蟠桃变动记录", httpMethod = "POST",response = PageQueryAccountLogOutput.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userNo", value = "用户编码",paramType = "query"),
            @ApiImplicitParam(name = "pageNo", value = "当前页",paramType = "query",defaultValue = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页面长度",paramType = "query",defaultValue = "10"),
    })
    public Object pageQueryAccountLogList(@RequestBody(required = false) @Valid RequestData<PageQueryUserNoBase> requestData, BindingResult bindingResult){
        PageQueryUserNoBase data = requestData.getData();
        PageQueryAccountLogOutput pageQueryAccountLogOutput = userAccountLogService.pageQueryAccountLogList(data.getUserNo(),data.getPageNo(),data.getPageSize());
        return getSuccess(pageQueryAccountLogOutput);
    }
}

