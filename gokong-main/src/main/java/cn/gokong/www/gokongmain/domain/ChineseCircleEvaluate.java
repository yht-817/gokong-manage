package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 华人圈评论表
 * </p>
 *
 * @author ikook
 * @since 2018-09-18
 */
@TableName("chinese_circle_evaluate")
public class ChineseCircleEvaluate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 评论编码
     */
    private String evaluateNo;

    /**
     * 华人圈编码
     */
    private String circleNo;

    /**
     * 用户编码
     */
    private String fromUserNo;

    /**
     * 评论内容
     */
    private String evaluateContent;

    /**
     * 被评论者用户编码
     */
    private String toUserNo;

    /**
     * 评论时间
     */
    private Date evaluateDate;

    /**
     * 点赞数量
     */
    private Integer praiseNum;

    /**
     * 回复数量
     */
    private Integer replyNum;

    public String getCircleNo() {
        return circleNo;
    }

    public void setCircleNo(String circleNo) {
        this.circleNo = circleNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getToUserNo() {
        return toUserNo;
    }

    public void setToUserNo(String toUserNo) {
        this.toUserNo = toUserNo;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public Date getEvaluateDate() {
        return evaluateDate;
    }

    public void setEvaluateDate(Date evaluateDate) {
        this.evaluateDate = evaluateDate;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }

    public Integer getReplyNum() {
        return replyNum;
    }

    public void setReplyNum(Integer replyNum) {
        this.replyNum = replyNum;
    }

    @Override
    public String toString() {
        return "ChineseCircleEvaluate{" +
                "id='" + id + '\'' +
                ", evaluateNo='" + evaluateNo + '\'' +
                ", circleNo='" + circleNo + '\'' +
                ", fromUserNo='" + fromUserNo + '\'' +
                ", evaluateContent='" + evaluateContent + '\'' +
                ", toUserNo='" + toUserNo + '\'' +
                ", evaluateDate=" + evaluateDate +
                ", praiseNum=" + praiseNum +
                ", replyNum=" + replyNum +
                '}';
    }
}
