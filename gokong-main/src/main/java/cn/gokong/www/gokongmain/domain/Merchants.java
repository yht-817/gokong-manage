package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 商户数据
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
@TableName("merchants")
public class Merchants implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 商户编码
     */
    private String merchantsNo;

    /**
     * 商户名称
     */
    private String merchantsName;

    /**
     * 标题图片
     */
    private String titleImg;

    /**
     * 描述
     */
    private String merchantsDesc;

    /**
     * 商户地址
     */
    private String address;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 商户类型
     */
    private String merchantsType;

    /**
     * 商户评分
     */
    private int merchantsScore;

    /**
     * 评价数量
     */
    private int evaluateNum;

    /**
     * 活得总评分
     */
    private int scoreTotal;

    /**
     * 商户联系电话
     */
    private String phoneNo;

    /**
     * 发布城市
     */
    private String cityName;


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public int getEvaluateNum() {
        return evaluateNum;
    }

    public void setEvaluateNum(int evaluateNum) {
        this.evaluateNum = evaluateNum;
    }

    public int getScoreTotal() {
        return scoreTotal;
    }

    public void setScoreTotal(int scoreTotal) {
        this.scoreTotal = scoreTotal;
    }

    public int getMerchantsScore() {
        return scoreTotal/evaluateNum;
    }

    public void setMerchantsScore(int merchantsScore) {
        this.merchantsScore = merchantsScore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMerchantsNo() {
        return merchantsNo;
    }

    public void setMerchantsNo(String merchantsNo) {
        this.merchantsNo = merchantsNo;
    }

    public String getMerchantsName() {
        return merchantsName;
    }

    public void setMerchantsName(String merchantsName) {
        this.merchantsName = merchantsName;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getMerchantsDesc() {
        return merchantsDesc;
    }

    public void setMerchantsDesc(String merchantsDesc) {
        this.merchantsDesc = merchantsDesc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getMerchantsType() {
        return merchantsType;
    }

    public void setMerchantsType(String merchantsType) {
        this.merchantsType = merchantsType;
    }

    @Override
    public String toString() {
        return "Merchants{" +
                "id='" + id + '\'' +
                ", merchantsNo='" + merchantsNo + '\'' +
                ", merchantsName='" + merchantsName + '\'' +
                ", titleImg='" + titleImg + '\'' +
                ", merchantsDesc='" + merchantsDesc + '\'' +
                ", address='" + address + '\'' +
                ", createTime=" + createTime +
                ", priority=" + priority +
                ", merchantsType='" + merchantsType + '\'' +
                ", merchantsScore=" + merchantsScore +
                ", evaluateNum=" + evaluateNum +
                ", scoreTotal=" + scoreTotal +
                ", phoneNo='" + phoneNo + '\'' +
                '}';
    }
}
