package cn.gokong.www.gokongmain.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 说明:查询用户金额出参
 *
 * @author ikook
 * @CreateDate 2018/9/11 17:06
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "QueryUserAccountOutput", description = "查询用户金额出参")
public class QueryUserAccountOutput {

    @ApiModelProperty(dataType = "String",name = "accountAmount",value = "用户金额")
    private BigDecimal accountAmount;

    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }
}
