package cn.gokong.www.gokongmain.vo.local_activity_apply;

import cn.gokong.www.gokongmain.vo.base.PageQueryBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:分页查询活动成员列表
 *
 * @author ikook
 * @CreateDate 2018/10/5 16:23
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryActivityMemberOutput", description = "分页查询活动成员列表")
public class PageQueryActivityMemberInput extends PageQueryBase {
    @ApiModelProperty(dataType = "String",name = "activityNo",value = "活动编码")
    @NotNull(message = "活动编码不能为空")
    @Length(max = 50,message = "活动编码长度<=50")
    private String activityNo;

    @NotNull
    public String getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(@NotNull String activityNo) {
        this.activityNo = activityNo;
    }
}
