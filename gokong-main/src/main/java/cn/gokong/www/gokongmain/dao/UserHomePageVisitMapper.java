package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.UserHomePageVisit;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.Map;

/**
 * <p>
 * 用户主页访问记录 Mapper 接口
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
public interface UserHomePageVisitMapper extends BaseMapper<UserHomePageVisit> {

    /**
     * 根据用户编码查询访问量
     * @param userNo    用户编码
     * @param date      当前时间
     * @return
     */
    @Select("select count(1) as total,(select count(1) from user_home_page_visit where user_no = #{userNo} and DATE_FORMAT(visit_date,'%Y-%m-%d') = DATE_FORMAT(#{date},'%Y-%m-%d' )) as today " +
            "from user_home_page_visit " +
            "where user_no = #{userNo}")
    Map<String,Long> queryUserHomeVisit(@Param("userNo")String userNo, @Param("date")Date date);
}
