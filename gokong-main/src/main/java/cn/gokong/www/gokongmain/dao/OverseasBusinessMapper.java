package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.OverseasBusiness;
import cn.gokong.www.gokongmain.vo.overseas_business.PageQueryListOutput;
import cn.gokong.www.gokongmain.vo.overseas_business.QueryDetailsOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 海外创业 Mapper 接口
 * </p>
 *
 * @author ikook
 * @since 2018-09-25
 */
public interface OverseasBusinessMapper extends BaseMapper<OverseasBusiness> {

    /**
     * 分页查询列表
     *
     * @param keyword  检索内容
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    List<PageQueryListOutput> pageQueryList(@Param("keyword")String keyword, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    /**
     * 查询详情
     *
     * @param userNo     用户编码
     * @param businessNo 创业编码
     * @return
     */
    @Select("select business_no,business_title,title_img,content,(select join_state from overseas_business_apply where  user_no = #{userNo} and business_no = #{businessNo}) as joinState " +
            "from overseas_business " +
            "where business_no = #{businessNo}")
    QueryDetailsOutput queryDetails(@Param("userNo")String userNo, @Param("businessNo")String businessNo);
}
