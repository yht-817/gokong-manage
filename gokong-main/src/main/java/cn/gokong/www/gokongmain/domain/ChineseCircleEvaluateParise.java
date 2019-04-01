package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 华人圈评论点赞

 * </p>
 *
 * @author ikook
 * @since 2018-09-18
 */
@TableName("chinese_circle_evaluate_parise")
public class ChineseCircleEvaluateParise implements Serializable {

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
     * 用户编码
     */
    private String userNo;

    /**
     * 点赞时间
     */
    private Date pariseDate;


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

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public Date getPariseDate() {
        return pariseDate;
    }

    public void setPariseDate(Date pariseDate) {
        this.pariseDate = pariseDate;
    }

    @Override
    public String toString() {
        return "ChineseCircleEvaluateParise{" +
        "id=" + id +
        ", evaluateNo=" + evaluateNo +
        ", userNo=" + userNo +
        ", pariseDate=" + pariseDate +
        "}";
    }
}
