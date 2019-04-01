package cn.gokong.www.gokongmain.vo.local_activity_apply;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:审核活动申请
 *
 * @author ikook
 * @CreateDate 2018/10/5 15:45
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "AuditApplyInput", description = "审核活动申请")
public class AuditApplyInput extends UserNoBase {

    @ApiModelProperty(dataType = "String",name = "applyNo",value = "申请编码")
    @NotNull(message = "申请编码不能为空")
    @Length(max = 50,message = "申请编码长度<=50")
    private String applyNo;

    @ApiModelProperty(dataType = "boolean",name = "auditState",value = "审核状态")
    private boolean auditState;

    @NotNull
    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(@NotNull String applyNo) {
        this.applyNo = applyNo;
    }

    public boolean isAuditState() {
        return auditState;
    }

    public void setAuditState(boolean auditState) {
        this.auditState = auditState;
    }
}
