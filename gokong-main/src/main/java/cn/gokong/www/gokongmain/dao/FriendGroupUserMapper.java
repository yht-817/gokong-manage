package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.FriendGroupUser;
import cn.gokong.www.gokongmain.vo.group.PageQueryUserGroupOutput;
import cn.gokong.www.gokongmain.vo.group.QueryGroupUserOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;


/**
 * <p>
 * 好友群用户信息 Mapper 接口
 * </p>
 *
 * @author ikook
 * @since 2018-09-13
 */
public interface FriendGroupUserMapper extends BaseMapper<FriendGroupUser> {

    List<Map<String, String>> findUserGroup(@Param(value = "pageno") int pageno, @Param(value = "cityno") String cityno, @Param(value = "userno") String userno);


    /*@Select("delete from friend_group_user where group_no = #{groupNo} and user_no in (#{userNos})")*/
    Integer deleteBatchGroupUser(@Param("groupNo")String groupNo, @Param("userNos")List<String> userNos);

    @Select("select * from friend_group_user where group_no = #{groupNo} and user_no = #{userNo} and group_user_state = #{groupUserState}")
    FriendGroupUser findByGroupNoAndUserNoAndGroupUserState(@Param("groupNo") String groupNo, @Param("userNo") String userNo, @Param("groupUserState") String groupUserState);


    /**
     * 分页查询群用户信息
     * @param groupNo   群编码
     * @param keyword   检索内容
     * @param start     开始位置
     * @param end       检索长度
     * @return
     */
    List<QueryGroupUserOutput> pageQueryGroupUsers(@Param("groupNo")String groupNo, @Param("keyword")String keyword, @Param("start")int start, @Param("end")int end);

    /**
     * 根据群编码和状态查询群用户编码
     * @param groupNo           群编码
     * @param groupUserState    在群状态
     * @return
     */
    @Select("select user_no from friend_group_user where group_no = #{groupNo} and group_user_state = #{groupUserState}")
    List<String> findByGroupNoAndGroupUserState(@Param("groupNo")String groupNo, @Param("groupUserState")String groupUserState);

    @Select("select * from friend_group_user where invite_no = #{inviteNo}")
    FriendGroupUser findByInviteNo(@Param("inviteNo")String inviteNo);

    /**
     * 分页查询用户群列表
     *
     * @param keyword        检索内容
     * @param userNo         用户编码
     * @param groupUserState 在群状态
     * @param pageNo         开始位置
     * @param pageSize       检索长度
     * @return
     */
    List<PageQueryUserGroupOutput> pageQueryUserGroup(@Param("keyword")String keyword, @Param("userNo") String userNo, @Param("groupUserState") String groupUserState, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    /**
     * 根据用户编码查询加入的群
     *
     * @param userNo         用户编码
     * @param groupUserState 群状态
     * @return
     */
    @Select("select fg.group_name " +
            "from friend_group fg " +
            "where fg.group_no in (select group_no from friend_group_user where user_no = #{userNo} and group_user_state = #{groupUserState})")
    List<Map<String, String>> queryJoinGroup(@Param("userNo")String userNo, @Param("groupUserState")String groupUserState);

    @Select("select * from friend_group_user WHERE group_no = #{groupNo} and user_no = #{userNo}")
    FriendGroupUser findByGroupNoAndUserNo(@Param("groupNo")String groupNo, @Param("userNo")String userNo);
}
