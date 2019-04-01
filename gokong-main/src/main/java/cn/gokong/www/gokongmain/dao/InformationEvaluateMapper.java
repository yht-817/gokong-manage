package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.InformationEvaluate;
import cn.gokong.www.gokongmain.vo.Information_evaluate.PageQueryInformationEvaluateOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 资讯信息表评价记录 Mapper 接口
 * </p>
 *
 * @author ikook
 * @since 2018-09-15
 */
public interface InformationEvaluateMapper extends BaseMapper<InformationEvaluate> {

    @Select("select ie.evaluate_no,ie.evaluate_date, ie.evaluate_content, ie.praise_num,ie.reply_num, ui.user_no,ui.user_head, " +
            "ui.nick_name,if((select count(1) from information_evaluate_parise a where ie.evaluate_no = a.evaluate_no and a.user_no = #{userNo}),true,false) as praise " +
            "FROM information_evaluate ie " +
            "LEFT JOIN user_info ui on ie.user_no = ui.user_no " +
            "WHERE ie.information_no = #{informationNo} " +
            "order by ie.evaluate_date desc " +
            "limit #{pageNo},#{pageSize}")
    List<PageQueryInformationEvaluateOutput> pageQueryInformationEvaluate(@Param("informationNo")String informationNo, @Param("userNo")String userNo, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);

    @Select("select ie.evaluate_no,ie.evaluate_date, ie.evaluate_content, ie.praise_num,ie.reply_num, ui.user_no,ui.user_head, " +
            "ui.nick_name,if((select count(1) from information_evaluate_parise a where ie.evaluate_no = a.evaluate_no and a.user_no = #{userNo}),'true','false') as praise " +
            "FROM information_evaluate ie " +
            "LEFT JOIN user_info ui on ie.user_no = ui.user_no " +
            "WHERE ie.evaluate_no = #{evaluateNo} " +
            "order by ie.evaluate_date desc ")
    PageQueryInformationEvaluateOutput queryInformationEvaluate(@Param("evaluateNo")String evaluateNo, @Param("userNo")String userNo);
}
