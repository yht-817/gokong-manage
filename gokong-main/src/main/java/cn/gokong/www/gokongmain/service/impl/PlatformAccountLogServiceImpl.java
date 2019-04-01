package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.base.util.DataBaseTool;
import cn.gokong.www.gokongmain.domain.PlatformAccountLog;
import cn.gokong.www.gokongmain.dao.PlatformAccountLogMapper;
import cn.gokong.www.gokongmain.service.PlatformAccountLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 平台账户变动日志 服务实现类
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@Service
public class PlatformAccountLogServiceImpl extends ServiceImpl<PlatformAccountLogMapper, PlatformAccountLog> implements PlatformAccountLogService {

    @Override
    public boolean inserlog(String changemode, BigDecimal changeamount, String changeno, String userno, String changeremark) {
        PlatformAccountLog platformAccountLog = new PlatformAccountLog();
        platformAccountLog.setId(DataBaseTool.createId());
        platformAccountLog.setChangeDate(new Date());
        platformAccountLog.setChangeAmount(changeamount);
        platformAccountLog.setChangeMode(changemode);
        platformAccountLog.setChangeNo(changeno);
        platformAccountLog.setChangeRemark(changeremark);
        platformAccountLog.setUserNo(userno);
        return save(platformAccountLog);
    }
}
