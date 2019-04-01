package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 咨询信息评价回复记录表
 * </p>
 *
 * @author ikook
 * @since 2018-09-15
 */
@TableName("information_evaluate_reply")
public class InformationEvaluateReply implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 资讯编码
     */
    private String informationNo;

    /**
     * 回复编码
     */
    private String replyNo;

    /**
     * 主评论编码
     */
    private String evaluateNo;

    /**
     * 回复用户编码
     */
    private String fromUserNo;

    /**
     * 回复时间
     */
    private Date replyDate;

    /**
     * 回复内容
     */
    private String replyContent;

    /**
     * 被回复用户编码
     */
    private String toUserNo;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInformationNo() {
        return informationNo;
    }

    public void setInformationNo(String informationNo) {
        this.informationNo = informationNo;
    }

    public String getReplyNo() {
        return replyNo;
    }

    public void setReplyNo(String replyNo) {
        this.replyNo = replyNo;
    }

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

    public Date getReplyDate() {
        return replyDate;
    }

    public void setReplyDate(Date replyDate) {
        this.replyDate = replyDate;
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

    @Override
    public String toString() {
        return "InformationEvaluateReply{" +
        "id=" + id +
        ", informationNo=" + informationNo +
        ", replyNo=" + replyNo +
        ", evaluateNo=" + evaluateNo +
        ", fromUserNo=" + fromUserNo +
        ", replyDate=" + replyDate +
        ", replyContent=" + replyContent +
        ", toUserNo=" + toUserNo +
        "}";
    }
}
