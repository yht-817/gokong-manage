package cn.gokong.www.gokongmain.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 平台账户金额
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@TableName("platform_account")
public class PlatformAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 账户金额
     */
    private BigDecimal accountAmount;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }

    @Override
    public String toString() {
        return "PlatformAccount{" +
        "id=" + id +
        ", accountAmount=" + accountAmount +
        "}";
    }
}
