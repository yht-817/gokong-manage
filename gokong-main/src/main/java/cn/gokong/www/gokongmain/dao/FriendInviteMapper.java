package cn.gokong.www.gokongmain.dao;

import cn.gokong.www.gokongmain.domain.BlacFriendInfo;
import cn.gokong.www.gokongmain.domain.FriendInvite;
import cn.gokong.www.gokongmain.vo.friend.PageQueryFriendOutput;
import cn.gokong.www.gokongmain.vo.group.PageQueryInviteGroupUserOutput;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 好友邀请表 Mapper 接口
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
public interface FriendInviteMapper extends BaseMapper<FriendInvite> {

    /**
     * 查询好友记录
     *
     * @param userNo       用户编码
     * @param friendUserNo 用户编码
     * @return
     */
    @Select("select * from friend_invite where user_no = #{userNo} and friend_user_no = #{friendUserNo}")
    FriendInvite findByUserNoAndFriendUserNo(@Param("userNo") String userNo, @Param("friendUserNo") String friendUserNo);

    /**
     * 根据用户编码和状态查询好友信息
     *
     * @param userNo      用户编码
     * @param inviteState 好友状态
     * @return
     */
    @Select("select friend_user_no from friend_invite where user_no = #{userNo} and invite_state = #{inviteState}")
    List<String> findByUserNoAndInviteState(@Param("userNo") String userNo, @Param("inviteState") String inviteState);

    /**
     * 分页查询邀请好友列表
     *
     * @param userNo  用户编码
     * @param content 检索内容
     * @param start   开始位置
     * @param end     结束位置
     * @return
     */
    List<PageQueryInviteGroupUserOutput> pageQueryInviteGroupUser(@Param("userNo") String userNo, @Param("keyword") String content, @Param("start") Integer start, @Param("end") Integer end);

    /**
     * 根据用户编码和好友编码和好友状态查询好友信息
     *
     * @param userNo       用户编码
     * @param friendUserNo 好友编码
     * @param friendInvite 好友状态
     * @return
     */
    @Select("select * from friend_invite where user_no = #{userNo} and friend_user_no = #{friendUserNo} and invite_state = #{friendInvite}")
    FriendInvite findByUserNoAndFriendUserNoAndInviteState(@Param("userNo") String userNo, @Param("friendUserNo") String friendUserNo, @Param("friendInvite") String friendInvite);


    /**
     * 查询当前是否是好友而且是否在申请中
     *
     * @param userNo       用户编码
     * @param friendUserNo 好友编码
     * @param friendInvite 好友状态
     * @return
     */
    @Select("select * from friend_invite where user_no = #{userNo} and friend_user_no = #{friendUserNo} and invite_state != #{friendInvite}")
    FriendInvite findByUserNoAndFriendUserNoAndInviteStates(@Param("userNo") String userNo, @Param("friendUserNo") String friendUserNo, @Param("friendInvite") String friendInvite);


    /**
     * 查询当前两个数据在数据列表中（不管是否同意）
     *
     * @param userno
     * @param friendno
     * @return
     */
    @Select("select * from friend_invite where user_no = #{userno} and friend_user_no = #{friendno} and invite_state != 10150003")
    FriendInvite findByUserNoAndFriendUserNoAndInviteStateS(@Param("userno") String userno, @Param("friendno") String friendno);

    @Select("select * from friend_invite where invite_no = #{inviteNo}")
    FriendInvite findByInviteNo(@Param("inviteNo") String inviteNo);

    /**
     * 查询我的好友
     *
     * @param keyword
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 结束位置
     * @return
     */

    List<PageQueryFriendOutput> pageQueryFriend(@Param("keyword") String keyword, @Param("userNo") String userNo, @Param("pageNo") Integer pageNo, @Param("pageSize") Integer pageSize);

    @Select("delete from friend_invite where (user_no = #{userNo} and friend_user_no = #{friendUserNo}) or (friend_user_no = #{userNo} and user_no = #{friendUserNo})")
    Integer delFriend(@Param("userNo") String userNo, @Param("friendUserNo") String friendUserNo);


    @Select("SELECT pay_amount FROM user_pay_detail WHERE pay_no = #{verifyInfo} AND pay_state = 1")
    Map<String, Object> queryMoney(@Param("verifyInfo") String verifyInfo);

    List<BlacFriendInfo> queryBlackList(@Param("listdata") List<String> listdata);
}
