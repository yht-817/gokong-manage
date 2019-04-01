package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.MarketingPopSet;
import cn.gokong.www.gokongmain.vo.marketing_pop.QueryMarketingPopOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 营销弹窗设置表 Mapper 接口
 * </p>
 *
 * @author ikook
 * @since 2018-09-19
 */
public interface MarketingPopSetMapper extends BaseMapper<MarketingPopSet> {

    /**
     * 查询营销弹窗
     * @return
     */
    @Select("select pop_no,pop_photo,pop_url,pop_remark from marketing_pop_set where now() between pop_begin_date and pop_end_date order by create_time desc limit 1")
    QueryMarketingPopOutput queryMarketingPop();
}
