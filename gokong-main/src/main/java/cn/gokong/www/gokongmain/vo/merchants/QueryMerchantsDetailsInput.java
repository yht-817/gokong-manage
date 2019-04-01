package cn.gokong.www.gokongmain.vo.merchants;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 说明:获取商户详情信息 入参
 *
 * @author ikook
 * @CreateDate 2018/9/11 14:20
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "QueryMerchantsDetailsInput", description = "获取商户详情信息 入参")
public class QueryMerchantsDetailsInput extends UserNoBase {

    @ApiModelProperty(dataType = "String",name = "merchantsNo",value = "商户编码")
    @NotNull(message = "商户编码不能为空")
    private String merchantsNo;

    public String getMerchantsNo() {
        return merchantsNo;
    }

    public void setMerchantsNo(String merchantsNo) {
        this.merchantsNo = merchantsNo;
    }
}
