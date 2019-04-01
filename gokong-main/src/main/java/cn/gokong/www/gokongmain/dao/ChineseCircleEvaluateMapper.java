package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.ChineseCircleEvaluate;
import cn.gokong.www.gokongmain.vo.chinese_circle_evaluate.PageQueryCircleEvaluateOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 华人圈评论表 Mapper 接口
 * </p>
 *
 * @author ikook
 * @since 2018-09-18
 */
public interface ChineseCircleEvaluateMapper extends BaseMapper<ChineseCircleEvaluate> {

    @Select("select cce.evaluate_no,cce.evaluate_date, cce.evaluate_content, cce.praise_num,cce.reply_num, from_user_no.user_no as fromUserNo,from_user_no.user_head as fromUserHead," +
            "from_user_no.nick_name as fromUserNick,to_user_no.user_no as toUserNo,to_user_no.nick_name as toUserNick," +
            "if((select count(1) from chinese_circle_evaluate_parise a where cce.evaluate_no = a.evaluate_no and a.user_no = #{userNo}),true,false) as liked " +
            "FROM chinese_circle_evaluate cce " +
            "LEFT JOIN user_info from_user_no on cce.from_user_no = from_user_no.user_no " +
            "LEFT JOIN user_info to_user_no on cce.to_user_no = to_user_no.user_no " +
            "WHERE cce.circle_no = #{circleNo} " +
            "order by cce.evaluate_date desc " +
            "limit #{pageNo},#{pageSize}")
    List<PageQueryCircleEvaluateOutput> pageQueryCircleEvaluate(@Param("circleNo")String circleNo, @Param("userNo")String userNo, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);
}
