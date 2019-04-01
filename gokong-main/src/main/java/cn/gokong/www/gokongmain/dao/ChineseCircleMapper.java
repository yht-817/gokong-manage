package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.ChineseCircle;
import cn.gokong.www.gokongmain.vo.chinese_circle.CircleDetails;
import cn.gokong.www.gokongmain.vo.chinese_circle.PageQueryChineseCircleOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 华人圈 Mapper 接口
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
public interface ChineseCircleMapper extends BaseMapper<ChineseCircle> {

    /**
     * 分页查询我发布的华人圈
     *
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Select("select cc.circle_no,cc.circle_img,cc.instructions,ui.user_no,ui.user_head,ui.nick_name " +
            "from chinese_circle cc " +
            "left join user_info ui on ui.user_no = cc.user_no " +
            "where cc.user_no = #{userNo} GROUP BY cc.create_time DESC " +
            "limit #{pageNo},#{pageSize}")
    List<PageQueryChineseCircleOutput> pageQueryMyChineseCircle(@Param("userNo") String userNo, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    /**
     * 分页获取当前的城市的华人圈
     *
     * @param cityNo 城市编码
     * @param pageno 分页编码
     * @return
     */
    List<Map<String, Object>> findHot(@Param(value = "cityNo") String cityNo, @Param(value = "pageno") int pageno);

    /**
     * 分页查询华人圈详情
     *
     * @param userNo   用户编码
     * @param circleNo 华人圈编码
     * @param pageNo   开始位置
     * @param pageSize 页面长度
     * @return
     */
    @Select("select cc.user_no,cc.circle_no,cc.instructions,cc.circle_img," +
            "(select cce.evaluate_date from chinese_circle_evaluate cce where cce.circle_no = cc.circle_no order by cce.evaluate_date desc limit 1) as create_time," +
            "cc.like_num,cc.comments_num," +
            "if((select count(1) from collection_info ci where ci.content_no = cc.circle_no and ci.user_no = #{userNo}),'true','false') as collection," +
            "if((select count(1) from chinese_circle_praise ccp where ccp.circle_no = cc.circle_no and ccp.user_no = #{userNo}),'true','false') as liked " +
            "from chinese_circle cc " +
            "where cc.existe_state = 0 and cc.user_no = (select cc_cc.user_no from chinese_circle cc_cc where cc_cc.circle_no = #{circleNo}) " +
            "ORDER BY cc.circle_no <> #{circleNo},cc.create_time desc " +
            "limit #{pageNo},#{pageSize}")
    List<CircleDetails> pageQueryCircleDetails(@Param("userNo") String userNo, @Param("circleNo") String circleNo, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    @Insert("INSERT content_report(id,user_no,type,date)VALUES(#{id},#{userNo},#{circleNo},#{date})")
    int reportCircleDetails(@Param("id") String id, @Param("userNo") String userNo, @Param("circleNo") String circleNo, @Param("date") Date date);

    @Update("UPDATE chinese_circle SET existe_state = 1 WHERE circle_no = #{circleNo}")
    int updateCircleDetailsStates(@Param("circleNo") String circleNo);
}
