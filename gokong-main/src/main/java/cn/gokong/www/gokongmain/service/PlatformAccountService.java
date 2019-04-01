package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.PlatformAccount;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 * 平台账户金额 服务类
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
public interface PlatformAccountService extends IService<PlatformAccount> {


    /**
     * 变动平台余额
     * 变动金额	change_amount
     */
    int changeAmount(BigDecimal changeamount);


}
