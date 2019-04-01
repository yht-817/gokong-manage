package cn.gokong.www.gokongmain.vo.Information_evaluate;

import cn.gokong.www.gokongmain.vo.base.InformationNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:回复评论入参
 *
 * @author ikook
 * @CreateDate 2018/9/15 20:00
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "ReplyEvaluateInput", description = "回复评论入参")
public class ReplyEvaluateInput extends InformationNoBase {
    @ApiModelProperty(dataType = "String",name = "userNo",value = "评论编码")
    @NotNull(message = "评论编码不能为空")
    @Length(max = 50,message = "评论编码长度<=50")
    private String evaluateNo;

    @ApiModelProperty(dataType = "String",name = "fromUserNo",value = "回复用户编码")
    @NotNull(message = "回复用户编码不能为空")
    @Length(max = 50,message = "回复用户编码长度<=50")
    private String fromUserNo;

    @ApiModelProperty(dataType = "String",name = "replyContent",value = "回复内容")
    @NotNull(message = "回复内容不能为空")
    @Length(max = 120,message = "回复内容长度<=120")
    private String replyContent;

    @ApiModelProperty(dataType = "String",name = "toUserNo",value = "被回复用户编码")
    @Length(max = 50,message = "被回复用户编码长度<=50")
    private String toUserNo;

    public String getEvaluateNo() {
        return evaluateNo;
    }

    public void setEvaluateNo(String evaluateNo) {
        this.evaluateNo = evaluateNo;
    }

    public String getFromUserNo() {
        return fromUserNo;
    }

    public void setFromUserNo(String fromUserNo) {
        this.fromUserNo = fromUserNo;
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }

    public String getToUserNo() {
        return toUserNo;
    }

    public void setToUserNo(String toUserNo) {
        this.toUserNo = toUserNo;
    }
}
