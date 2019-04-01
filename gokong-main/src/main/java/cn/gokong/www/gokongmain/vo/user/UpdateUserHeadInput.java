package cn.gokong.www.gokongmain.vo.user;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:修改用户头像入参
 *
 * @author ideac
 * @CreateDate 2018/9/10 22:02
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "UpdateUserHeadInput", description = "修改用户头像入参")
public class UpdateUserHeadInput extends UserNoBase {

    @ApiModelProperty(dataType = "String",name = "userHead",value = "头像地址")
    @NotNull(message = "头像地址不能为空")
    @Length(max = 255,message = "头像地址长度<=255")
    private String userHead;

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    @Override
    public String toString() {
        return "UpdateUserHeadInput{" +
                "userHead='" + userHead + '\'' +
                '}';
    }
}
