package cn.gokong.www.gokongmain.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 说明:用户登录入参
 *
 * @author ikook
 * @CreateDate 2018/12/4 16:53
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "UserInfo对象", description = "用户登录入参")
public class LoginInput {

    @ApiModelProperty(value = "临时登录凭证code")
    @NotNull(message = "code 不能为NULL")
    private String code;

    @ApiModelProperty(value = "用户非敏感信息")
    @NotNull(message = "用户非敏感信息 不能为NULL")
    private String rawData;

    @ApiModelProperty(value = "签名")
    @NotNull(message = "签名 不能为NULL")
    private String signature;

    @ApiModelProperty(value = "用户敏感信息")
    @NotNull(message = "用户敏感信息 不能为NULL")
    private String encrypteData;

    @ApiModelProperty(value = "解密算法的向量")
    @NotNull(message = "解密算法的向量 不能为NULL")
    private String iv;

    @NotNull
    public String getCode() {
        return code;
    }

    public void setCode(@NotNull String code) {
        this.code = code;
    }

    @NotNull
    public String getRawData() {
        return rawData;
    }

    public void setRawData(@NotNull String rawData) {
        this.rawData = rawData;
    }

    @NotNull
    public String getSignature() {
        return signature;
    }

    public void setSignature(@NotNull String signature) {
        this.signature = signature;
    }

    @NotNull
    public String getEncrypteData() {
        return encrypteData;
    }

    public void setEncrypteData(@NotNull String encrypteData) {
        this.encrypteData = encrypteData;
    }

    @NotNull
    public String getIv() {
        return iv;
    }

    public void setIv(@NotNull String iv) {
        this.iv = iv;
    }
}
