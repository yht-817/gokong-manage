package cn.gokong.www.gokongmain.vo.merchants_evaluate;

import cn.gokong.www.base.util.RelativeDateFormat;
import cn.gokong.www.gokongmain.vo.user.QueryUserOutput;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 说明:分页查询评论列表出参
 *
 * @author ikook
 * @CreateDate 2018/10/1 14:33
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@ApiModel(value = "PageQueryListOutput", description = "分页查询评论列表出参")
public class PageQueryListOutput extends QueryUserOutput {

    @ApiModelProperty(dataType = "String",name = "evaluateScore",value = "评分")
    private String evaluateScore;
    @ApiModelProperty(dataType = "String",name = "evaluateContent",value = "评论内容")
    private String evaluateContent;
    @ApiModelProperty(dataType = "String",name = "evaluateTime",value = "评论时间")
    private String evaluateTime;

    public String getEvaluateScore() {
        return evaluateScore;
    }

    public void setEvaluateScore(String evaluateScore) {
        this.evaluateScore = evaluateScore;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public String getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(Date evaluateTime) {
        this.evaluateTime = RelativeDateFormat.format(evaluateTime);
    }
}
