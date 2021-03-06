package cn.gokong.www.gokongmain.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 说明:手机号+验证码登录入参
 *
 * @author ideac
 * @CreateDate 2018/9/8 5:09
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "AuthCodeLoginInput", description = "手机号+验证码登录入参")
public class AuthCodeLoginInput {

    @ApiModelProperty(dataType = "String",name = "phoneNo",value = "手机号")
    @Pattern(regexp = "^\\d{5,20}$",
            message = "手机号码格式错误")
    @NotNull(message = "手机号码不能为空")
    @Length(max = 20,message = "你输入的电话号码太长了")
    private String phoneNo;

    @ApiModelProperty(dataType = "String",name = "phoneCode",value = "手机代码")
    @Pattern(regexp = "^(00)[0-9]*$",
            message = "手机代码至少三位")
    @NotNull(message = "手机代码不能为空")
    @Length(message = "手机代码长度<=10",max = 10)
    private String phoneCode;

    @ApiModelProperty(dataType = "String",name = "securityCode",value = "验证码")
    @Pattern(regexp = "^[0-9]{4}$",
            message = "请输入4位数的验证码")
    @NotNull(message = "验证码不能为空")
    private String securityCode;

    private String fansUserNo;

    @ApiModelProperty(dataType = "String",name = "userType",value = "用户类型 刷单用户:amazon")
    private String userType;

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getFansUserNo() {
        return fansUserNo;
    }

    public void setFansUserNo(String fansUserNo) {
        this.fansUserNo = fansUserNo;
    }

    @Override
    public String toString() {
        return "AuthCodeLoginInput{" +
                "phoneNo='" + phoneNo + '\'' +
                ", phoneCode='" + phoneCode + '\'' +
                ", securityCode='" + securityCode + '\'' +
                ", fansUserNo='" + fansUserNo + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}
