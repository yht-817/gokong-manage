package cn.gokong.www.gokongmain.service;

import cn.gokong.www.gokongmain.domain.BlacFriendInfo;
import cn.gokong.www.gokongmain.domain.FriendInvite;
import cn.gokong.www.gokongmain.vo.friend.PageQueryFriendOutput;
import cn.gokong.www.gokongmain.vo.group.PageQueryInviteGroupUserOutput;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 好友邀请表 服务类
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
public interface FriendInviteService extends IService<FriendInvite> {

    @Override
    boolean save(FriendInvite friendInvite);

    /**
     * 是否为好友
     *
     * @param userNo    用户编码
     * @param visitorNo 用户编码
     * @return
     */
    boolean isFriend(String userNo, String visitorNo);

    /**
     * 查询当前是否是好友而且是否在申请中
     *
     * @param userNo    用户编码
     * @param visitorNo 用户编码
     * @return
     */
    boolean isFriendss(String userNo, String visitorNo);

    /**
     * 查询当前两个是否在好友的数据库列表中
     *
     * @param userno   用户编码
     * @param friendno 用户编码
     * @return
     */
    boolean isFriends(String userno, String friendno);

    /**
     * 查询我的好友编码列表
     *
     * @param userNo
     * @return
     */
    List<String> selectOneUserNoResult(String userNo);

    /**
     * 分页查询邀请好友列表
     *
     * @param userNo  用户编码
     * @param content 检索内容
     * @param start   开始位置
     * @param end     结束位置
     * @return
     */
    List<PageQueryInviteGroupUserOutput> pageQueryInviteGroupUser(String userNo, String content, Integer start, Integer end);

    /**
     * 申请添加好友
     *
     * @param userNo       申请者编码
     * @param friendUserNo 好友编码
     * @param verifyInfo   验证码消息
     * @return
     */
    boolean applyFriend(String userNo, String friendUserNo, String verifyInfo);

    /**
     * 审核好友申请
     *
     * @param inviteNo   申请编码
     * @param auditState 审核状态
     * @return
     */
    boolean auditFriendApply(String inviteNo, String auditState);

    /**
     * 删除好友
     *
     * @param inviteNo 申请编码
     * @return
     */
    boolean delFriend(String inviteNo);

    /**
     * 查询我的好友
     *
     * @param keyword  检索内容
     * @param userNo   用户编码
     * @param pageNo   开始位置
     * @param pageSize 结束位置
     * @return
     */
    List<PageQueryFriendOutput> pageQueryFriend(String keyword, String userNo, Integer pageNo, Integer pageSize);

    /**
     * 成为好友
     *
     * @param userNo       用户编码
     * @param friendUserNo 好友编码
     * @return
     */
    boolean becomeFriend(String userNo, String friendUserNo);


    List<BlacFriendInfo> queryBlackList(List<Map<String, String>> userBlack);

    /**
     * 查询好友状态
     *
     * @param userNo
     * @param cattelUserNo
     * @param code
     * @return
     */
    boolean isFriendInfo(String userNo, String cattelUserNo, String code);
}
