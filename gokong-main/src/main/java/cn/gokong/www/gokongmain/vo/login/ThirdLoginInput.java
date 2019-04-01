package cn.gokong.www.gokongmain.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 说明:第三方登录入参
 *
 * @author ideac
 * @CreateDate 2018/9/9 20:38
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "ThirdLoginInput", description = "第三方登录入参")
public class ThirdLoginInput {
    @ApiModelProperty(dataType = "String",name = "thirdKey",value = "第三方key")
    @NotNull(message = "第三方key不能为空")
    @Length(max = 200,message = "第三方key长度<=200")
    private String thirdKey;

    @ApiModelProperty(dataType = "String",name = "thirdType",value = "第三方类型")
    @NotNull(message = "第三方类型不能为空")
    @Pattern(regexp = "^(10320001)|(10320002)|(10320002)$",message = "类型错误(微信:10320001 QQ:10320002 微博:10320003)")
    @Length(max = 20,message = "第三方类型长度<=20")
    private String thirdType;

    @ApiModelProperty(dataType = "String",name = "thirdHead",value = "头像地址")
    @NotNull(message = "头像地址不能为空")
    @Length(max = 500,message = "头像地址长度<=500")
    private String thirdHead;

    @ApiModelProperty(dataType = "String",name = "thirdName",value = "用户名称")
    @NotNull(message = "用户名称不能为空")
    @Length(max = 100,message = "用户名称长度<=100")
    private String thirdName;

    @ApiModelProperty(dataType = "String",name = "thirdSex",value = "用户性别")
    @NotNull(message = "用户性别不能为空")
    @Pattern(regexp = "^(10010001)|(10010002)$",message = "用户性别格式错误")
    @Length(max = 10,message = "用户性别长度<=10")
    private String thirdSex;

    public String getThirdKey() {
        return thirdKey;
    }

    public void setThirdKey(String thirdKey) {
        this.thirdKey = thirdKey;
    }

    public String getThirdType() {
        return thirdType;
    }

    public void setThirdType(String thirdType) {
        this.thirdType = thirdType;
    }

    public String getThirdHead() {
        return thirdHead;
    }

    public void setThirdHead(String thirdHead) {
        this.thirdHead = thirdHead;
    }

    public String getThirdName() {
        return thirdName;
    }

    public void setThirdName(String thirdName) {
        this.thirdName = thirdName;
    }

    public String getThirdSex() {
        return thirdSex;
    }

    public void setThirdSex(String thirdSex) {
        this.thirdSex = thirdSex;
    }

    @Override
    public String toString() {
        return "ThirdLoginInput{" +
                "thirdKey='" + thirdKey + '\'' +
                ", thirdType='" + thirdType + '\'' +
                ", thirdHead='" + thirdHead + '\'' +
                ", thirdName='" + thirdName + '\'' +
                ", thirdSex='" + thirdSex + '\'' +
                '}';
    }
}
