package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.gokongmain.domain.PlatformAccount;
import cn.gokong.www.gokongmain.dao.PlatformAccountMapper;
import cn.gokong.www.gokongmain.service.PlatformAccountService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * <p>
 * 平台账户金额 服务实现类
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@Service
public class PlatformAccountServiceImpl extends ServiceImpl<PlatformAccountMapper, PlatformAccount> implements PlatformAccountService {

    /**
     * 减去平台的金额
     *
     * @param changeamount 金额
     * @return
     */
    public int changeAmount(BigDecimal changeamount) {
        return this.baseMapper.changeAmount(changeamount);
    }
}
