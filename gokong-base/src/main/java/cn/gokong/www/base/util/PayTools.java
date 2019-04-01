package cn.gokong.www.base.util;

import cn.hutool.core.util.ObjectUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 支付的公共参数
 */
public class PayTools {

    /**
     * 解析参数表
     *
     * @param request
     * @param response
     * @return
     */
    public static Map<String, Object> wxasynTotal(HttpServletRequest request, HttpServletResponse response) {
        Map<String, String> datamsgmap = ParseTools.wxParsing(request, response);
        if (datamsgmap != null) {
            // 这里是支付成功
            if ("SUCCESS".equals((String) datamsgmap.get("result_code"))) {
                // 支付成功修改当前充值蟠桃的订单的支付状态和添加微信支付的支付订单号
                System.out.println("微信端返回map" + datamsgmap.toString());
                // 获取微信支付的订单号
                String wxorderno = datamsgmap.get("out_trade_no");
                // 支付的金额
                String totalfee = datamsgmap.get("total_fee");
                // 微信订单号
                String transactionid = datamsgmap.get("transaction_id");
                System.out.println("微信支付的订单：" + wxorderno + "支付的金额" + totalfee + "微信订单：" + transactionid);
                // 进行业务逻辑处理
                Map<String, Object> data = new HashMap<>();
                data.put("totalfee", yuanToSubdivisions(totalfee));
                data.put("wxorderno", wxorderno);
                data.put("transactionid", transactionid);
                return data;
            }
        }
        return null;
    }

    /**
     * 对参数进行处理返回给微信的服务
     *
     * @param request
     * @param response
     * @param result
     */
    public static void wxasynTotals(HttpServletRequest request, HttpServletResponse response, int result) {
        try {
            String resXml = "";
            if (result > 0) {
                resXml = "<xml>" + "<return_code><![CDATA[SUCCESS]]></return_code>" + "<return_msg><![CDATA[OK]]></return_msg>" + "</xml> ";
            } else {
                resXml = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>" + "<return_msg><![CDATA[参数错误]]></return_msg>" + "</xml> ";
            }
            //处理业务完毕返回参数
            BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
            out.write(resXml.getBytes());
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 分转化元
     */
    public static double yuanToSubdivisions(String total_fee) {
        if (ObjectUtil.isNotNull(total_fee)) {
            return new Double(Double.parseDouble(total_fee) / 100).intValue();
        }
        return 0;
    }
}
