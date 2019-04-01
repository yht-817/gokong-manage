package cn.gokong.www.gokongmain.vo.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:分页检索群出参
 *
 * @author ikook
 * @CreateDate 2018/9/13 22:35
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageSearchGroupOutput", description = "分页检索群出参")
public class PageSearchGroupOutput {
    @ApiModelProperty(dataType = "String",name = "groupNo",value = "群编码")
    private String groupNo;
    @ApiModelProperty(dataType = "String",name = "groupName",value = "群名称")
    private String groupName;
    @ApiModelProperty(dataType = "String", name = "groupNode", value = "群公告")
    private String groupNode;
    @ApiModelProperty(dataType = "String",name = "userNo",value = "创建人编码")
    private String userNo;
    @ApiModelProperty(dataType = "String", name = "groupHead", value = "群头像")
    private String groupHead;
    @ApiModelProperty(dataType = "String",name = "personCount",value = "群人数")
    private String personCount;
    @ApiModelProperty(dataType = "String",name = "isJoin",value = "当前访问用户是否加入群")
    private boolean isokJoin;
    @ApiModelProperty(dataType = "String",name = "isokOwner",value = "当前访问用户是否是群主")
    private boolean isokOwner;
    @ApiModelProperty(dataType = "String",name = "joinMark",value = "是否需要群主同意才可加入群")
    private boolean joinMark;
    @ApiModelProperty(dataType = "String",name = "applyState",value = "申请状态 (10170001:正常 10170005：申请中 '':未申请)")
    private String applyState;

    public String getApplyState() {
        return applyState;
    }

    public void setApplyState(String applyState) {
        this.applyState = applyState;
    }

    public boolean isJoinMark() {
        return joinMark;
    }

    public void setJoinMark(boolean joinMark) {
        this.joinMark = joinMark;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public boolean isIsokJoin() {
        return isokJoin;
    }

    public void setIsokJoin(boolean isokJoin) {
        this.isokJoin = isokJoin;
    }

    public boolean isIsokOwner() {
        return isokOwner;
    }

    public void setIsokOwner(boolean isokOwner) {
        this.isokOwner = isokOwner;
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

    public String getGroupNode() {
        return groupNode;
    }

    public void setGroupNode(String groupNode) {
        this.groupNode = groupNode;
    }

    public String getGroupHead() {
        return groupHead;
    }

    public void setGroupHead(String groupHead) {
        this.groupHead = groupHead;
    }

    public String getPersonCount() {
        return personCount;
    }

    public void setPersonCount(String personCount) {
        this.personCount = personCount;
    }
}
