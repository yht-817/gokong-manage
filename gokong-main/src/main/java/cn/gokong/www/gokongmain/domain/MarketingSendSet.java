package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 营销赠送设置表
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@TableName("marketing_send_set")
public class MarketingSendSet implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 赠送单号
     */
    private String sendNo;

    /**
     * 赠送类型 1026
     */
    private String sendType;

    /**
     * 赠送金额
     */
    private BigDecimal sendAmount;

    /**
     * 开始日期
     */
    private Date sendBeginDate;

    /**
     * 结束日期
     */
    private Date sendEndDate;

    /**
     * 制单日期
     */
    private Date operatorDate;

    /**
     * 平台用户编码
     */
    private String userNoPlatform;

    /**
     * 平台用户名称
     */
    private String userNamePlatform;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSendNo() {
        return sendNo;
    }

    public void setSendNo(String sendNo) {
        this.sendNo = sendNo;
    }

    public String getSendType() {
        return sendType;
    }

    public void setSendType(String sendType) {
        this.sendType = sendType;
    }

    public BigDecimal getSendAmount() {
        return sendAmount;
    }

    public void setSendAmount(BigDecimal sendAmount) {
        this.sendAmount = sendAmount;
    }

    public Date getSendBeginDate() {
        return sendBeginDate;
    }

    public void setSendBeginDate(Date sendBeginDate) {
        this.sendBeginDate = sendBeginDate;
    }

    public Date getSendEndDate() {
        return sendEndDate;
    }

    public void setSendEndDate(Date sendEndDate) {
        this.sendEndDate = sendEndDate;
    }

    public Date getOperatorDate() {
        return operatorDate;
    }

    public void setOperatorDate(Date operatorDate) {
        this.operatorDate = operatorDate;
    }

    public String getUserNoPlatform() {
        return userNoPlatform;
    }

    public void setUserNoPlatform(String userNoPlatform) {
        this.userNoPlatform = userNoPlatform;
    }

    public String getUserNamePlatform() {
        return userNamePlatform;
    }

    public void setUserNamePlatform(String userNamePlatform) {
        this.userNamePlatform = userNamePlatform;
    }

    @Override
    public String toString() {
        return "MarketingSendSet{" +
        "id=" + id +
        ", sendNo=" + sendNo +
        ", sendType=" + sendType +
        ", sendAmount=" + sendAmount +
        ", sendBeginDate=" + sendBeginDate +
        ", sendEndDate=" + sendEndDate +
        ", operatorDate=" + operatorDate +
        ", userNoPlatform=" + userNoPlatform +
        ", userNamePlatform=" + userNamePlatform +
        "}";
    }
}
