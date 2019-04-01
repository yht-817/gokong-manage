package cn.gokong.www.gokongmain.vo.user;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:查询个人主页入参
 *
 * @author ikook
 * @CreateDate 2018/9/12 11:44
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "UserHomeInput", description = "查询个人主页入参")
public class UserHomeInput extends UserNoBase {

    @ApiModelProperty(dataType = "String",name = "visitorNo",value = "访问者编码")
    @NotNull(message = "访问者编码不能为空")
    @Length(max = 50,message = "访问者编码长度<=50")
    private String visitorNo;

    public String getVisitorNo() {
        return visitorNo;
    }

    public void setVisitorNo(String visitorNo) {
        this.visitorNo = visitorNo;
    }
}
