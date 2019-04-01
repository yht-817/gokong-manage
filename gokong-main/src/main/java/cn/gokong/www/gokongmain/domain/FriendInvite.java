package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 好友邀请表
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@TableName("friend_invite")
public class FriendInvite implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 好友编码
     */
    private String inviteNo;

    /**
     * 用户编码
     */
    private String userNo;

    /**
     * 好友用户编码
     */
    private String friendUserNo;

    /**
     * 时间
     */
    private Date inviteDate;

    /**
     * 验证信息
     */
    private String verifyInfo;

    /**
     * 状态 1015
     */
    private String inviteState;

    /**
     * 变更时间
     */
    private Date changeDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInviteNo() {
        return inviteNo;
    }

    public void setInviteNo(String inviteNo) {
        this.inviteNo = inviteNo;
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

    public Date getInviteDate() {
        return inviteDate;
    }

    public void setInviteDate(Date inviteDate) {
        this.inviteDate = inviteDate;
    }

    public String getVerifyInfo() {
        return verifyInfo;
    }

    public void setVerifyInfo(String verifyInfo) {
        this.verifyInfo = verifyInfo;
    }

    public String getInviteState() {
        return inviteState;
    }

    public void setInviteState(String inviteState) {
        this.inviteState = inviteState;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    @Override
    public String toString() {
        return "FriendInvite{" +
        "id=" + id +
        ", inviteNo=" + inviteNo +
        ", userNo=" + userNo +
        ", friendUserNo=" + friendUserNo +
        ", inviteDate=" + inviteDate +
        ", verifyInfo=" + verifyInfo +
        ", inviteState=" + inviteState +
        ", changeDate=" + changeDate +
        "}";
    }
}
