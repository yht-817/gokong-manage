package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.ChineseCirclePraise;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 华人圈点赞表 Mapper 接口
 * </p>
 *
 * @author ikook
 * @since 2018-09-18
 */
public interface ChineseCirclePraiseMapper extends BaseMapper<ChineseCirclePraise> {

    @Select("select * from chinese_circle_praise where user_no = #{userNo} and circle_no = #{circleNo}")
    ChineseCirclePraise findByUserNoAndCircleNo(@Param("userNo")String userNo, @Param("circleNo")String circleNo);
}
