package cn.gokong.www.gokongmain.vo.local_activity_apply;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:活动报名入参
 *
 * @author ikook
 * @CreateDate 2018/10/5 14:25
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "UserApplyInput", description = "活动报名入参")
public class UserApplyInput extends UserNoBase {

    @ApiModelProperty(dataType = "String",name = "activityNo",value = "活动编码")
    @NotNull(message = "活动编码不能为空")
    @Length(max = 50,message = "活动编码长度<=50")
    private String activityNo;

    @ApiModelProperty(dataType = "String",name = "phone",value = "联系电话")
    @NotNull(message = "联系电话不能为空")
    @Length(max = 20,message = "联系电话长度<=20")
    private String phone;

    @ApiModelProperty(dataType = "String",name = "introduce",value = "自我介绍")
    @NotNull(message = "自我介绍不能为空")
    @Length(max = 100,message = "自我介绍长度<=100")
    private String introduce;

    @ApiModelProperty(dataType = "String",name = "sex",value = "性别")
    @NotNull(message = "性别不能为空")
    @Length(max = 5,message = "性别长度<=5")
    private String sex;

    @NotNull
    public String getPhone() {
        return phone;
    }

    public void setPhone(@NotNull String phone) {
        this.phone = phone;
    }

    @NotNull
    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(@NotNull String introduce) {
        this.introduce = introduce;
    }

    @NotNull
    public String getSex() {
        return sex;
    }

    public void setSex(@NotNull String sex) {
        this.sex = sex;
    }

    @NotNull
    public String getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(@NotNull String activityNo) {
        this.activityNo = activityNo;
    }
}
