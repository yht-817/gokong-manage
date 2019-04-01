package cn.gokong.www.gokongmain.vo.fans;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:添加关注入参
 *
 * @author ikook
 * @CreateDate 2018/9/14 17:54
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "SetFocusInput", description = "添加关注入参")
public class SetFocusInput extends UserNoBase {

    @ApiModelProperty(dataType = "String",name = "userNo",value = "粉丝编码")
    @NotNull(message = "粉丝编码不能为空")
    @Length(max = 50,message = "粉丝编码长度<=50")
    private String fansUserNo;

    @ApiModelProperty(dataType = "boolean",name = "focus",value = "true：关注 false：取消关注")
    private boolean focus;

    public boolean isFocus() {
        return focus;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }

    public String getFansUserNo() {
        return fansUserNo;
    }

    public void setFansUserNo(String fansUserNo) {
        this.fansUserNo = fansUserNo;
    }
}
