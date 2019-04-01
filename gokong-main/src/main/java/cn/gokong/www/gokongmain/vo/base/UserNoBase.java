package cn.gokong.www.gokongmain.vo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:公用用户编码数据
 *
 * @author ideac
 * @CreateDate 2018/9/10 22:02
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "UserNoBase", description = "公用用户编码数据")
public class UserNoBase {

    @ApiModelProperty(dataType = "String", name = "userNo", value = "用户编码")
    @NotNull(message = "用户编码不能为空")
    @Length(max = 50, message = "用户编码长度<=50")
    private String userNo;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    @Override
    public String toString() {
        return "UserNoBase{" +
                "userNo='" + userNo + '\'' +
                '}';
    }
}
