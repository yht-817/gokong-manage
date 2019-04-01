package cn.gokong.www.gokongmain.vo.friend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 说明:审核好友请求
 *
 * @author ikook
 * @CreateDate 2018/9/13 18:27
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "AuditFriendInviteInput", description = "审核好友请求")
public class AuditFriendInviteInput {

    @ApiModelProperty(dataType = "String",name = "inviteNo",value = "申请编码")
    @NotNull(message = "申请编码不能为空")
    @Length(max = 50,message = "申请编码长度<=50")
    private String inviteNo;

    @ApiModelProperty(dataType = "String",name = "auditState",value = "审核状态")
    @NotNull(message = "审核状态不能为空")
    @Pattern(regexp = "^(10150002)|(10150003)$",
            message = "审核状态格式错误")
    @Length(max = 10,message = "审核状态长度<=10")
    private String auditState;

    public String getInviteNo() {
        return inviteNo;
    }

    public void setInviteNo(String inviteNo) {
        this.inviteNo = inviteNo;
    }

    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState;
    }
}
