package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.FriendGroup;
import cn.gokong.www.gokongmain.vo.group.PageQueryHotGroupOutput;
import cn.gokong.www.gokongmain.vo.group.PageSearchGroupOutput;
import cn.gokong.www.gokongmain.vo.group.QueryGroupDetailsOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 好友群信息 Mapper 接口
 * </p>
 *
 * @author tom
 * @since 2018-09-12
 */
public interface FriendGroupMapper extends BaseMapper<FriendGroup> {

    List<Map<String, String>> getHots(@Param(value = "cityNo") String cityNo);

    List<Map<String, String>> findPageGropy(@Param(value = "cityno") String cityno, @Param(value = "pageno") int pageno, @Param(value = "userno") String userno);

    /**
     * 查询群详情
     *
     * @param groupNo 群编码
     * @param userNo
     * @return
     */
    @Select("select group_no,group_name,create_date,user_no,group_node,group_head,(select count(1) from friend_group_user where group_user_state = '10170001' and group_no = #{groupNo}) as group_count,join_mark," +
            "(select group_user_state from friend_group_user fgu where fgu.group_no = #{groupNo} and fgu.user_no = #{userNo}) as applyState,group_type " +
            "from friend_group " +
            "where group_no = #{groupNo}")
    QueryGroupDetailsOutput queryGroupDetails(@Param("groupNo") String groupNo, @Param("userNo")String userNo);


    /**
     * 分页检索群
     *
     * @param userNo   用户编码
     * @param keyword  检索内容
     * @param cityName 城市名称
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    List<PageSearchGroupOutput> pageSearchGroup(@Param("userNo") String userNo, @Param("keyword") String keyword, @Param("cityName")String cityName, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    /**
     * 分页查询更多热门群
     *
     * @param userNo   用户编码
     * @param cityName 城市名称
     * @param pageNo   开始位置
     * @param pageSize 检索长度
     * @return
     */
    @Select("select fg.group_name,fg.group_head,fg.group_no,(select count(1) from friend_group_user fgu where fgu.group_no = fg.group_no and fgu.group_user_state = '10170001') as personCount," +
            "(select group_user_state from friend_group_user fgu where fgu.group_no = fg.group_no and fgu.user_no = #{userNo}) as applyState " +
            "from friend_group fg " +
            "where fg.city_no = #{cityName} " +
            "and fg.group_state = '10160001' " +
            "and fg.group_type = '1' " +
            "and fg.group_no not in (select fgu.group_no from friend_group_user fgu where fgu.user_no = #{userNo}and fgu.group_user_state = '10170001') " +
            "limit #{pageNo},#{pageSize}")
    List<PageQueryHotGroupOutput> pageQueryHotGroup(@Param("userNo")String userNo, @Param("cityName")String cityName, @Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);
}
