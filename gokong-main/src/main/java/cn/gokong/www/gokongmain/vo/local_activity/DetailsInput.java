package cn.gokong.www.gokongmain.vo.local_activity;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:查询同城活动详情
 *
 * @author ikook
 * @CreateDate 2018/10/4 12:00
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "DetailsInput", description = "查询同城活动详情")
public class DetailsInput extends UserNoBase {
    @ApiModelProperty(dataType = "String",name = "activityNo",value = "活动编码")
    @NotNull(message = "活动编码不能为空")
    @Length(max = 50,message = "活动编码长度<=50")
    private String activityNo;

    public String getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(String activityNo) {
        this.activityNo = activityNo;
    }
}
