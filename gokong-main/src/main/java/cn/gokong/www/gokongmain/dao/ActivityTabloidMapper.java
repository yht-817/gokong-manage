package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.ActivityTabloid;
import cn.gokong.www.gokongmain.vo.local_activity.QueryActivityTabloidOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 活动小报 Mapper 接口
 * </p>
 *
 * @author ikook
 * @since 2018-10-22
 */
public interface ActivityTabloidMapper extends BaseMapper<ActivityTabloid> {

    @Select("select a_t.activity_no,a_t.content\n" +
            "from activity_tabloid a_t\n" +
            "right join local_activity la on la.activity_no = a_t.activity_no " +
            "where #{activityTime} < a_t.activity_time\n" +
            "and a_t.city_name = #{cityName}")
    List<QueryActivityTabloidOutput> queryActivityTabloid(@Param("cityName") String cityName, @Param("activityTime")Date date);
}
