package cn.gokong.www.gokongmain.vo.used_deal_leave;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:回复留言
 *
 * @author ikook
 * @CreateDate 2018/10/1 17:41
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "ReplyLeaveInput", description = "回复留言")
public class ReplyLeaveInput extends UserNoBase {

    @ApiModelProperty(dataType = "String", name = "leaveNo", value = "留言编码")
    @NotNull(message = "留言编码不能为空")
    @Length(max = 50, message = "留言编码长度<=50")
    private String leaveNo;

    @ApiModelProperty(dataType = "String", name = "leaveContent", value = "回复内容")
    @NotNull(message = "回复内容不能为空")
    @Length(max = 100, message = "回复内容长度<=100")
    private String leaveContent;

    public String getLeaveNo() {
        return leaveNo;
    }

    public void setLeaveNo(String leaveNo) {
        this.leaveNo = leaveNo;
    }

    public String getLeaveContent() {
        return leaveContent;
    }

    public void setLeaveContent(String leaveContent) {
        this.leaveContent = leaveContent;
    }
}
