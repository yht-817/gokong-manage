package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 商户评价表
 * </p>
 *
 * @author ikook
 * @since 2018-09-14
 */
@TableName("merchants_evaluate")
public class MerchantsEvaluate implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 用户编码
     */
    private String userNo;

    /**
     * 商户编码
     */
    private String merchantsNo;

    /**
     * 商户评价
     */
    private String evaluateContent;

    /**
     * 评价时间
     */
    private Date evaluateTime;

    /**
     * 评分
     */
    private Integer evaluateScore;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getMerchantsNo() {
        return merchantsNo;
    }

    public void setMerchantsNo(String merchantsNo) {
        this.merchantsNo = merchantsNo;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public Date getEvaluateTime() {
        return evaluateTime;
    }

    public void setEvaluateTime(Date evaluateTime) {
        this.evaluateTime = evaluateTime;
    }

    public Integer getEvaluateScore() {
        return evaluateScore;
    }

    public void setEvaluateScore(Integer evaluateScore) {
        this.evaluateScore = evaluateScore;
    }

    @Override
    public String toString() {
        return "MerchantsEvaluate{" +
        "id=" + id +
        ", userNo=" + userNo +
        ", merchantsNo=" + merchantsNo +
        ", evaluateContent=" + evaluateContent +
        ", evaluateTime=" + evaluateTime +
        ", evaluateScore=" + evaluateScore +
        "}";
    }
}
