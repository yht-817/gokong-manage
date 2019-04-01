package cn.gokong.www.gokongmain.vo.user_pay;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:查询支付类型
 *
 * @author ikook
 * @CreateDate 2018/10/22 17:09
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "QueryPayType", description = "查询支付类型")
public class QueryPayType {

    @ApiModelProperty(dataType = "boolean",name = "hasIosPay",value = "是否是IOS内购")
    public boolean hasIosPay;

    public boolean isHasIosPay() {
        return hasIosPay;
    }

    public void setHasIosPay(boolean hasIosPay) {
        this.hasIosPay = hasIosPay;
    }
}
