package cn.gokong.www.gokongmain.vo.used_deal_leave;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:发布留言入参
 *
 * @author ikook
 * @CreateDate 2018/10/1 17:08
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "ReleaseLeaveInput", description = "发布留言入参")
public class ReleaseLeaveInput extends UserNoBase {

    @ApiModelProperty(dataType = "String", name = "usedNo", value = "二手交易编码")
    @NotNull(message = "二手交易编码不能为空")
    @Length(max = 50, message = "二手交易编码长度<=50")
    private String usedNo;

    @ApiModelProperty(dataType = "String", name = "leaveContent", value = "留言内容")
    @NotNull(message = "留言内容不能为空")
    @Length(max = 100, message = "留言内容长度<=100")
    private String leaveContent;

    public String getUsedNo() {
        return usedNo;
    }

    public void setUsedNo(String usedNo) {
        this.usedNo = usedNo;
    }

    public String getLeaveContent() {
        return leaveContent;
    }

    public void setLeaveContent(String leaveContent) {
        this.leaveContent = leaveContent;
    }
}
