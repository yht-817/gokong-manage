package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 资讯信息表评价记录
 * </p>
 *
 * @author ikook
 * @since 2018-09-15
 */
@TableName("information_evaluate")
public class InformationEvaluate implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 资讯编码
     */
    private String informationNo;

    /**
     * 评价编码
     */
    private String evaluateNo;

    /**
     * 评价用户编码
     */
    private String userNo;

    /**
     * 评价时间
     */
    private Date evaluateDate;

    /**
     * 评价内容
     */
    private String evaluateContent;

    /**
     * 点赞数
     */
    private Integer praiseNum;

    /**
     * 回复数
     */
    private Integer replyNum;


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

    public String getEvaluateNo() {
        return evaluateNo;
    }

    public void setEvaluateNo(String evaluateNo) {
        this.evaluateNo = evaluateNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public Date getEvaluateDate() {
        return evaluateDate;
    }

    public void setEvaluateDate(Date evaluateDate) {
        this.evaluateDate = evaluateDate;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
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
        return "InformationEvaluate{" +
        "id=" + id +
        ", informationNo=" + informationNo +
        ", evaluateNo=" + evaluateNo +
        ", userNo=" + userNo +
        ", evaluateDate=" + evaluateDate +
        ", evaluateContent=" + evaluateContent +
        ", praiseNum=" + praiseNum +
        ", replyNum=" + replyNum +
        "}";
    }
}
