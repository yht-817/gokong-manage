package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.UsedDeal;
import cn.gokong.www.gokongmain.vo.used_deal.PageQueryListOutput;
import cn.gokong.www.gokongmain.vo.used_deal.QueryDetailsOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 二手交易 Mapper 接口
 * </p>
 *
 * @author ikook
 * @since 2018-10-01
 */
public interface UsedDealMapper extends BaseMapper<UsedDeal> {

    /**
     * 分页查询二手交易列表
     *
     * @param cityName 所在城市
     * @param typeNo   类型编码
     * @param keyWord  检索内容
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    List<PageQueryListOutput> pageQueryList(@Param("cityName")String cityName, @Param("typeNo") String typeNo, @Param("keyWord") String keyWord, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    /**
     * 查询二手交易详情
     *
     * @param userNo 用户编码
     * @param usedNo 二手交易编码
     * @return
     */
    @Select("select ui.user_no,ui.user_head,ui.nick_name,ud.used_no,ud.used_content,ud.used_img,create_time,CONCAT(ud.gold_symbols,ud.price) as price,ud.used_title," +
            "if((select count(1) from collection_info where content_no = #{usedNo} and user_no = #{userNo}),'true','false') as collection " +
            "from used_deal ud " +
            "left join user_info ui on ui.user_no = ud.user_no " +
            "where ud.used_no = #{usedNo} "
    )
    QueryDetailsOutput queryDetails(@Param("userNo")String userNo, @Param("usedNo")String usedNo);

    /**
     * 分页查询我发布的二手交易
     *
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Select("select ui.user_no,ui.user_head,ui.nick_name,ud.used_no,ud.used_title,ud.used_content,SUBSTRING_INDEX(ud.used_img,',',1) as used_img,ud.browse_num,ud.comment_num,CONCAT(ud.gold_symbols,ud.price) as price,ud.create_time\n" +
            "from used_deal ud\n" +
            "left join user_info ui on ui.user_no = ud.user_no\n" +
            "where ud.user_no = #{userNo}\n" +
            "order by ud.create_time desc\n" +
            "limit #{pageNo},#{pageSize}")
    List<PageQueryListOutput> pageQueryMyUsedDeal(@Param("userNo")String userNo, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);
}
