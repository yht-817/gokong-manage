package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.MarketingPopSet;
import cn.gokong.www.gokongmain.vo.marketing_pop.QueryMarketingPopOutput;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 营销弹窗设置表 服务类
 * </p>
 *
 * @author ikook
 * @since 2018-09-19
 */
public interface MarketingPopSetService extends IService<MarketingPopSet> {

    /**
     * 查询营销弹窗
     * @return
     */
    QueryMarketingPopOutput queryMarketingPop();
}
