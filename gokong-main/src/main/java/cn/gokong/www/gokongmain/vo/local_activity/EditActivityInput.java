package cn.gokong.www.gokongmain.vo.local_activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:编辑活动入参
 *
 * @author ikook
 * @CreateDate 2018/10/5 16:41
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "EditActivityInput", description = "编辑活动入参")
public class EditActivityInput {
    @ApiModelProperty(dataType = "String",name = "activityNo",value = "活动编码")
    @NotNull(message = "活动编码不能为空")
    @Length(max = 50,message = "活动编码长度<=50")
    private String activityNo;

    @ApiModelProperty(dataType = "Date",name = "activityTime",value = "活动时间")
    private String activityTime;

    @ApiModelProperty(dataType = "String",name = "address",value = "具体位置")
    private String address;

    @ApiModelProperty(dataType = "String",name = "hopePersonNum",value = "希望人数")
    private int hopePersonNum;

    @ApiModelProperty(dataType = "String", name = "personNumScope", value = "希望人数范围")
    private String personNumScope;

    @ApiModelProperty(dataType = "boolean", name = "hasVerify", value = "是否需要活动验证")
    private boolean hasVerify;

    public boolean isHasVerify() {
        return hasVerify;
    }

    public void setHasVerify(boolean hasVerify) {
        this.hasVerify = hasVerify;
    }

    @NotNull
    public String getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(@NotNull String activityNo) {
        this.activityNo = activityNo;
    }

    public String getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(String activityTime) {
        this.activityTime = activityTime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getHopePersonNum() {
        return hopePersonNum;
    }

    public void setHopePersonNum(int hopePersonNum) {
        this.hopePersonNum = hopePersonNum;
    }

    public String getPersonNumScope() {
        return personNumScope;
    }

    public void setPersonNumScope(String personNumScope) {
        this.personNumScope = personNumScope;
    }
}
