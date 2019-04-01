package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.CompanyRecruitment;
import cn.gokong.www.gokongmain.vo.company_recruitment.DetailsOutput;
import cn.gokong.www.gokongmain.vo.company_recruitment.PageQueryListOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 招聘信息 Mapper 接口
 * </p>
 *
 * @author ikook
 * @since 2018-10-02
 */
public interface CompanyRecruitmentMapper extends BaseMapper<CompanyRecruitment> {

    /**
     * 分页查询招聘求职列表
     *
     * @param cityName 城市名称
     * @param keyWord  检索内容
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    List<PageQueryListOutput> pageQueryList(@Param("cityName")String cityName, @Param("keyWord")String keyWord, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);

    @Select("select recruitment_no,company_logo,company_name,staff_size,contact_number," +
            "(select type_name from position_type where type_no = position_type) as positionType," +
            "CONCAT(gold_symbols,job_salary) as job_salary,browse_num,working_place,minimum_degree,experience,job_description," +
            "if((select count(1) from collection_info where content_no = #{recruitmentNo} and user_no = #{userNo}),'true','false') as hasCollect " +
            "from company_recruitment " +
            "where recruitment_no = #{recruitmentNo}")
    DetailsOutput details(@Param("userNo")String userNo, @Param("recruitmentNo") String recruitmentNo);

    /**
     * 分页获取我发布的招聘求职
     *
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Select("select recruitment_no,company_logo,company_name,staff_size,(select type_name from position_type where type_no = position_type) as position_type,CONCAT(gold_symbols,job_salary) as job_salary,job_description\n" +
            "from company_recruitment\n" +
            "where user_no = #{userNo}\n" +
            "order by create_time desc\n" +
            "limit #{pageNo},#{pageSize}")
    List<PageQueryListOutput> pageQueryMyCompanyRecruitment(@Param("userNo")String userNo, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);
}
