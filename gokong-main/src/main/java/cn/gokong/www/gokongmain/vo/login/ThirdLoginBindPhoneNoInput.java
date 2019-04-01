package cn.gokong.www.gokongmain.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 说明:第三方登录绑定手机号码入参
 *
 * @author ikook
 * @CreateDate 2018/9/10 10:31
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "ThirdLoginBindPhoneNoInput", description = "第三方登录绑定手机号码入参")
public class ThirdLoginBindPhoneNoInput {

    @ApiModelProperty(dataType = "String",name = "thirdKey",value = "第三方key")
    @NotNull(message = "第三方key不能为空")
    @Length(max = 200,message = "第三方key长度<=200")
    private String thirdKey;

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
    @Length(message = "手机代码长度<=10",max = 10)
    private String phoneCode;

    @ApiModelProperty(dataType = "String",name = "securityCode",value = "验证码")
    @Pattern(regexp = "^[0-9]{6}$",
            message = "请输入六位数的验证码")
    @NotNull(message = "验证码不能为空")
    private String securityCode;

    public String getThirdKey() {
        return thirdKey;
    }

    public void setThirdKey(String thirdKey) {
        this.thirdKey = thirdKey;
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

    @Override
    public String toString() {
        return "ThirdLoginBindPhoneNoInput{" +
                "thirdKey='" + thirdKey + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", phoneCode='" + phoneCode + '\'' +
                ", securityCode='" + securityCode + '\'' +
                '}';
    }
}
