package cn.gokong.www.gokongmain.domain;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tom
 * @since 2018-09-20
 */
@TableName("cattle_people")
public class CattlePeople implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 用户编码
     */
    private String userNo;

    /**
     * 牛人姓名
     */
    private String cattlePeopleName;

    /**
     * 牛人的工作
     */
    private String cattlePeopleWork;

    /**
     * 牛人的简介
     */
    private String cattlePeopleAbstract;

    /**
     * 牛人地址
     */
    private String cattlePeopleAddress;

    /**
     * 牛人标签
     */
    private String cattlePeopleLabel;

    /**
     * 牛人的头像
     */
    private String cattlePeoplePhoto;

    /**
     * 所在城市
     */
    private String cattlePeopleCityNo;

    /**
     * 排名
     */
    private String cattlePeopleRanke;

    /**
     * 用户的身价（认识他支付的钱）
     */
    private BigDecimal cattleWorth;


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

    public String getCattlePeopleName() {
        return cattlePeopleName;
    }

    public void setCattlePeopleName(String cattlePeopleName) {
        this.cattlePeopleName = cattlePeopleName;
    }

    public String getCattlePeopleWork() {
        return cattlePeopleWork;
    }

    public void setCattlePeopleWork(String cattlePeopleWork) {
        this.cattlePeopleWork = cattlePeopleWork;
    }

    public String getCattlePeopleAbstract() {
        return cattlePeopleAbstract;
    }

    public void setCattlePeopleAbstract(String cattlePeopleAbstract) {
        this.cattlePeopleAbstract = cattlePeopleAbstract;
    }

    public String getCattlePeopleAddress() {
        return cattlePeopleAddress;
    }

    public void setCattlePeopleAddress(String cattlePeopleAddress) {
        this.cattlePeopleAddress = cattlePeopleAddress;
    }

    public String getCattlePeopleLabel() {
        return cattlePeopleLabel;
    }

    public void setCattlePeopleLabel(String cattlePeopleLabel) {
        this.cattlePeopleLabel = cattlePeopleLabel;
    }

    public String getCattlePeoplePhoto() {
        return cattlePeoplePhoto;
    }

    public void setCattlePeoplePhoto(String cattlePeoplePhoto) {
        this.cattlePeoplePhoto = cattlePeoplePhoto;
    }

    public String getCattlePeopleCityNo() {
        return cattlePeopleCityNo;
    }

    public void setCattlePeopleCityNo(String cattlePeopleCityNo) {
        this.cattlePeopleCityNo = cattlePeopleCityNo;
    }

    public String getCattlePeopleRanke() {
        return cattlePeopleRanke;
    }

    public void setCattlePeopleRanke(String cattlePeopleRanke) {
        this.cattlePeopleRanke = cattlePeopleRanke;
    }

    public BigDecimal getCattleWorth() {
        return cattleWorth;
    }

    public void setCattleWorth(BigDecimal cattleWorth) {
        this.cattleWorth = cattleWorth;
    }

    @Override
    public String toString() {
        return "CattlePeople{" +
        "id=" + id +
        ", userNo=" + userNo +
        ", cattlePeopleName=" + cattlePeopleName +
        ", cattlePeopleWork=" + cattlePeopleWork +
        ", cattlePeopleAbstract=" + cattlePeopleAbstract +
        ", cattlePeopleAddress=" + cattlePeopleAddress +
        ", cattlePeopleLabel=" + cattlePeopleLabel +
        ", cattlePeoplePhoto=" + cattlePeoplePhoto +
        ", cattlePeopleCityNo=" + cattlePeopleCityNo +
        ", cattlePeopleRanke=" + cattlePeopleRanke +
        ", cattleWorth=" + cattleWorth +
        "}";
    }
}
