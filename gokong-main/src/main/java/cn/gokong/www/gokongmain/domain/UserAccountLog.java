package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户账户日志表
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
@TableName("user_account_log")
public class UserAccountLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 用户编码
     */
    private String userNo;

    /**
     * 变动方式 1005
     */
    private String changeMode;

    /**
     * 变动金额
     */
    private BigDecimal changeAmount;

    /**
     * 变动日期
     */
    private Date changeDate;

    /**
     * 变动单号
     */
    private String changeNo;

    /**
     * 变动说明
     */
    private String changeRemark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getChangeMode() {
        return changeMode;
    }

    public void setChangeMode(String changeMode) {
        this.changeMode = changeMode;
    }

    public BigDecimal getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getChangeNo() {
        return changeNo;
    }

    public void setChangeNo(String changeNo) {
        this.changeNo = changeNo;
    }

    public String getChangeRemark() {
        return changeRemark;
    }

    public void setChangeRemark(String changeRemark) {
        this.changeRemark = changeRemark;
    }

    @Override
    public String toString() {
        return "UserAccountLog{" +
        "id=" + id +
        ", userNo=" + userNo +
        ", changeMode=" + changeMode +
        ", changeAmount=" + changeAmount +
        ", changeDate=" + changeDate +
        ", changeNo=" + changeNo +
        ", changeRemark=" + changeRemark +
        "}";
    }
}
