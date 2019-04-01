package cn.gokong.www.gokongmain.vo.group;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 说明:查询群详情出参
 *
 * @author ikook
 * @CreateDate 2018/9/13 15:35
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "QueryGroupDetailsOutput", description = "查询群详情出参")
public class QueryGroupDetailsOutput {
    @ApiModelProperty(dataType = "String",name = "groupNo",value = "群编码")
    private String groupNo;

    @ApiModelProperty(dataType = "String",name = "groupName",value = "群名称")
    private String groupName;

    @ApiModelProperty(dataType = "String",name = "createDate",value = "创建时间")
    private String createDate;

    @ApiModelProperty(dataType = "String",name = "userNo",value = "创建人编码")
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "groupNode",value = "群公告")
    private String groupNode;

    @ApiModelProperty(dataType = "String",name = "groupHead",value = "群头像")
    private String groupHead;

    @ApiModelProperty(dataType = "String",name = "isJoin",value = "当前访问用户是否加入群")
    private boolean isokJoin;

    @ApiModelProperty(dataType = "String",name = "isokOwner",value = "当前访问用户是否是群主")
    private boolean isokOwner;

    @ApiModelProperty(dataType = "List",name = "queryUsers",value = "详情显示的8个群成员列表")
    private List<QueryGroupUserOutput> groupUsers;

    @ApiModelProperty(dataType = "int",name = "groupCount",value = "群总人数")
    private int groupCount;

    @ApiModelProperty(dataType = "boolean",name = "joinMark",value = "是否需要群主同意加入")
    private boolean joinMark;

    @ApiModelProperty(dataType = "String",name = "applyState",value = "申请状态 (10170001:正常 10170005：申请中 '':未申请)")
    private String applyState;

    @ApiModelProperty(dataType = "String",name = "groupType",value = "群类型 (1：普通群 2：活动群)")
    private String groupType;

    public String getGroupType() {
        return groupType;
    }

    public void setGroupType(String groupType) {
        this.groupType = groupType;
    }

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
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

    public List<QueryGroupUserOutput> getGroupUsers() {
        return groupUsers;
    }

    public void setGroupUsers(List<QueryGroupUserOutput> groupUsers) {
        this.groupUsers = groupUsers;
    }

    public int getGroupCount() {
        return groupCount;
    }

    public void setGroupCount(int groupCount) {
        this.groupCount = groupCount;
    }
}
