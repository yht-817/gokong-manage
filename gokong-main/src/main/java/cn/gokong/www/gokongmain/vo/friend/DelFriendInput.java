package cn.gokong.www.gokongmain.vo.friend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:删除好友入参
 *
 * @author ikook
 * @CreateDate 2018/9/13 18:27
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "DelFriendInput", description = "删除好友入参")
public class DelFriendInput {

    @ApiModelProperty(dataType = "String",name = "inviteNo",value = "申请编码")
    @NotNull(message = "申请编码不能为空")
    @Length(max = 50,message = "申请编码长度<=50")
    private String inviteNo;

    public String getInviteNo() {
        return inviteNo;
    }

    public void setInviteNo(String inviteNo) {
        this.inviteNo = inviteNo;
    }
}
