package cn.gokong.www.gokongmain.vo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:群聊编码base
 *
 * @author ikook
 * @CreateDate 2018/9/13 11:05
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "GroupNoBase", description = "群聊编码base")
public class GroupNoBase {

    @ApiModelProperty(dataType = "String",name = "groupNo",value = "群聊编码")
    @NotNull(message = "群聊编码不能为空")
    @Length(max = 50,message = "群聊编码长度<=50")
    private String groupNo;

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }
}
