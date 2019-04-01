package cn.gokong.www.gokongmain.vo.overseas_business;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:加入海外创业入参
 *
 * @author ikook
 * @CreateDate 2018/9/25 14:15
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "JoinInput", description = "加入海外创业入参")
public class JoinInput extends UserNoBase {

    @ApiModelProperty(dataType = "String",name = "businessNo",value = "创业编码")
    @NotNull(message = "创业编码不能为空")
    @Length(max = 50,message = "创业编码长度<=50")
    private String businessNo;

    @ApiModelProperty(dataType = "String",name = "name",value = "姓名")
    @NotNull(message = "姓名不能为空")
    @Length(max = 20,message = "姓名长度<=20")
    private String name;

    @ApiModelProperty(dataType = "String",name = "phone",value = "联系电话")
    @NotNull(message = "联系电话不能为空")
    @Length(max = 20,message = "联系电话长度<=20")
    private String phone;

    @ApiModelProperty(dataType = "String",name = "city",value = "加盟城市")
    @NotNull(message = "加盟城市不能为空")
    @Length(max = 50,message = "加盟城市长度<=50")
    private String city;

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
