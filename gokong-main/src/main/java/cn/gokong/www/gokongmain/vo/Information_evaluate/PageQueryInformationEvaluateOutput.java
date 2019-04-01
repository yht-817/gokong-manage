package cn.gokong.www.gokongmain.vo.Information_evaluate;

import cn.gokong.www.base.util.RelativeDateFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 说明:分页查询资讯评论列表出参
 *
 * @author ikook
 * @CreateDate 2018/9/15 18:19
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryInformationEvaluateOutput", description = "分页查询资讯评论列表出参")
public class PageQueryInformationEvaluateOutput {
    @ApiModelProperty(dataType = "String",name = "evaluateNo",value = "评论编码")
    private String evaluateNo;
    @ApiModelProperty(dataType = "String",name = "evaluateContent",value = "评论内容")
    private String evaluateContent;
    @ApiModelProperty(dataType = "String",name = "evaluateDate",value = "评论时间")
    private String evaluateDate;
    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    private String userNo;
    @ApiModelProperty(dataType = "String",name = "nickName",value = "用户昵称")
    private String nickName;
    @ApiModelProperty(dataType = "String",name = "userHead",value = "用户头像")
    private String userHead;
    @ApiModelProperty(dataType = "String",name = "replyNum",value = "评论回复数")
    private int replyNum;
    @ApiModelProperty(dataType = "String",name = "praise",value = "是否点赞")
    private boolean praise;

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

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public int getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(int replyNum) {
        this.replyNum = replyNum;
    }

    public boolean isPraise() {
        return praise;
    }

    public void setPraise(boolean praise) {
        this.praise = praise;
    }
}
