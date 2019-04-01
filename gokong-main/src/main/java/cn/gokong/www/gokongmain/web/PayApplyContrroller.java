package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.domain.PayApply;
import cn.hutool.log.StaticLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户提现
 * </p>
 *
 * @author tom
 * @since 2018-09-20
 */
@RestController
@RequestMapping("/v2/payApply")
@Api(tags = "用户提现的处理", description = "PayApplyContrroller")
public class PayApplyContrroller extends BaseController {


    /**
     * 绑定用户的账号
     */
    @RequestMapping(value = "/addBindingBank", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "用户绑定提现账号", httpMethod = "POST", notes = "用户绑定提现账号", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "userName", value = "开户行姓名", paramType = "query"),
            @ApiImplicitParam(required = true, name = "userPhone", value = "用户电话号码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "userAddres", value = "开户行地址", paramType = "query"),
            @ApiImplicitParam(required = true, name = "userAccount", value = "银行卡账号", paramType = "query"),
            @ApiImplicitParam(required = true, name = "bankName", value = "开户银行名称", paramType = "query"),
    })
    public Object addBindingBank(@RequestBody(required = false) RequestData<PayApply> requestData) {
        StaticLog.info(requestData.getData().toString());
        PayApply payApply = requestData.getData();
        return payApplyService.addBindingBank(payApply.getUserNo(), payApply.getUserName(), payApply.getUserPhone(), payApply.getUserAddres(), payApply.getUserAccount(), payApply.getBankName());
    }

    /**
     * 查看用户的是否存在账号和用户现在可提现余额
     */
    @RequestMapping(value = "/userInfoR", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "查看用户是否存在账号，存在直接进行体现处理，没有进行信息填写", httpMethod = "POST", notes = "查看用户是否存在账号，存在直接进行体现处理，没有进行信息填写", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query"),
    })
    public Object userInfoR(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        ResponseData responseData = new ResponseData();
        String userNo = requestData.getData().get("userNo");
        // 查看用户是否存在提现的信息
        boolean cz = payApplyService.seeUserInfoR(userNo);
        BigDecimal mony = payApplyService.getMony(userNo);
        Map<String, Object> msgdata = new HashMap<>();
        msgdata.put("exist", cz);
        msgdata.put("money", mony);
        if (cz) {
            // 查询当前用户的银行卡信息
            Map<String, String> cardNo = payApplyService.seeCarNo(userNo);
            msgdata.put("carNo", cardNo.get("user_account"));
            msgdata.put("bankName", cardNo.get("bank_name"));
        }
        responseData.setData(msgdata);
        responseData.setCode(200);
        return responseData;
    }

    /**
     * 进行提现申请
     */
    @RequestMapping(value = "/addWithdrawalInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "提现的金额", httpMethod = "POST", notes = "提现的金额", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "applyAmount", value = "提现金额", paramType = "query"),
            @ApiImplicitParam(required = true, name = "userAccount", value = "提现银行卡", paramType = "query"),
    })
    public Object addOrder(@RequestBody(required = false) RequestData<PayApply> requestData) {
        PayApply payApply = requestData.getData();
        return payApplyService.addWithdrawalInfo(payApply.getUserNo(), payApply.getApplyAmount(), payApply.getUserAccount());
    }

    /**
     * 解绑银行卡
     */
    @RequestMapping(value = "/removeAccount", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "解绑银行卡", httpMethod = "POST", notes = "解绑银行卡", response = ResponseData.class)
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "userAccount", value = "提现银行卡", paramType = "query"),
    })
    public Object removeAccount(@RequestBody(required = false) RequestData<PayApply> requestData) {
        PayApply payApply = requestData.getData();
        return payApplyService.removeAccount(payApply.getUserNo(), payApply.getUserAccount());
    }


}

