package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 资讯信息表
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@TableName("information")
public class Information implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 资讯编码
     */
    private String informationNo;

    /**
     * 资讯模式 1023
     */
    private String informationMode;

    /**
     * 发布人ID
     */
    private String userNo;

    /**
     * 资讯标题
     */
    private String informationName;

    /**
     * 资讯图片
     */
    private String informationPhoto;

    /**
     * 图片尺寸类型 1040
     */
    private String photoSizeType;

    /**
     * 资讯链接
     */
    private String informationUrl;

    /**
     * 发布时间
     */
    private Date releaseDate;

    /**
     * 置顶标识 1024
     */
    private String setTopState;

    /**
     * 评论数
     */
    private Integer evaluateNum;

    /**
     * 点击数
     */
    private Integer clickNum;


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

    public String getInformationMode() {
        return informationMode;
    }

    public void setInformationMode(String informationMode) {
        this.informationMode = informationMode;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getInformationName() {
        return informationName;
    }

    public void setInformationName(String informationName) {
        this.informationName = informationName;
    }

    public String getInformationPhoto() {
        return informationPhoto;
    }

    public void setInformationPhoto(String informationPhoto) {
        this.informationPhoto = informationPhoto;
    }

    public String getPhotoSizeType() {
        return photoSizeType;
    }

    public void setPhotoSizeType(String photoSizeType) {
        this.photoSizeType = photoSizeType;
    }

    public String getInformationUrl() {
        return informationUrl;
    }

    public void setInformationUrl(String informationUrl) {
        this.informationUrl = informationUrl;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSetTopState() {
        return setTopState;
    }

    public void setSetTopState(String setTopState) {
        this.setTopState = setTopState;
    }

    public Integer getEvaluateNum() {
        return evaluateNum;
    }

    public void setEvaluateNum(Integer evaluateNum) {
        this.evaluateNum = evaluateNum;
    }

    public Integer getClickNum() {
        return clickNum;
    }

    public void setClickNum(Integer clickNum) {
        this.clickNum = clickNum;
    }

    @Override
    public String toString() {
        return "Information{" +
        "id=" + id +
        ", informationNo=" + informationNo +
        ", informationMode=" + informationMode +
        ", userNo=" + userNo +
        ", informationName=" + informationName +
        ", informationPhoto=" + informationPhoto +
        ", photoSizeType=" + photoSizeType +
        ", informationUrl=" + informationUrl +
        ", releaseDate=" + releaseDate +
        ", setTopState=" + setTopState +
        ", evaluateNum=" + evaluateNum +
        ", clickNum=" + clickNum +
        "}";
    }
}
