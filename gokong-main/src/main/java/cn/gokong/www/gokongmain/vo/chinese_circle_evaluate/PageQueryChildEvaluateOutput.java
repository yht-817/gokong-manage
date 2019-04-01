package cn.gokong.www.gokongmain.vo.chinese_circle_evaluate;

import cn.gokong.www.base.util.RelativeDateFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 说明:分页查询子评论出参
 *
 * @author ikook
 * @CreateDate 2018/9/15 18:19
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryChildEvaluateOutput", description = "分页查询子评论出参")
public class PageQueryChildEvaluateOutput {
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
    @ApiModelProperty(dataType = "String",name = "replyDate",value = "回复时间")
    private String replyDate;
    @ApiModelProperty(dataType = "String",name = "replyContent",value = "回复内容")
    private String replyContent;

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

    public String getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {

        this.replyDate = RelativeDateFormat.format(replyDate);
    }

    public String getReplyContent() {
        return replyContent;
    }

    public void setReplyContent(String replyContent) {
        this.replyContent = replyContent;
    }
}
