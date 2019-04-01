package cn.gokong.www.gokongmain.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 说明:账号密码登陆入参
 *
 * @author ideac
 * @CreateDate 2018/9/8 1:25
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "AccountPasswordInput", description = "账号密码登录入参")
public class AccountPasswordInput {

    @ApiModelProperty(dataType = "String",name = "mailboxNo",value = "邮箱")
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
            message = "邮箱格式错误!")
    @Length(message = "邮箱长度<=50",max = 50)
    private String mailboxNo;

    @ApiModelProperty(dataType = "String",name = "userName",value = "用户名")
    @Pattern(regexp = "^[a-zA-Z0-9_]{4,16}$", message = "用户名必须是 字母、数字、下划线组成，字母开头，4-16位")
    @Length(max = 16,message = "用户名长度<=16")
    private String userName;

    @ApiModelProperty(dataType = "String",name = "phoneNo",value = "手机号")
    @Pattern(regexp = "^[0-9]*$",
            message = "手机号码格式错误")
    @Length(max = 20,message = "手机号码长度<=20")
    private String phoneNo;

    @ApiModelProperty(dataType = "String",name = "password",value = "密码")
    @Pattern(regexp = "^.*(?=.{6,})(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[.!@#$%^&*? ]).*$",
            message = "密码最少6位，包括至少1个大写字母，1个小写字母，1个数字，1个特殊字符[.!@#$%^&*? ]")
    @NotNull(message = "用户密码不能为空")
    @Length(max = 16,message = "密码长度<=16")
    private String password;

    public String getMailboxNo() {
        return mailboxNo;
    }

    public void setMailboxNo(String mailboxNo) {
        this.mailboxNo = mailboxNo;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "AccountPasswordInput{" +
                "mailboxNo='" + mailboxNo + '\'' +
                ", userName='" + userName + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
