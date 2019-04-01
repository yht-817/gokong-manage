package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.CollectionInfo;
import cn.gokong.www.gokongmain.vo.chinese_circle.PageQueryChineseCircleOutput;
import cn.gokong.www.gokongmain.vo.chioce.PageQueryChoiceOutput;
import cn.gokong.www.gokongmain.vo.used_deal.PageQueryListOutput;
import cn.gokong.www.gokongmain.vo.user_collection.PageQueryInformationCollectionOutput;
import cn.gokong.www.gokongmain.vo.merchants.PageQueryMerchantsOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 收藏信息表 Mapper 接口
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
public interface CollectionInfoMapper extends BaseMapper<CollectionInfo> {

    /**
     * 查询用户收藏信息
     * @param userNo            用户编码
     * @param contentNo         收藏编码
     * @return
     */
    List<CollectionInfo> findByUserNoAndContentNo(@Param("userNo")String userNo,@Param("contentNo")String contentNo);


    /**
     * 根据用户编码和收藏类型查询用户收藏列表
     * @param userNo            用户编码
     * @param collectionType    收藏类型
     * @return
     */
    List<CollectionInfo> findByUserNoAndCollectionType(@Param("userNo")String userNo,@Param("collectionType")String collectionType);


    /**
     * 分页查询资讯收藏列表
     *
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Select("select inf.information_no,inf.information_name,inf.information_photo,inf.set_top_state,u_i.user_no,u_i.user_head,u_i.nick_name " +
            "from information inf " +
            "LEFT JOIN user_info u_i ON u_i.user_no = inf.user_no " +
            "where inf.information_no in (select content_no from collection_info where collection_type = '"+10110001+"' and user_no = #{userNo}) " +
            "limit #{pageNo},#{pageSize}")
    List<PageQueryInformationCollectionOutput> pageQueryInformationCollection(@Param("userNo")String userNo, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);


    /**
     * 分页查询精选收藏列表
     *
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Select("select  c_i.resource_no,c_i.resource_name,c_i.banner_photo,CONCAT_WS(' 元/',c_i.pay_amount,c_i.unit) as pay_amount,c_i.cost_amount " +
            "from choice_info c_i " +
            "where c_i.resource_no in (select content_no from collection_info where collection_type = '" + 10110002 + "' and user_no = #{userNo}) " +
            "limit #{pageNo},#{pageSize}")
    List<PageQueryChoiceOutput> pageQueryChoiceCollection(@Param("userNo")String userNo, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);

    /**
     * 分页查询商户收藏列表
     *
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Select("select merchants_no,merchants_name,title_img,address " +
            "from merchants " +
            "where merchants_no in (select content_no from collection_info where collection_type = '" + 10110003 + "' and user_no = #{userNo}) " +
            "limit #{pageNo},#{pageSize}")
    List<PageQueryMerchantsOutput> pageQueryMerchantsCollection(@Param("userNo")String userNo, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);


    /**
     * 分页获取华人圈收藏列表
     *
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Select("select cc.circle_no,cc.circle_img,cc.instructions,ui.user_no,ui.user_head,ui.nick_name " +
            "from chinese_circle cc " +
            "left join user_info ui on ui.user_no = cc.user_no " +
            "where cc.circle_no in (select content_no from collection_info where collection_type = '"+10110004+"' and user_no = #{userNo}) " +
            "limit #{pageNo},#{pageSize}")
    List<PageQueryChineseCircleOutput> pageQueryChineseCircleCollection(@Param("userNo")String userNo, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);


    @Select("select ud.used_no,SUBSTRING_INDEX(ud.used_img,',',1) as used_img,ud.used_content,ui.user_no,ui.user_head,ui.nick_name,CONCAT(ud.gold_symbols,ud.price) as price,ud.create_time,ud.browse_num " +
            "from used_deal ud " +
            "left join user_info ui on ui.user_no = ud.user_no " +
            "where ud.used_no in (select content_no from collection_info where collection_type = '" + 10110005 + "' and user_no = #{userNo}) " +
            "limit #{pageNo},#{pageSize}")
    List<PageQueryListOutput> pageQueryUsedDealCollection(@Param("userNo")String userNo, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);

    @Select("select recruitment_no,company_logo,company_name,staff_size,(select type_name from position_type where type_no = position_type) as position_type,CONCAT(gold_symbols,job_salary) as job_salary,job_description\n" +
            "from company_recruitment\n" +
            "where recruitment_no in (select content_no from collection_info where collection_type = '" + 10110006 + "' and user_no = #{userNo}) " +
            "limit #{pageNo},#{pageSize}")
    List<cn.gokong.www.gokongmain.vo.company_recruitment.PageQueryListOutput> pageQueryCompanyRecruitmentCollection(@Param("userNo")String userNo, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);
}
