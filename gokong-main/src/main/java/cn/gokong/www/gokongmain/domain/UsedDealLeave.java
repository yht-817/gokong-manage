package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 二手交易留言
 * </p>
 *
 * @author ikook
 * @since 2018-10-01
 */
@TableName("used_deal_leave")
public class UsedDealLeave implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 留言编码
     */
    private String leaveNo;

    /**
     * 二手交易编码
     */
    private String usedNo;

    /**
     * 评论者用户编码
     */
    private String fromUserNo;

    /**
     * 评论内容
     */
    private String leaveContent;

    /**
     * 被评论者用户编码
     */
    private String toUserNo;

    /**
     * 评论时间
     */
    private Date leaveTime;

    public String getLeaveNo() {
        return leaveNo;
    }

    public void setLeaveNo(String leaveNo) {
        this.leaveNo = leaveNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsedNo() {
        return usedNo;
    }

    public void setUsedNo(String usedNo) {
        this.usedNo = usedNo;
    }

    public String getFromUserNo() {
        return fromUserNo;
    }

    public void setFromUserNo(String fromUserNo) {
        this.fromUserNo = fromUserNo;
    }

    public String getLeaveContent() {
        return leaveContent;
    }

    public void setLeaveContent(String leaveContent) {
        this.leaveContent = leaveContent;
    }

    public String getToUserNo() {
        return toUserNo;
    }

    public void setToUserNo(String toUserNo) {
        this.toUserNo = toUserNo;
    }

    public Date getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = leaveTime;
    }

    @Override
    public String toString() {
        return "UsedDealLeave{" +
        "id=" + id +
        ", usedNo=" + usedNo +
        ", fromUserNo=" + fromUserNo +
        ", leaveContent=" + leaveContent +
        ", toUserNo=" + toUserNo +
        ", leaveTime=" + leaveTime +
        "}";
    }
}
