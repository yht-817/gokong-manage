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
 * @since 2019-01-09
 */
@TableName("user_pay_detail")
public class UserPayDetail implements Serializable {

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
     * 支付的订单号
     */
    private String payNo;

    /**
     * 支付方式
     */
    private String payModeNo;

    /**
     * 支付金额
     */
    private BigDecimal payAmount;

    /**
     * 支付状态
     */
    private Integer payState;

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

    public String getPayModeNo() {
        return payModeNo;
    }

    public void setPayModeNo(String payModeNo) {
        this.payModeNo = payModeNo;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public Integer getPayState() {
        return payState;
    }

    public void setPayState(Integer payState) {
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
        return "UserPayDetail{" +
                "id=" + id +
                ", userNo=" + userNo +
                ", payNo=" + payNo +
                ", payModeNo=" + payModeNo +
                ", payAmount=" + payAmount +
                ", payState=" + payState +
                ", payDate=" + payDate +
                "}";
    }
}
