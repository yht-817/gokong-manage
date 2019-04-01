package cn.gokong.www.gokongmain.vo.group;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

/**
 * 说明:创建群聊入参
 *
 * @author ikook
 * @CreateDate 2018/9/13 10:35
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "CreateGroupInput", description = "创建群聊入参")
public class CreateGroupInput extends UserNoBase {
    @ApiModelProperty(dataType = "String",name = "groupName",value = "群名称")
    @NotNull(message = "群名称不能为空")
    @Length(max = 10,message = "群名称长度<=10")
    private String groupName;

    @ApiModelProperty(dataType = "String",name = "groupHead",value = "群头像")
    @NotNull(message = "群头像不能为空")
    @Length(max = 100,message = "群头像长度<=100")
    private String groupHead;

    @ApiModelProperty(dataType = "String",name = "groupNode",value = "群公告")
    @NotNull(message = "群公告不能为空")
    @Length(max = 100,message = "群公告长度<=100")
    private String groupNode;

    @ApiModelProperty(dataType = "String",name = "cityNo",value = "城市编码")
    @NotNull(message = "城市编码不能为空")
    @Length(max = 50,message = "城市编码长度<=50")
    private String cityNo;

    @ApiModelProperty(dataType = "String",name = "ifPayGroup",value = "是否付费(公开群:10340001 收费群:10340002)")
    @Pattern(regexp = "^(10340001)|(10340002)$",message = "是否付费格式错误")
    @NotNull(message = "是否收费不能为空")
    private String ifPayGroup;

    @ApiModelProperty(dataType = "String", name = "payAmount", value = "收费金额")
    private BigDecimal payAmount;

    @ApiModelProperty(dataType = "boolean", name = "joinMark", value = "是否需要群主同意加入")
    private boolean joinMark;

    public boolean isJoinMark() {
        return joinMark;
    }

    public void setJoinMark(boolean joinMark) {
        this.joinMark = joinMark;
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupHead() {
        return groupHead;
    }

    public void setGroupHead(String groupHead) {
        this.groupHead = groupHead;
    }

    public String getGroupNode() {
        return groupNode;
    }

    public void setGroupNode(String groupNode) {
        this.groupNode = groupNode;
    }

    public String getIfPayGroup() {
        return ifPayGroup;
    }

    public void setIfPayGroup(String ifPayGroup) {
        this.ifPayGroup = ifPayGroup;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }
}
