package cn.gokong.www.gokongmain.vo.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:用户群列表出参
 *
 * @author ikook
 * @CreateDate 2018/9/13 18:58
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryUserGroupOutput", description = "用户群列表出参")
public class PageQueryUserGroupOutput {

    @ApiModelProperty(dataType = "String",name = "groupNo",value = "群编码")
    private String groupNo;

    @ApiModelProperty(dataType = "String",name = "groupName",value = "群名称")
    private String groupName;

    @ApiModelProperty(dataType = "String",name = "groupHead",value = "群头像")
    private String groupHead;

    @ApiModelProperty(dataType = "String",name = "userNo",value = "创建人编码")
    private String userNo;

    @ApiModelProperty(dataType = "int",name = "personCount",value = "群人数")
    private int personCount;

    @ApiModelProperty(dataType = "String",name = "groupNode",value = "群公告")
    private String groupNode;

    @ApiModelProperty(dataType = "String",name = "isokOwner",value = "是否是群主")
    private boolean isokOwner = false;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
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

    public int getPersonCount() {
        return personCount;
    }

    public void setPersonCount(int personCount) {
        this.personCount = personCount;
    }

    public String getGroupNode() {
        return groupNode;
    }

    public void setGroupNode(String groupNode) {
        this.groupNode = groupNode;
    }

    public boolean isIsokOwner() {
        return isokOwner;
    }

    public void setIsokOwner(boolean isokOwner) {
        this.isokOwner = isokOwner;
    }
}
