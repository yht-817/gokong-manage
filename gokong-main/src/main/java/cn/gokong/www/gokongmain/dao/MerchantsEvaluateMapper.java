package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.MerchantsEvaluate;
import cn.gokong.www.gokongmain.vo.merchants_evaluate.PageQueryListOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 商户评价表 Mapper 接口
 * </p>
 *
 * @author ikook
 * @since 2018-09-14
 */
public interface MerchantsEvaluateMapper extends BaseMapper<MerchantsEvaluate> {

    @Select("select ui.user_no,ui.user_head,ui.nick_name,me.evaluate_content,me.evaluate_time,me.evaluate_score " +
            "from merchants_evaluate me " +
            "left join user_info ui on ui.user_no = me.user_no " +
            "where me.merchants_no = #{merchantsNo} " +
            "limit #{pageNo},#{pageSize}")
    List<PageQueryListOutput> pageQueryList(@Param("merchantsNo")String merchantsNo, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);
}
