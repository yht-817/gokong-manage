package cn.gokong.www.gokongmain.vo.Information_evaluate;

import cn.gokong.www.gokongmain.vo.base.InformationNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:发布评论入参
 *
 * @author ikook
 * @CreateDate 2018/9/15 20:00
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "ReleaseEvaluateInput", description = "发布评论入参")
public class ReleaseEvaluateInput extends InformationNoBase {
    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    @NotNull(message = "用户编码不能为空")
    @Length(max = 50,message = "用户编码长度<=50")
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "evaluateContent",value = "评论内容")
    @NotNull(message = "评论内容不能为空")
    @Length(max = 120,message = "评论内容长度<=120")
    private String evaluateContent;

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }
}
