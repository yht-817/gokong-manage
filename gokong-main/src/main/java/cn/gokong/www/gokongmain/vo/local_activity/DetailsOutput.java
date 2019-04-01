package cn.gokong.www.gokongmain.vo.local_activity;

import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.gokong.www.gokongmain.vo.group.QueryGroupUserOutput;
import cn.gokong.www.gokongmain.vo.user.QueryUserOutput;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 说明:查询同城活动详情
 *
 * @author ikook
 * @CreateDate 2018/10/4 12:00
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "DetailsInput", description = "查询同城活动详情")
public class DetailsOutput extends QueryUserOutput {
    @ApiModelProperty(dataType = "String",name = "activityNo",value = "活动编码")
    private String activityNo;

    @ApiModelProperty(dataType = "String",name = "activityImg",value = "用户签名")
    private String activityImg;

    @ApiModelProperty(dataType = "String",name = "userSign",value = "活动标题")
    private String activityTitle;

    @ApiModelProperty(dataType = "String",name = "price",value = "价格")
    private String price;

    @ApiModelProperty(dataType = "String",name = "activityDesc",value = "活动描述")
    private String activityDesc;

    @ApiModelProperty(dataType = "String",name = "activityTime",value = "活动时间")
    private String activityTime;

    @ApiModelProperty(dataType = "Date",name = "activityDate",value = "修改时间")
    private String activityDate;

    @ApiModelProperty(dataType = "String",name = "address",value = "活动地点")
    private String address;

    @ApiModelProperty(dataType = "String",name = "personNumScope",value = "希望人数")
    private String personNumScope;

    @ApiModelProperty(dataType = "String",name = "groupNo",value = "群编码")
    private String groupNo;

    @ApiModelProperty(dataType = "String",name = "groupName",value = "群名称")
    private String groupName;

    @ApiModelProperty(dataType = "String",name = "groupHead",value = "群头像")
    private String groupHead;

    @ApiModelProperty(dataType = "boolean",name = "hasJoin",value = "是否参加活动")
    private boolean hasJoin;

    @ApiModelProperty(dataType = "String",name = "applyState",value = "活动加入状态(10470001:未参加 10470002:申请中 10470003:已通过 10470004:已拒绝)")
    private String applyState;

    @ApiModelProperty(dataType = "boolean",name = "hasCreator",value = "是否是活动创建者")
    private boolean hasCreator;

    @ApiModelProperty(dataType = "boolean",name = "hasCoinMeet",value = "金币是否满足")
    private boolean hasCoinMeet;

    @ApiModelProperty(dataType = "List", name = "joinUsers", value = "参加用户")
    private List<QueryGroupUserOutput> joinUsers;

    @ApiModelProperty(dataType = "boolean", name = "hasVerify", value = "是否需要验证")
    private boolean hasVerify;

    public boolean isHasVerify() {
        return hasVerify;
    }

    public void setHasVerify(boolean hasVerify) {
        this.hasVerify = hasVerify;
    }

    /*public String getApplyState() {
        return applyState;
    }

    public void setApplyState(String applyState) {
        System.out.println(applyState);
        this.applyState = StrUtil.isEmpty(applyState)? SysCodeEnum.CODE_10470001.getCode():applyState;
    }*/

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

    public String getApplyState() {
        return StrUtil.isEmpty(applyState)? SysCodeEnum.CODE_10470001.getCode():applyState;
    }

    public void setApplyState(String applyState) {
        this.applyState = applyState;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = DateUtil.formatDateTime(activityDate);
    }

    public boolean isHasCoinMeet() {
        return hasCoinMeet;
    }

    public void setHasCoinMeet(boolean hasCoinMeet) {
        this.hasCoinMeet = hasCoinMeet;
    }

    public boolean isHasCreator() {
        return hasCreator;
    }

    public void setHasCreator(boolean hasCreator) {
        this.hasCreator = hasCreator;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public boolean isHasJoin() {
        return hasJoin;
    }

    public void setHasJoin(boolean hasJoin) {
        this.hasJoin = hasJoin;
    }

    public String getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(String activityNo) {
        this.activityNo = activityNo;
    }

    public String getActivityImg() {
        return activityImg;
    }

    public void setActivityImg(String activityImg) {
        this.activityImg = activityImg;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {

        this.activityTime = DateUtil.format(activityTime,"MM月dd日 HH:mm");
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPersonNumScope() {
        return personNumScope;
    }

    public void setPersonNumScope(String personNumScope) {
        this.personNumScope = personNumScope;
    }

    public List<QueryGroupUserOutput> getJoinUsers() {
        return joinUsers;
    }

    public void setJoinUsers(List<QueryGroupUserOutput> joinUsers) {
        this.joinUsers = joinUsers;
    }
}
