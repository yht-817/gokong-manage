package cn.gokong.www.gokongmain.vo.chioce;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * <p>
 * 精选支付的信息
 * </p>
 *
 * @author mick
 * @since 2018-08-02
 */
public class ChoiceInfoPayRecharge {

    @NotNull(message = "参数不能为空")
    private BigDecimal amount;
    @NotNull(message = "参数不能为空")
    private String title;
    @NotNull(message = "参数不能为空")
    private String userNo;
    @NotNull(message = "参数不能为空")
    private Integer payType;
    @NotNull(message = "参数不能为空")
    private BigDecimal peachNumber;
    @NotNull(message = "参数不能为空")
    private BigDecimal givpeachNumbe;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public BigDecimal getPeachNumber() {
        return peachNumber;
    }

    public void setPeachNumber(BigDecimal peachNumber) {
        this.peachNumber = peachNumber;
    }

    public BigDecimal getGivpeachNumbe() {
        return givpeachNumbe;
    }

    public void setGivpeachNumbe(BigDecimal givpeachNumbe) {
        this.givpeachNumbe = givpeachNumbe;
    }
}
