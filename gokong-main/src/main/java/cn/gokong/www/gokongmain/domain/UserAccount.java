package cn.gokong.www.gokongmain.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户账户表
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
@TableName("user_account")
public class UserAccount implements Serializable {

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
     * 账户金额
     */
    private BigDecimal accountAmount;

    /**
     * 冻结账户金额
     */
    private BigDecimal forzenAccountAmount;

    /**
     * 提现账户金额
     */
    private BigDecimal cashAccountAmount;


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

    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }

    public BigDecimal getForzenAccountAmount() {
        return forzenAccountAmount;
    }

    public void setForzenAccountAmount(BigDecimal forzenAccountAmount) {
        this.forzenAccountAmount = forzenAccountAmount;
    }

    public BigDecimal getCashAccountAmount() {
        return cashAccountAmount;
    }

    public void setCashAccountAmount(BigDecimal cashAccountAmount) {
        this.cashAccountAmount = cashAccountAmount;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
        "id=" + id +
        ", userNo=" + userNo +
        ", accountAmount=" + accountAmount +
        ", forzenAccountAmount=" + forzenAccountAmount +
        ", cashAccountAmount=" + cashAccountAmount +
        "}";
    }
}
