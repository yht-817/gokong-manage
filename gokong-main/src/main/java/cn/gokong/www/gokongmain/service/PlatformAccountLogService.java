package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.PlatformAccountLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.math.BigDecimal;

/**
 * <p>
 * 平台账户变动日志 服务类
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
public interface PlatformAccountLogService extends IService<PlatformAccountLog> {

    /**
     * 变动平台余额
     * 变动方式	change_mode
     * 变动金额	change_amount
     * 变动单号	change_no
     * 对应用户	user_no
     * 变动说明	change_remark
     */
    boolean inserlog(String changemode, BigDecimal changeamount, String changeno, String userno, String changeremark);
}
