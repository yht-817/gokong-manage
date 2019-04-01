package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.gokongmain.dao.MarketingSendSetMapper;
import cn.gokong.www.gokongmain.domain.MarketingSendSet;
import cn.gokong.www.gokongmain.service.MarketingSendSetService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>
 * 营销赠送设置表 服务实现类
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@Service
public class MarketingSendSetServiceImpl extends ServiceImpl<MarketingSendSetMapper, MarketingSendSet> implements MarketingSendSetService {

    /**
     * 获取营销赠送配置
     * @param date      当前时间
     * @param sendType  赠送类型
     * @return
     */
    @Override
    public MarketingSendSet queryMarketingSendConfig(Date date, String sendType) {
        return baseMapper.findBySendTypeBetweenDate(date,sendType);
    }
}
