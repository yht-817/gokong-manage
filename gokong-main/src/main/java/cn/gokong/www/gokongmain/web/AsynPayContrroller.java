package cn.gokong.www.gokongmain.web;


import cn.gokong.www.base.util.AliPayUtil;
import cn.gokong.www.base.util.ParseTools;
import cn.gokong.www.base.util.PayTools;
import cn.gokong.www.gokongmain.domain.UserPayDetail;
import cn.hutool.log.StaticLog;
import io.swagger.annotations.Api;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * 异步回调控制器
 */
@RestController
@RequestMapping("/v2/asyn")
@Api(tags = "支付回调系统", description = "AsynPayContrroller")
public class AsynPayContrroller extends BaseController {
    /**
     * 蟠桃支付的微信异步回调程序接口
     */
    @RequestMapping(value = "/wxasyn", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    public void wxasyn(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("微信异步回掉开始");
        Map<String, Object> data = PayTools.wxasynTotal(request, response);
        if (data != null) {
            System.out.println("--------返回的参数--------" + data.toString());
            // 支付返回结果参数进行修改业务
            int update = userRechargeService.updateUserInfo(data);
            PayTools.wxasynTotals(request, response, update);
        } else {
            PayTools.wxasynTotals(request, response, 0);
        }
    }

    /**
     * 蟠桃支付的支付宝异步回调程序接口
     */
    @RequestMapping(value = "/aliasyn", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    public String aliasyn(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("支付宝回调开始");
        String state = "fail";
        Map<String, String> requestData = ParseTools.analyticAlipay(request, response);
        System.out.println("--------返回的参数--------" + requestData.toString());
        if (requestData != null) {
            if (AliPayUtil.alipayNotify(requestData)) {
                Map<String, Object> data = new HashMap<>();
                data.put("wxorderno", requestData.get("out_trade_no"));
                data.put("totalfee", requestData.get("total_amount"));
                // 支付返回结果参数进行修改业务
                int update = userRechargeService.updateUserInfo(data);
                if (update > 0) {
                    state = "success";
                    return state;
                }
            }
        }
        return state;
    }

    /**
     * 牛人支付异步回调（支付宝）
     */
    @RequestMapping(value = "/peopelPayAli", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    public String peopelPayAli(HttpServletRequest request, HttpServletResponse response) {
        StaticLog.info("支付宝回调开始");
        String state = "fail";
        Map<String, String> requestData = ParseTools.analyticAlipay(request, response);
        System.out.println("--------返回的参数--------" + requestData.toString());
        if (requestData != null) {
            if (AliPayUtil.alipayNotify(requestData)) {
                Map<String, Object> data = new HashMap<>();
                data.put("wxorderno", requestData.get("out_trade_no"));
                data.put("totalfee", requestData.get("total_amount"));
                // 支付返回结果参数进行修改业务
                int update = userRechargeService.updatePeopelPayAli(data);
                if (update > 0) {
                    state = "success";
                    return state;
                }
            }
        }
        return state;
    }

    /**
     * 牛人支付异步回调（微信）
     */
    @RequestMapping(value = "/peopelPayWx", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    public void peopelPayWx(HttpServletRequest request, HttpServletResponse response) {
        StaticLog.info("微信回调开始");
        Map<String, Object> data = PayTools.wxasynTotal(request, response);
        if (data != null) {
            StaticLog.info("--------微信返回的参数--------" + data.toString());
            // 支付返回结果参数进行修改业务
            int update = userRechargeService.updatePeopelPayAli(data);
            PayTools.wxasynTotals(request, response, update);
        } else {
            PayTools.wxasynTotals(request, response, 0);
        }
    }

    /**
     * 同城活动的异步回调(支付宝)
     */
    @RequestMapping(value = "/sameAtivityAli", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    public String sameAtivityAli(HttpServletRequest request, HttpServletResponse response) {
        StaticLog.info("支付宝回调开始");
        String state = "fail";
        Map<String, String> requestData = ParseTools.analyticAlipay(request, response);
        System.out.println("--------返回的参数--------" + requestData.toString());
        if (requestData != null) {
            if (AliPayUtil.alipayNotify(requestData)) {
                Map<String, Object> data = new HashMap<>();
                data.put("wxorderno", requestData.get("out_trade_no"));
                data.put("totalfee", requestData.get("total_amount"));
                // 支付返回结果参数进行修改业务
                int update = localActivityApplyService.sameAtivityAli(data);
                if (update > 0) {
                    StaticLog.info("支付宝回调成功修改参数成功：" + update);
                    state = "success";
                    return state;
                }
            }
        }
        return state;
    }

    /**
     * 同城活动异步回调（微信）
     */
    @RequestMapping(value = "/sameAtivityWx", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    public void sameAtivityWx(HttpServletRequest request, HttpServletResponse response) {
        StaticLog.info("微信回调开始");
        Map<String, Object> data = PayTools.wxasynTotal(request, response);
        if (data != null) {
            StaticLog.info("--------微信返回的参数--------" + data.toString());
            // 支付返回结果参数进行修改业务
            int update = localActivityApplyService.sameAtivityAli(data);
            StaticLog.info("微信回调的信息：" + update);
            PayTools.wxasynTotals(request, response, update);
        } else {
            PayTools.wxasynTotals(request, response, 0);
        }
    }


    /**
     * 用户充值回调参数
     */
    @RequestMapping(value = "/userPayMainWx", produces = MediaType.APPLICATION_JSON_UTF8_VALUE, method = RequestMethod.POST)
    public void userPayMainWx(HttpServletRequest request, HttpServletResponse response) {
        StaticLog.info("微信回调开始");
        Map<String, Object> data = PayTools.wxasynTotal(request, response);
        if (data != null) {
            StaticLog.info("--------微信返回的参数--------" + data.toString());
            // 查询用户编码
            UserPayDetail userPayDetail = new UserPayDetail();
            userPayDetail.setPayNo(String.valueOf(data.get("wxorderno")));
            UserPayDetail userPayDetaildata = userPayDetailService.queryDataInfo(userPayDetail);
            // 查询当前用户编码
            String userNo = userPayDetaildata.getUserNo();
            // 修改订单状态
            int updateData = userPayDetailService.updateOrderStates(String.valueOf(data.get("wxorderno")));
            // 进行入账处理
            int update = userAccountService.addUserAccount(userNo, new BigDecimal(70));
            // 说明微信回调成功然后修改用户账户信息
            StaticLog.info("微信回调的信息：" + update);
            PayTools.wxasynTotals(request, response, update);
        } else {
            PayTools.wxasynTotals(request, response, 0);
        }
    }

}

