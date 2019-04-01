package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.base.util.HttpClientTool;
import cn.gokong.www.gokongmain.dao.ChoiceMapper;
import cn.gokong.www.gokongmain.dao.ChoiceUserPayMapper;
import cn.gokong.www.gokongmain.domain.ChoiceUserPay;
import cn.gokong.www.gokongmain.domain.UserPayDetail;
import cn.gokong.www.gokongmain.service.*;
import cn.gokong.www.gokongmain.vo.chioce.ChoiceInfo;
import cn.gokong.www.gokongmain.vo.user.QueryUserAccountOutput;
import cn.hutool.core.util.ObjectUtil;
import cn.ikeek.www.weixin.pay.dao.WePayDao;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 用户精选支付信息 服务实现类
 * </p>
 *
 * @author tom
 * @since 2018-09-17
 */
@Service
public class ChoiceUserPayServiceImpl extends ServiceImpl<ChoiceUserPayMapper, ChoiceUserPay> implements ChoiceUserPayService {

    private static Logger logger = LoggerFactory.getLogger(HttpClientTool.class);

    @Autowired
    UserRechargeService userRechargeService;

    @Autowired
    UserAccountService userAccountService;

    @Autowired
    UserAccountLogService userAccountLogService;

    @Autowired
    ChoiceMapper choiceMapper;

    @Autowired
    FriendInviteService friendInviteService;

    @Autowired
    UserPayDetailService userPayDetailService;

    /**
     * 蟠桃充值业务（app）
     */
    public ResponseData payPeach(BigDecimal amount, Integer payType, String title, String userNo, BigDecimal peachNumber, BigDecimal givpeachNumbe) {
        ResponseData responseData = new ResponseData();
        // 生成订单
        String orderno = DataBaseTool.createNum(28);
        int insert = userRechargeService.payUserRecharge(amount, orderno, payType, givpeachNumbe, peachNumber, userNo);
        if (insert > 0) {
            Map<String, String> body = new HashMap<>();
            body.put("orderno", orderno);
            body.put("amount", String.valueOf(amount));
            body.put("title", title);
            // 1.0 支付宝支付
            if (payType == 1) {
                body.put("url", "http://47.98.139.112:8080/v2/asyn/aliasyn");
                // 远程调度支付系统 返回参数进行解析
                Map<String, Object> requestdata = HttpClientTool.requestPost("alipay/appunifiedorder", body);
                if (requestdata != null) {
                    int code = (int) requestdata.get("code");
                    if (code == 200) {
                        Map<String, Object> datasdk = (Map<String, Object>) requestdata.get("data");
                        responseData.setMessage("返回成功");
                        responseData.setCode(200);
                        responseData.setData(datasdk.get("sdk_data"));
                        return responseData;
                    }
                }
                responseData.setCode(500);
                responseData.setMessage("支付失败！请重新支付");
                return responseData;
            }
            // 2.0 微信支付
            else {
                body.put("url", "http://47.98.139.112:8080/v2/asyn/wxasyn");
                Map<String, Object> requestdata = HttpClientTool.requestPost("wxpay/appunifiedorder", body);
                if (requestdata != null) {
                    int code = (int) requestdata.get("code");
                    if (code == 200) {
                        Map<String, Object> datasdk = (Map<String, Object>) requestdata.get("data");
                        responseData.setMessage("返回成功");
                        responseData.setCode(200);
                        responseData.setData(datasdk);
                        return responseData;
                    }
                }
                responseData.setCode(500);
                responseData.setMessage("支付失败！请重新支付");
                return responseData;
            }
        }
        responseData.setCode(500);
        responseData.setMessage("支付失败！请重新支付");
        return responseData;
    }

    /**
     * APP 精选的购买
     */
    public ResponseData payTicketVolume(String userNo, Integer payType, String payAmount, String resourceNo, String
            peachSum) {
        ResponseData responseData = new ResponseData();
        // 1.0 查询用户的余额
        QueryUserAccountOutput queryUserAccountOutput = userAccountService.queryUserAccount(userNo);
        BigDecimal userMony = queryUserAccountOutput.getAccountAmount();
        ChoiceInfo choiceInfo = choiceMapper.findDetails(resourceNo);
        BigDecimal sum = null;
        // 2.0 蟠桃的使用情况
        int wukb_case = 10420001;
        if (userMony.compareTo(BigDecimal.valueOf(Double.valueOf(peachSum))) == -1) {
            // 3.0 小于说明加上抵扣的蟠桃
            sum = BigDecimal.valueOf(Double.valueOf(payAmount)).add(BigDecimal.valueOf(Double.valueOf(peachSum)));
        } else {
            sum = BigDecimal.valueOf(Double.valueOf(payAmount));
            wukb_case = 10420002;
        }
        return responseData;
    }


    /**
     * 蟠桃支付
     *
     * @param userno    蟠桃支付
     * @param payamount 支付的金额
     * @return
     */
    public ResponseData flatPeachPay(String userno, String friendNo, String payamount) {
        int am = Integer.valueOf(payamount);
        ResponseData responseData = new ResponseData();
        // 生成订单号
        String orderno = DataBaseTool.createNo("JHY");
        // 现记录日志然后进行减
        if (am > 0) {
            boolean log = userAccountLogService.inserlog("10050008", BigDecimal.valueOf(Double.valueOf(payamount)), orderno, userno, "添加好友");
            if (log) {
                // 说明扣款成功然后自动加为好友
                boolean userfrind = friendInviteService.applyFriend(userno, friendNo, orderno);
                if (userfrind) {
                    responseData.setMessage("你已成功认识他！");
                    responseData.setCode(200);
                    responseData.setData(orderno);
                    return responseData;
                }
            }
        } else {
            // 说明扣款成功然后自动加为好友
            boolean userfrind = friendInviteService.applyFriend(userno, friendNo, orderno);
            if (userfrind) {
                responseData.setMessage("你已成功认识他！");
                responseData.setCode(200);
                responseData.setData(orderno);
                return responseData;
            }
        }
        responseData.setMessage("支付失败！");
        responseData.setCode(500);
        responseData.setData(orderno);
        return responseData;
    }

    /**
     * 更具订单进行扣款处理
     *
     * @param order 订单号
     * @return
     */
    public boolean deductMoney(String order, String finrduserNo) {
        Map<String, Object> monydata = userAccountLogService.deductMoney(order);
        String userNo = (String) monydata.get("user_no");
        BigDecimal amount = (BigDecimal) monydata.get("change_amount");
        int ft = userAccountService.subtractUserAccount(userNo, amount);
        if (ft > 0) {
            // 在进行牛人金额相加
            int fadd = userAccountService.addUserAccount(finrduserNo, amount);
            if (fadd > 0) {
                return true;
            }
            return true;
        }
        return false;
    }

    /**
     * 用户充值（小程序）
     *
     * @param amount
     * @param userNo
     * @param openId
     * @return
     */
    public Object addSmallProgramPay(String amount, String userNo, String openId) {
        ResponseData responseData = new ResponseData();
        if (ObjectUtil.isNotNull(amount) && ObjectUtil.isNotNull(userNo) && ObjectUtil.isNotNull(openId)) {
            String rechargeType = "微信小程序";
            String orderNo = DataBaseTool.createNo("CZ");
            UserPayDetail userPayDetail = new UserPayDetail();
            userPayDetail.setId(DataBaseTool.createId());
            userPayDetail.setPayNo(orderNo);
            userPayDetail.setUserNo(userNo);
            userPayDetail.setPayAmount(new BigDecimal(amount));
            userPayDetail.setPayModeNo(rechargeType);
            userPayDetail.setPayState(0);
            userPayDetail.setPayDate(new Date());
            // 先进行日志记录
            int data = userPayDetailService.addPayDetailLog(userPayDetail);
            if (data > 0) {
                //微信下单
                Map<String, String> jsApiUnifiedOrder = WePayDao.jsApiUnifiedOrder("充值", orderNo, amount, openId, "https://api.gokong.cn/v2/asyn/userPayMainWx");
                System.out.println(jsApiUnifiedOrder.get("package").split("=")[1]);
                responseData.setMessage("成功");
                responseData.setCode(200);
                responseData.setData(jsApiUnifiedOrder);
                return responseData;
            }
        }
        responseData.setMessage("失败");
        responseData.setCode(500);
        return responseData;
    }
}
