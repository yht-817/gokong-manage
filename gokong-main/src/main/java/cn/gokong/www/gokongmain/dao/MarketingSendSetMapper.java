package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.MarketingSendSet;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;import org.apache.ibatis.annotations.Select;

import java.util.Date;

/**
 * <p>
 * 营销赠送设置表 Mapper 接口
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
public interface MarketingSendSetMapper extends BaseMapper<MarketingSendSet> {

    /**
     * 根据当前时间和赠送类型查询营销赠送配置
     * @param date      当前时间
     * @param sendType  赠送类型
     * @return
     */
    @Select("select * from marketing_send_set where send_type = #{sendType} and #{date} BETWEEN send_begin_date AND send_end_date limit 1")
    MarketingSendSet findBySendTypeBetweenDate(@Param("date")Date date, @Param("sendType")String sendType);
}
