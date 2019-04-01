package cn.gokong.www.gokongmain.vo.user_account;

import cn.gokong.www.base.util.TimeUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 说明:蟠桃变动记录
 *
 * @author ikook
 * @CreateDate 2018/9/19 16:16
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "AccountLogOutput", description = "蟠桃变动记录")
public class AccountLogOutput {
    /**
     * 变动金额
     */
    @ApiModelProperty(dataType = "String",name = "changeAmount",value = "变动金额")
    private BigDecimal changeAmount;

    /**
     * 变动日期
     */
    @ApiModelProperty(dataType = "String",name = "changeDate",value = "变动日期")
    private String changeDate;

    /**
     * 变动说明
     */
    @ApiModelProperty(dataType = "String",name = "changeRemark",value = "变动说明")
    private String changeRemark;

    public BigDecimal getChangeAmount() {
        return changeAmount;
    }

    public void setChangeAmount(BigDecimal changeAmount) {
        this.changeAmount = changeAmount;
    }

    public String getChangeDate() {
        return changeDate;
    }

    public void setChangeDate(Date changeDate) {
        this.changeDate = TimeUtil.getChatTimeStr(changeDate.getTime());
    }

    public String getChangeRemark() {
        return changeRemark;
    }

    public void setChangeRemark(String changeRemark) {
        this.changeRemark = changeRemark;
    }
}
