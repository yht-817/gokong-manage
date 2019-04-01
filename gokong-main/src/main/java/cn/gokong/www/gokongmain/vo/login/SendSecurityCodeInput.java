package cn.gokong.www.gokongmain.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 说明:发送验证码入参
 *
 * @author ideac
 * @CreateDate 2018/9/8 5:17
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "SendSecurityCodeInput", description = "发送验证码入参")
public class SendSecurityCodeInput {
    @ApiModelProperty(dataType = "String",name = "phoneNo",value = "手机号")
    @Pattern(regexp = "^[0-9]*$",
            message = "手机号码格式错误")
    @NotNull(message = "手机号码不能为空")
    @Length(max = 20,message = "手机号长度<=20")
    private String phoneNo;

    @ApiModelProperty(dataType = "String",name = "phoneCode",value = "手机代码")
    @Pattern(regexp = "^(00)[0-9]*$",
            message = "手机代码至少三位")
    @NotNull(message = "手机代码不能为空")
    @Length(message = "你输入的手机代码太长了",max = 10)
    private String phoneCode;

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

    @Override
    public String toString() {
        return "SendSecurityCodeInput{" +
                "phoneNo='" + phoneNo + '\'' +
                ", phoneCode='" + phoneCode + '\'' +
                '}';
    }
}
