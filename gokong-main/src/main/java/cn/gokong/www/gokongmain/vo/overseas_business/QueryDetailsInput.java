package cn.gokong.www.gokongmain.vo.overseas_business;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:查询详情入参
 *
 * @author ikook
 * @CreateDate 2018/9/25 11:44
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "QueryDetailsInput", description = "查询详情入参")
public class QueryDetailsInput extends UserNoBase {

    @ApiModelProperty(dataType = "String",name = "businessNo",value = "创业编码")
    @NotNull(message = "创业编码不能为空")
    @Length(max = 50,message = "创业编码长度<=50")
    private String businessNo;

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }
}
