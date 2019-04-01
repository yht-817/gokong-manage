package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 好友群信息
 * </p>
 *
 * @author tom
 * @since 2018-09-12
 */
@TableName("friend_group")
public class FriendGroup implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 群编码
     */
    private String groupNo;

    /**
     * 群名称
     */
    private String groupName;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 创建人
     */
    private String userNo;

    /**
     * 群公告
     */
    private String groupNode;

    /**
     * 群状态 1016
     */
    private String groupState;

    /**
     * 是否收费群 1034
     */
    private String ifPayGroup;

    /**
     * 群头像
     */
    private String groupHead;

    /**
     * 收费金额
     */
    private BigDecimal payAmount;

    /**
     * 城市编码
     */
    private String cityNo;

    /**
     * 手动控制排序
     */
    private Integer groupSort;

    /**
     * 加入群标示（是需要同意加入）
     */
    private boolean joinMark;

    /**
     * 群类型
     */
    private String groupType;

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

    public boolean isJoinMark() {
        return joinMark;
    }

    public void setJoinMark(boolean joinMark) {
        this.joinMark = joinMark;
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getGroupNode() {
        return groupNode;
    }

    public void setGroupNode(String groupNode) {
        this.groupNode = groupNode;
    }

    public String getGroupState() {
        return groupState;
    }

    public void setGroupState(String groupState) {
        this.groupState = groupState;
    }

    public String getIfPayGroup() {
        return ifPayGroup;
    }

    public void setIfPayGroup(String ifPayGroup) {
        this.ifPayGroup = ifPayGroup;
    }

    public String getGroupHead() {
        return groupHead;
    }

    public void setGroupHead(String groupHead) {
        this.groupHead = groupHead;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }

    public Integer getGroupSort() {
        return groupSort;
    }

    public void setGroupSort(Integer groupSort) {
        this.groupSort = groupSort;
    }

    @Override
    public String toString() {
        return "FriendGroup{" +
                "id='" + id + '\'' +
                ", groupNo='" + groupNo + '\'' +
                ", groupName='" + groupName + '\'' +
                ", createDate=" + createDate +
                ", userNo='" + userNo + '\'' +
                ", groupNode='" + groupNode + '\'' +
                ", groupState='" + groupState + '\'' +
                ", ifPayGroup='" + ifPayGroup + '\'' +
                ", groupHead='" + groupHead + '\'' +
                ", payAmount=" + payAmount +
                ", cityNo='" + cityNo + '\'' +
                ", groupSort=" + groupSort +
                '}';
    }
}
