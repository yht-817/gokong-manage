package cn.gokong.www.gokongmain.vo.group;

import cn.gokong.www.gokongmain.vo.base.GroupNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:申请加入群聊入参
 *
 * @author ikook
 * @CreateDate 2018/9/13 11:14
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "ApplyJoinGroupInput", description = "申请加入群聊入参")
public class QueryGroupDetailsInput extends GroupNoBase {

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    @NotNull(message = "用户编码不能为空")
    @Length(max = 50,message = "用户编码长度<=50")
    private String userNo;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
}
