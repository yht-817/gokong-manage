package cn.gokong.www.gokongmain.service;

import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.domain.PayApply;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;
import java.util.Map;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author tom
 * @since 2018-09-20
 */
public interface PayApplyService extends IService<PayApply> {

    ResponseData addBindingBank(String userNo, String userName, String userPhone, String userAddres, String userAccount, String bankName);

    ResponseData removeAccount(String userNo, String userAccount);

    ResponseData addWithdrawalInfo(String userNo, BigDecimal applyAmount, String userAccount);

    Map<String, String> seeCarNo(String userNo);

    BigDecimal getMony(String userNo);

    boolean seeUserInfoR(String userNo);
}
