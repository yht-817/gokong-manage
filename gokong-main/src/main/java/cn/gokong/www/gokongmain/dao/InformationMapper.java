package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.Information;
import cn.gokong.www.gokongmain.vo.user_collection.PageQueryInformationCollectionOutput;
import cn.gokong.www.gokongmain.vo.information.PageQueryInformationHomeOutput;
import cn.gokong.www.gokongmain.vo.information.QueryInformationDetailsOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 资讯信息表 Mapper 接口
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
public interface InformationMapper extends BaseMapper<Information> {

    /**
     * 分页获取我发布的资讯
     *
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Select("select inf.information_no,inf.information_name,inf.information_photo,inf.set_top_state,u_i.user_no,u_i.user_head " +
            "from information inf " +
            "LEFT JOIN user_info u_i ON u_i.user_no = inf.user_no " +
            "where inf.user_no = #{userNo} " +
            "order by inf.release_date " +
            "limit #{pageNo},#{pageSize}")
    List<PageQueryInformationCollectionOutput> pageQueryMyInformation(@Param("userNo")String userNo, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);

    /**
     * 分页查询资讯首页列表
     * @param keyword   关键字
     * @param pageNo    开始位置
     * @param pageSize  检索长度
     * @return
     */
    List<PageQueryInformationHomeOutput> pageQueryHomeInformation(@Param("keyword")String keyword, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);

    /**
     * 查询资讯详情
     * @param informationNo 资讯编码
     * @return
     */
    @Select("select * from information where information_no = #{informationNo}")
    QueryInformationDetailsOutput queryInformationDetails(@Param("informationNo")String informationNo);

    /**
     * 根据权重值修改资讯点击量
     * @param weight        权重值
     * @param informationNo 资讯编码
     * @return
     */
    @Select("update information set click_num = click_num + #{weight} where information_no = #{informationNo}")
    Long updateClickNum(@Param("weight")Integer weight, @Param("informationNo")String informationNo);


    List<PageQueryInformationHomeOutput> pageQueryHomeFansInformation(@Param("keyword")String keyword, @Param("userNo")String userNo, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);
}
