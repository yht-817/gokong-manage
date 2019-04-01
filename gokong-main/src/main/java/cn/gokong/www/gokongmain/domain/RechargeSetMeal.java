package cn.gokong.www.gokongmain.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 充值套餐表
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
@TableName("recharge_set_meal")
public class RechargeSetMeal implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 套餐编码
     */
    private String setMealNo;

    /**
     * 购买数量
     */
    private BigDecimal payNum;

    /**
     * 赠送数量
     */
    private BigDecimal sendNum;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSetMealNo() {
        return setMealNo;
    }

    public void setSetMealNo(String setMealNo) {
        this.setMealNo = setMealNo;
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

    @Override
    public String toString() {
        return "RechargeSetMeal{" +
        "id=" + id +
        ", setMealNo=" + setMealNo +
        ", payNum=" + payNum +
        ", sendNum=" + sendNum +
        "}";
    }
}
