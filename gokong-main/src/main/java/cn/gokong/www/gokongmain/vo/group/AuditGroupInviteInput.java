package cn.gokong.www.gokongmain.vo.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:审核（申请/邀请）
 *
 * @author ikook
 * @CreateDate 2018/9/13 18:27
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "AuditGroupInviteInput", description = "审核（申请/邀请）")
public class AuditGroupInviteInput {

    @ApiModelProperty(dataType = "String",name = "inviteNo",value = "申请编码")
    @NotNull(message = "申请编码不能为空")
    @Length(max = 50,message = "申请编码长度<=50")
    private String inviteNo;

    @ApiModelProperty(dataType = "String",name = "auditState",value = "审核状态")
    @NotNull(message = "审核状态不能为空")
    private boolean auditState;

    public String getInviteNo() {
        return inviteNo;
    }

    public void setInviteNo(String inviteNo) {
        this.inviteNo = inviteNo;
    }

    public boolean isAuditState() {
        return auditState;
    }

    public void setAuditState(boolean auditState) {
        this.auditState = auditState;
    }
}
