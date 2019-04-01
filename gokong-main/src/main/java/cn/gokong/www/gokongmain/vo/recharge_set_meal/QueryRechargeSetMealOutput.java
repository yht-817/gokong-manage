package cn.gokong.www.gokongmain.vo.recharge_set_meal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:查询充值套餐列表出参
 *
 * @author ikook
 * @CreateDate 2018/9/11 17:35
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "QueryRechargeSetMealOutput", description = "查询充值套餐列表出参")
public class QueryRechargeSetMealOutput {

    @ApiModelProperty(dataType = "String",name = "setMealNo",value = "套餐编码")
    private String setMealNo;

    @ApiModelProperty(dataType = "String",name = "payNum",value = "购买数量")
    private String payNum;

    @ApiModelProperty(dataType = "String",name = "sendNum",value = "赠送数量")
    private String sendNum;

    public String getSetMealNo() {
        return setMealNo;
    }

    public void setSetMealNo(String setMealNo) {
        this.setMealNo = setMealNo;
    }

    public String getPayNum() {
        return payNum;
    }

    public void setPayNum(String payNum) {
        this.payNum = payNum;
    }

    public String getSendNum() {
        return sendNum;
    }

    public void setSendNum(String sendNum) {
        this.sendNum = sendNum;
    }
}
