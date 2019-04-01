package cn.gokong.www.gokongmain.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 说明:找回密码-重置密码入参
 *
 * @author ideac
 * @CreateDate 2018/9/10 21:45
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "ResetPasswdInput", description = "找回密码-重置密码入参")
public class ResetPasswdInput {

    @ApiModelProperty(dataType = "String",name = "phoneNo",value = "手机号")
    @Pattern(regexp = "^[0-9]*$",
            message = "手机号码格式错误")
    @NotNull(message = "手机号码不能为空")
    @Length(max = 20,message = "你输入的电话号码太长了")
    private String phoneNo;

    @ApiModelProperty(dataType = "String",name = "phoneCode",value = "手机代码")
    @Pattern(regexp = "^(00)[0-9]*$",
            message = "手机代码至少三位")
    @NotNull(message = "手机代码不能为空")
    @Length(message = "你输入的手机代码太长了",max = 10)
    private String phoneCode;

    @ApiModelProperty(dataType = "String",name = "password",value = "密码")
    @Pattern(regexp = "^.*(?=.{6,})(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[.!@#$%^&*? ]).*$",
            message = "密码最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符[.!@#$%^&*? ]")
    @NotNull(message = "用户密码不能为空")
    @Length(max = 16,message = "密码长度<=16")
    private String password;

    @ApiModelProperty(dataType = "String",name = "securityCode",value = "验证码")
    @Pattern(regexp = "^[0-9]{6}$",
            message = "请输入六位数的验证码")
    @NotNull(message = "验证码不能为空")
    private String securityCode;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    @Override
    public String toString() {
        return "ResetPasswdInput{" +
                "phoneNo='" + phoneNo + '\'' +
                ", phoneCode='" + phoneCode + '\'' +
                ", password='" + password + '\'' +
                ", securityCode='" + securityCode + '\'' +
                '}';
    }
}
