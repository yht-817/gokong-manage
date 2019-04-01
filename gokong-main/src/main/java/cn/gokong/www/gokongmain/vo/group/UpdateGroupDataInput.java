package cn.gokong.www.gokongmain.vo.group;

import cn.gokong.www.gokongmain.vo.base.GroupNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
 * 说明:修改群资料入参
 *
 * @author ikook
 * @CreateDate 2018/9/13 15:21
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "UpdateGroupDataInput", description = "修改群资料入参")
public class UpdateGroupDataInput extends GroupNoBase {
    @ApiModelProperty(dataType = "String",name = "groupName",value = "群名称")
    @Length(max = 10,message = "群名称长度<=10")
    private String groupName;

    @ApiModelProperty(dataType = "String",name = "groupHead",value = "群头像")
    @Length(max = 100,message = "群头像长度<=100")
    private String groupHead;

    @ApiModelProperty(dataType = "String",name = "groupNode",value = "群公告")
    @Length(max = 100,message = "群公告长度<=100")
    private String groupNode;

    @ApiModelProperty(dataType = "boolean", name = "joinMark", value = "是否需要群主同意加入")
    private boolean joinMark;

    public boolean isJoinMark() {
        return joinMark;
    }

    public void setJoinMark(boolean joinMark) {
        this.joinMark = joinMark;
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
}
