package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 咨询信息评价点赞记录
 * </p>
 *
 * @author ikook
 * @since 2018-09-15
 */
@TableName("information_evaluate_parise")
public class InformationEvaluateParise implements Serializable {

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
     * 点赞用户编码
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

    public Date getPariseDate() {
        return pariseDate;
    }

    public void setPariseDate(Date pariseDate) {
        this.pariseDate = pariseDate;
    }

    @Override
    public String toString() {
        return "InformationEvaluateParise{" +
        "id=" + id +
        ", informationNo=" + informationNo +
        ", evaluateNo=" + evaluateNo +
        ", userNo=" + userNo +
        ", pariseDate=" + pariseDate +
        "}";
    }
}
