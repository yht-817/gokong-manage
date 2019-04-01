package cn.gokong.www.gokongmain.vo.user;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Pattern;

/**
 * 说明:修改用户信息入参
 *
 * @author ideac
 * @CreateDate 2018/9/10 21:19
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "UpdateUserInfoInput", description = "修改用户信息入参")
public class UpdateUserInfoInput extends UserNoBase {

    @ApiModelProperty(dataType = "String",name = "nickName",value = "昵称")
    @Length(max = 16,message = "昵称长度<=16")
    private String nickName;

    @ApiModelProperty(dataType = "String",name = "sexNo",value = "性别")
    @Pattern(regexp = "^(10010001)|(10010002)$",message = "用户性别格式错误")
    @Length(max = 50,message = "性别长度<=50")
    private String sexNo;

    @ApiModelProperty(dataType = "String",name = "userSign",value = "签名")
    @Length(max = 50,message = "签名长度<=50")
    private String userSign;

    @ApiModelProperty(dataType = "String",name = "userLocal",value = "常住地")
    @Length(max = 50,message = "常住地长度<=50")
    private String userLocal;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSexNo() {
        return sexNo;
    }

    public void setSexNo(String sexNo) {
        this.sexNo = sexNo;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public String getUserLocal() {
        return userLocal;
    }

    public void setUserLocal(String userLocal) {
        this.userLocal = userLocal;
    }

    @Override
    public String toString() {
        return "UpdateUserInfoInput{" +
                ", nickName='" + nickName + '\'' +
                ", sexNo='" + sexNo + '\'' +
                ", userSign='" + userSign + '\'' +
                ", userLocal='" + userLocal + '\'' +
                '}';
    }
}
