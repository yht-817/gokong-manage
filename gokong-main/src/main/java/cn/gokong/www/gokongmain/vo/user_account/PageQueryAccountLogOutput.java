package cn.gokong.www.gokongmain.vo.user_account;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 说明:分页查询蟠桃变动记录出参
 *
 * @author ikook
 * @CreateDate 2018/9/11 17:58
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryAccountLogOutput", description = "分页查询蟠桃变动记录出参")
public class PageQueryAccountLogOutput {

    @ApiModelProperty(dataType = "String",name = "amount",value = "昨日增加蟠桃")
    private BigDecimal amount;

    @ApiModelProperty(dataType = "String",name = "changeAmount",value = "蟠桃变动记录")
    private List<AccountLogOutput> accountLogs;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public List<AccountLogOutput> getAccountLogs() {
        return accountLogs;
    }

    public void setAccountLogs(List<AccountLogOutput> accountLogs) {
        this.accountLogs = accountLogs;
    }
}
