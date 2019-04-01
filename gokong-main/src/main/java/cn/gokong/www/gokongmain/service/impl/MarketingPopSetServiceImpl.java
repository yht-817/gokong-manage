package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.gokongmain.domain.MarketingPopSet;
import cn.gokong.www.gokongmain.dao.MarketingPopSetMapper;
import cn.gokong.www.gokongmain.service.MarketingPopSetService;
import cn.gokong.www.gokongmain.vo.marketing_pop.QueryMarketingPopOutput;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 营销弹窗设置表 服务实现类
 * </p>
 *
 * @author ikook
 * @since 2018-09-19
 */
@Service
public class MarketingPopSetServiceImpl extends ServiceImpl<MarketingPopSetMapper, MarketingPopSet> implements MarketingPopSetService {

    /**
     * 查询营销弹窗
     * @return
     */
    @Override
    public QueryMarketingPopOutput queryMarketingPop() {
        return baseMapper.queryMarketingPop();
    }
}
