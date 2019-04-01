package cn.gokong.www.gokongmain.vo.local_activity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:活动小报出参
 *
 * @author ikook
 * @CreateDate 2018/10/11 17:10
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "QueryActivityTabloidOutput", description = "活动小报出参")
public class QueryActivityTabloidOutput {
    @ApiModelProperty(dataType = "String",name = "activityNo",value = "活动编码")
    private String activityNo;

    @ApiModelProperty(dataType = "String",name = "content",value = "小报内容")
    private String content;

    public String getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(String activityNo) {
        this.activityNo = activityNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
