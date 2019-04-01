package cn.gokong.www.gokongmain.service;

import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.domain.ChoiceUserPay;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 * 用户精选支付信息 服务类
 * </p>
 *
 * @author tom
 * @since 2018-09-17
 */
public interface ChoiceUserPayService extends IService<ChoiceUserPay> {

    // 精选app支付
    ResponseData payTicketVolume(String userNo, Integer payType, String payAmount, String resourceNo, String peachSum);

    // 充值
    ResponseData payPeach(BigDecimal amount, Integer payType, String title, String userNo, BigDecimal peachNumber, BigDecimal givpeachNumbe);

    // 蟠桃支付
    ResponseData flatPeachPay(String userno, String friendNo, String payamount);


    /**
     * 根据订单进行扣款处理
     *
     * @param order 订单号
     * @return
     */
    boolean deductMoney(String order,String userNo);

    Object addSmallProgramPay(String amount, String userNo, String openId);
}
