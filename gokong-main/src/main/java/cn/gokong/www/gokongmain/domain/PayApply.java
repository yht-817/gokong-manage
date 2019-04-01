package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author tom
 * @since 2018-09-20
 */
@TableName("pay_apply")
public class PayApply implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 用户编码
     */
    private String userNo;

    /**
     * 申请编码
     */
    private String applyNo;

    /**
     * 申请金额
     */
    private BigDecimal applyAmount;

    /**
     * 执行状态
     */
    private String executionStat;

    /**
     * 申请时间
     */
    private Date changeDate;

    /**
     * 银行卡账号
     */
    private String userAccount;

    /**
     * 电话号码
     */
    private String userPhone;

    /**
     * 开户行姓名
     */
    private String userName;

    /**
     * 开户行地址
     */
    private String userAddres;

    /**
     * 开户行名称
     */
    private String bankName;


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

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public BigDecimal getApplyAmount() {
        return applyAmount;
    }

    public void setApplyAmount(BigDecimal applyAmount) {
        this.applyAmount = applyAmount;
    }

    public String getExecutionStat() {
        return executionStat;
    }

    public void setExecutionStat(String executionStat) {
        this.executionStat = executionStat;
    }

    public Date getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = changeDate;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserAddres() {
        return userAddres;
    }

    public void setUserAddres(String userAddres) {
        this.userAddres = userAddres;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    @Override
    public String toString() {
        return "PayApply{" +
                "id=" + id +
                ", userNo=" + userNo +
                ", applyNo=" + applyNo +
                ", applyAmount=" + applyAmount +
                ", executionStat=" + executionStat +
                ", changeDate=" + changeDate +
                ", userAccount=" + userAccount +
                ", userPhone=" + userPhone +
                ", userName=" + userName +
                ", userAddres=" + userAddres +
                ", bankName=" + bankName +
                "}";
    }
}
