package cn.gokong.www.gokongmain.vo.service_message;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 说明:设置消息读取状态入参
 *
 * @author ikook
 * @CreateDate 2018/9/15 21:10
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "SetServiceMessageReadStateInput", description = "设置消息读取状态入参")
public class SetServiceMessageReadStateInput {
    @ApiModelProperty(dataType = "String",name = "serviceNo",value = "服务消息编码")
    @NotNull(message = "服务消息编码不能为空")
    @Length(max = 50,message = "服务消息编码长度<=50")
    private String serviceNo;

    @ApiModelProperty(dataType = "String",name = "groupNo",value = "消息读取状态")
    @NotNull(message = "消息读取状态不能为空")
    @Pattern(regexp = "^(10200002)$",
            message = "消息读取状态格式错误")
    @Length(max = 10,message = "消息读取状态长度<=10")
    private String readState;

    public String getServiceNo() {
        return serviceNo;
    }

    public void setServiceNo(String serviceNo) {
        this.serviceNo = serviceNo;
    }

    public String getReadState() {
        return readState;
    }

    public void setReadState(String readState) {
        this.readState = readState;
    }
}
