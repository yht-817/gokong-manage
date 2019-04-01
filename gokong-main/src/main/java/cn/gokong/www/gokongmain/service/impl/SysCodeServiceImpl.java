package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.gokongmain.dao.SysCodeMapper;
import cn.gokong.www.gokongmain.domain.SysCode;
import cn.gokong.www.gokongmain.service.SysCodeService;
import cn.gokong.www.gokongmain.vo.user_pay.QueryPayType;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 控制代码表 服务实现类
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@Service
public class SysCodeServiceImpl extends ServiceImpl<SysCodeMapper, SysCode> implements SysCodeService {

    /**
     * 使用支付类型
     * @return
     */
    @Override
    public QueryPayType usePayType() {
        return baseMapper.usePayType();
    }
}
