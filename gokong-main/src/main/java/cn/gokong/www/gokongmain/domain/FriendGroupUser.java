package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

;

/**
 * <p>
 * 好友群用户信息
 * </p>
 *
 * @author ikook
 * @since 2018-09-13
 */
@TableName("friend_group_user")
public class FriendGroupUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 群编码
     */
    private String groupNo;

    /**
     * 被邀请者用户编码
     */
    private String userNo;

    /**
     * 邀请者用户编码
     */
    private String friendUserNo;

    /**
     * 邀请单号
     */
    private String inviteNo;

    /**
     * 在群状态 1017
     */
    private String groupUserState;

    /**
     * 加入时间
     */
    private Date joinDate;

    /**
     * 变更时间
     */
    private Date changeDate;

    /**
     * 验证消息
     */
    private String verifyInfo;

    public String getVerifyInfo() {
        return verifyInfo;
    }

    public void setVerifyInfo(String verifyInfo) {
        this.verifyInfo = verifyInfo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getFriendUserNo() {
        return friendUserNo;
    }

    public void setFriendUserNo(String friendUserNo) {
        this.friendUserNo = friendUserNo;
    }

    public String getInviteNo() {
        return inviteNo;
    }

    public void setInviteNo(String inviteNo) {
        this.inviteNo = inviteNo;
    }

    public String getGroupUserState() {
        return groupUserState;
    }

    public void setGroupUserState(String groupUserState) {
        this.groupUserState = groupUserState;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    @Override
    public String toString() {
        return "FriendGroupUser{" +
                "id=" + id +
                ", groupNo=" + groupNo +
                ", userNo=" + userNo +
                ", friendUserNo=" + friendUserNo +
                ", inviteNo=" + inviteNo +
                ", groupUserState=" + groupUserState +
                ", joinDate=" + joinDate +
                ", changeDate=" + changeDate +
                "}";
    }
}
