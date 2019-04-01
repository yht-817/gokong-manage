package cn.gokong.www.gokongmain.vo.merchants_evaluate;

import cn.gokong.www.gokongmain.vo.base.PageQueryBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:分页查询列表
 *
 * @author ikook
 * @CreateDate 2018/10/1 14:31
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryListInput", description = "分页查询列表")
public class PageQueryListInput extends PageQueryBase {

    @ApiModelProperty(dataType = "String",name = "merchantsNo",value = "商户编码")
    @NotNull(message = "商户编码不能为空")
    @Length(max = 50,message = "商户编码长度<=50")
    private String merchantsNo;

    public String getMerchantsNo() {
        return merchantsNo;
    }

    public void setMerchantsNo(String merchantsNo) {
        this.merchantsNo = merchantsNo;
    }
}
