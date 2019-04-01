package cn.gokong.www.gokongmain.vo.chinese_circle_evaluate;

import cn.gokong.www.base.util.RelativeDateFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 说明:分页查询华人圈评论出参
 *
 * @author ikook
 * @CreateDate 2018/9/18 15:35
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryCircleEvaluateOutput", description = "分页查询华人圈评论出参")
public class PageQueryCircleEvaluateOutput {

    @ApiModelProperty(dataType = "String",name = "fromUserNo",value = "回复者编码")
    private String fromUserNo;
    @ApiModelProperty(dataType = "String",name = "fromUserHead",value = "回复者头像")
    private String fromUserHead;
    @ApiModelProperty(dataType = "String",name = "fromUserNick",value = "回复者昵称")
    private String fromUserNick;

    @ApiModelProperty(dataType = "String",name = "toUserNo",value = "被回复者编码")
    private String toUserNo;
    @ApiModelProperty(dataType = "String",name = "toUserNick",value = "被回复者昵称")
    private String toUserNick;

    @ApiModelProperty(dataType = "String",name = "evaluateNo",value = "评论编码")
    private String evaluateNo;
    @ApiModelProperty(dataType = "String",name = "evaluateContent",value = "评论内容")
    private String evaluateContent;
    @ApiModelProperty(dataType = "String",name = "evaluateDate",value = "评论时间")
    private String evaluateDate;

    @ApiModelProperty(dataType = "String",name = "replyNum",value = "评论回复数")
    private int replyNum;
    @ApiModelProperty(dataType = "String",name = "praiseNum",value = "点赞数量")
    private int praiseNum;
    @ApiModelProperty(dataType = "String",name = "praise",value = "是否点赞")
    private boolean liked;

    public String getFromUserNo() {
        return fromUserNo;
    }

    public void setFromUserNo(String fromUserNo) {
        this.fromUserNo = fromUserNo;
    }

    public String getFromUserHead() {
        return fromUserHead;
    }

    public void setFromUserHead(String fromUserHead) {
        this.fromUserHead = fromUserHead;
    }

    public String getFromUserNick() {
        return fromUserNick;
    }

    public void setFromUserNick(String fromUserNick) {
        this.fromUserNick = fromUserNick;
    }

    public String getToUserNo() {
        return toUserNo;
    }

    public void setToUserNo(String toUserNo) {
        this.toUserNo = toUserNo;
    }

    public String getToUserNick() {
        return toUserNick;
    }

    public void setToUserNick(String toUserNick) {
        this.toUserNick = toUserNick;
    }

    public void setEvaluateDate(String evaluateDate) {
        this.evaluateDate = evaluateDate;
    }

    public String getEvaluateNo() {
        return evaluateNo;
    }

    public void setEvaluateNo(String evaluateNo) {
        this.evaluateNo = evaluateNo;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public String getEvaluateDate() {
        return evaluateDate;
    }

    public void setEvaluateDate(Date evaluateDate) {
        this.evaluateDate = RelativeDateFormat.format(evaluateDate);
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public int getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(int praiseNum) {
        this.praiseNum = praiseNum;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
