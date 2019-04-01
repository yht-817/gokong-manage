package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 用户充值记录表
 * </p>
 *
 * @author tom
 * @since 2018-09-19
 */
@TableName("user_recharge")
public class UserRecharge implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 用户编码
     */
    private String userNo;

    /**
     * 充值单号
     */
    private String rechargeNo;

    /**
     * 购买数量
     */
    private BigDecimal payNum;

    /**
     * 赠送数量
     */
    private BigDecimal sendNum;

    /**
     * 充值金额
     */
    private BigDecimal rechargeAmount;

    /**
     * 充值日期
     */
    private Date rechargeDate;

    /**
     * 支付方式 1006
     */
    private Integer payNo;

    /**
     * 充值状态 1013
     */
    private String rechargeState;

    private String payBillNo;


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

    public String getRechargeNo() {
        return rechargeNo;
    }

    public void setRechargeNo(String rechargeNo) {
        this.rechargeNo = rechargeNo;
    }

    public BigDecimal getPayNum() {
        return payNum;
    }

    public void setPayNum(BigDecimal payNum) {
        this.payNum = payNum;
    }

    public BigDecimal getSendNum() {
        return sendNum;
    }

    public void setSendNum(BigDecimal sendNum) {
        this.sendNum = sendNum;
    }

    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public Date getRechargeDate() {
        return rechargeDate;
    }

    public void setRechargeDate(Date rechargeDate) {
        this.rechargeDate = rechargeDate;
    }

    public Integer getPayNo() {
        return payNo;
    }

    public void setPayNo(Integer payNo) {
        this.payNo = payNo;
    }

    public String getRechargeState() {
        return rechargeState;
    }

    public void setRechargeState(String rechargeState) {
        this.rechargeState = rechargeState;
    }

    public String getPayBillNo() {
        return payBillNo;
    }

    public void setPayBillNo(String payBillNo) {
        this.payBillNo = payBillNo;
    }

    @Override
    public String toString() {
        return "UserRecharge{" +
                "id=" + id +
                ", userNo=" + userNo +
                ", rechargeNo=" + rechargeNo +
                ", payNum=" + payNum +
                ", sendNum=" + sendNum +
                ", rechargeAmount=" + rechargeAmount +
                ", rechargeDate=" + rechargeDate +
                ", payNo=" + payNo +
                ", rechargeState=" + rechargeState +
                ", payBillNo=" + payBillNo +
                "}";
    }
}
