package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.vo.chioce.ChoiceInfoPay;
import cn.gokong.www.gokongmain.vo.chioce.ChoiceInfoPayRecharge;
import cn.gokong.www.gokongmain.vo.user_pay.QueryPayType;
import io.swagger.annotations.*;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * <p>
 * 用户精选支付信息 前端控制器
 * <p>
 * 用户支付
 * </p>
 *
 * @author tom
 * @since 2018-09-17
 */
@RestController
@RequestMapping("/v2/choiceUserPay")
@Api(tags = "支付系统", description = "UserPayContrroller")
public class UserPayContrroller extends BaseController {
    /**
     * *蟠桃充值业务-支付宝接口（或者）微信接口（APP支付）
     *
     * @param requestData
     * @return
     */
    @RequestMapping(value = "/peachalipay", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "蟠桃充值业务-支付宝接口（或者）微信接口（APP支付）", httpMethod = "POST", notes = "蟠桃充值业务", response = ResponseData.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "失败"),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "amount", value = "支付的金额,前段把金额进行判断只准数据两位小数", paramType = "query"),
            @ApiImplicitParam(name = "payType", value = "充值类型：1：表示支付宝，2表示微信", paramType = "query"),
            @ApiImplicitParam(name = "title", value = "支付这笔费用的类型说明", paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "peachNumber", value = "充值的蟠桃的数量", paramType = "query"),
            @ApiImplicitParam(name = "givpeachNumbe", value = "赠送的蟠桃数量", paramType = "query")
    })
    public Object peachAlipay(@RequestBody(required = false) @Valid RequestData<ChoiceInfoPayRecharge> requestData, BindingResult bindingResult) {
        ChoiceInfoPayRecharge data = requestData.getData();
        return choiceUserPayService.payPeach(data.getAmount(), data.getPayType(), data.getTitle(), data.getUserNo(), data.getPeachNumber(), data.getGivpeachNumbe());
    }

    /**
     * APP 精选票卷支付接口
     * 蟠桃支付和现金支付
     */
    @RequestMapping(value = "/siftpay", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "精选购买劵业务-支付宝接口（或者）微信接口（APP支付）", httpMethod = "POST", notes = "精选购买劵业务", response = ResponseData.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "失败"),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "payType", value = "充值类型：1：表示支付宝，2表示微信", paramType = "query"),
            @ApiImplicitParam(required = true, name = "payAmount", value = "支付金额", paramType = "query"),
            @ApiImplicitParam(required = true, name = "resourceNo", value = "精选编码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "peachSum", value = "支付的蟠桃数量", paramType = "query"),
    })
    public Object payTicketVolume(@RequestBody(required = false) RequestData<ChoiceInfoPay> requestData) {
        ChoiceInfoPay data = requestData.getData();
        return choiceUserPayService.payTicketVolume(data.getUserNo(), data.getPayType(), data.getPayAmount(), data.getResourceNo(), data.getPeachSum());
    }

    /**
     * 蟠桃支付
     */
    @RequestMapping(value = "/flatPeachPay", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "蟠桃支付", httpMethod = "POST", notes = "蟠桃支付", response = ResponseData.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "失败"),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(required = true, name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "friendNo", value = "牛人用户编码", paramType = "query"),
            @ApiImplicitParam(required = true, name = "payAmount", value = "支付牛人的金额", paramType = "query")
    })
    public Object flatPeachPay(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        String userno = requestData.getData().get("userNo");
        String friendno = requestData.getData().get("friendNo");
        String payamount = requestData.getData().get("payAmount");
        return choiceUserPayService.flatPeachPay(userno, friendno, payamount);
    }


    @RequestMapping(value = "/queryPayType", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "使用支付类型", httpMethod = "POST", response = QueryPayType.class)
    public Object usePayType(@RequestBody(required = false) RequestData requestData) {
        QueryPayType queryPayType = sysCodeService.usePayType();
        return getSuccess(queryPayType);
    }


    /**
     * 微信小程序支付
     */
    @RequestMapping(value = "/smallProgramPay", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    @ApiOperation(value = "微信小程序支付", httpMethod = "POST", notes = "微信小程序支付", response = ResponseData.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功"),
            @ApiResponse(code = 500, message = "失败"),
    })
    @ApiImplicitParams({
            @ApiImplicitParam(name = "amount", value = "金额大小", paramType = "query"),
            @ApiImplicitParam(name = "userNo", value = "用户编码", paramType = "query"),
            @ApiImplicitParam(name = "openId", value = "微信openId", paramType = "query"),
    })
    public Object smallProgramPay(@RequestBody(required = false) RequestData<Map<String, String>> requestData) {
        String amount = requestData.getData().get("amount");
        String userNo = requestData.getData().get("userNo");
        String openId = requestData.getData().get("openId");
        return choiceUserPayService.addSmallProgramPay(amount, userNo, openId);
    }


}

