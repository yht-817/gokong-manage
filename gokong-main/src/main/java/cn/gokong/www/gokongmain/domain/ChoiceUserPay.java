package cn.gokong.www.gokongmain.domain;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableName;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户精选支付信息
 * </p>
 *
 * @author tom
 * @since 2018-09-17
 */
@TableName("choice_user_pay")
public class ChoiceUserPay implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 所属用户编码
     */
    private String userNo;

    /**
     * 购买单号
     */
    private String payNo;

    /**
     * 精选类型 1035
     */
    private String choiceType;

    /**
     * 资源编码
     */
    private String resourceNo;

    private BigDecimal payAmountWukb;

    private String wukbCase;

    /**
     * 购买张数
     */
    private Integer payNum;

    /**
     * 支付金额
     */
    private BigDecimal payAmount;

    /**
     * 支付状态 1038
     */
    private String payState;

    /**
     * 支付日期
     */
    private Date payDate;


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

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getChoiceType() {
        return choiceType;
    }

    public void setChoiceType(String choiceType) {
        this.choiceType = choiceType;
    }

    public String getResourceNo() {
        return resourceNo;
    }

    public void setResourceNo(String resourceNo) {
        this.resourceNo = resourceNo;
    }

    public BigDecimal getPayAmountWukb() {
        return payAmountWukb;
    }

    public void setPayAmountWukb(BigDecimal payAmountWukb) {
        this.payAmountWukb = payAmountWukb;
    }

    public String getWukbCase() {
        return wukbCase;
    }

    public void setWukbCase(String wukbCase) {
        this.wukbCase = wukbCase;
    }

    public Integer getPayNum() {
        return payNum;
    }

    public void setPayNum(Integer payNum) {
        this.payNum = payNum;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayState() {
        return payState;
    }

    public void setPayState(String payState) {
        this.payState = payState;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    @Override
    public String toString() {
        return "ChoiceUserPay{" +
                "id=" + id +
                ", userNo=" + userNo +
                ", payNo=" + payNo +
                ", choiceType=" + choiceType +
                ", resourceNo=" + resourceNo +
                ", payAmountWukb=" + payAmountWukb +
                ", wukbCase=" + wukbCase +
                ", payNum=" + payNum +
                ", payAmount=" + payAmount +
                ", payState=" + payState +
                ", payDate=" + payDate +
                "}";
    }
}
