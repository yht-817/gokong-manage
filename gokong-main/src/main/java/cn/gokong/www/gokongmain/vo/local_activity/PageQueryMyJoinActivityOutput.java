package cn.gokong.www.gokongmain.vo.local_activity;

import cn.gokong.www.gokongmain.vo.user.QueryUserOutput;
import cn.hutool.core.date.DateUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 说明:分页查询我加入的活动
 *
 * @author ikook
 * @CreateDate 2018/10/4 11:36
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryMyJoinActivityOutput", description = "分页查询我加入的活动")
public class PageQueryMyJoinActivityOutput extends QueryUserOutput {

    @ApiModelProperty(dataType = "String",name = "activityNo",value = "活动编码")
    private String activityNo;

    @ApiModelProperty(dataType = "String",name = "userSign",value = "用户签名")
    private String userSign;

    @ApiModelProperty(dataType = "String",name = "userSign",value = "活动标题")
    private String activityTitle;

    @ApiModelProperty(dataType = "String",name = "address",value = "活动地点")
    private String address;

    @ApiModelProperty(dataType = "String",name = "activityTime",value = "活动时间")
    private String activityTime;

    @ApiModelProperty(dataType = "String",name = "applyState",value = "申请状态(10470002:申请中 10470003：已通过)")
    private String applyState;

    @ApiModelProperty(dataType = "String",name = "hasEnd",value = "是否结束")
    private boolean hasEnd;

    public String getApplyState() {
        return applyState;
    }

    public void setApplyState(String applyState) {
        this.applyState = applyState;
    }

    public String getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(String activityNo) {
        this.activityNo = activityNo;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {

        this.activityTime = DateUtil.format(activityTime,"MM月dd日 HH:mm");
    }

    public boolean getHasEnd() {
        return hasEnd;
    }

    public void setHasEnd(boolean hasEnd) {
        this.hasEnd = hasEnd;
    }
}
