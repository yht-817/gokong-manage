package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.MarketingSendSet;
import cn.gokong.www.gokongmain.vo.marketing_pop.QueryMarketingPopOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;

/**
 * <p>
 * 营销赠送设置表 服务类
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
public interface MarketingSendSetService extends IService<MarketingSendSet> {

    /**
     * 获取营销赠送配置
     * @param date      当前时间
     * @param sendType  赠送类型
     * @return
     */
    MarketingSendSet queryMarketingSendConfig(Date date, String sendType);


}
