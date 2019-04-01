package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 同城活动
 * </p>
 *
 * @author ikook
 * @since 2018-10-04
 */
@TableName("local_activity")
public class LocalActivity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 活动编码
     */
    private String activityNo;

    /**
     * 活动发布者
     */
    private String userNo;

    /**
     * 活动标题
     */
    private String activityTitle;

    /**
     * 活动描述
     */
    private String activityDesc;

    /**
     * 活动城市
     */
    private String cityName;

    /**
     * 具体位置
     */
    private String address;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 活动时间
     */
    private Date activityTime;

    /**
     * 活动当前人数
     */
    private Integer personNum;

    /**
     * 希望人数
     */
    private Integer hopePersonNum;

    /**
     * 活动人数范围
     */
    private String personNumScope;

    /**
     * 是否收费
     */
    private boolean hasCharge;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 活动分类
     */
    private String activityType;

    /**
     * 活动图片
     */
    private String activityImg;

    /**
     * 群编码
     */
    private String groupNo;

    /**
     * 是否需要审核
     */
    private boolean hasVerify;

    public boolean isHasVerify() {
        return hasVerify;
    }

    public void setHasVerify(boolean hasVerify) {
        this.hasVerify = hasVerify;
    }

    public Integer getHopePersonNum() {
        return hopePersonNum;
    }

    public void setHopePersonNum(Integer hopePersonNum) {
        this.hopePersonNum = hopePersonNum;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(String activityNo) {
        this.activityNo = activityNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getActivityTitle() {
        return activityTitle;
    }

    public void setActivityTitle(String activityTitle) {
        this.activityTitle = activityTitle;
    }

    public String getActivityDesc() {
        return activityDesc;
    }

    public void setActivityDesc(String activityDesc) {
        this.activityDesc = activityDesc;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public String getPersonNumScope() {
        return personNumScope;
    }

    public void setPersonNumScope(String personNumScope) {
        this.personNumScope = personNumScope;
    }

    public boolean isHasCharge() {
        return hasCharge;
    }

    public void setHasCharge(boolean hasCharge) {
        this.hasCharge = hasCharge;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getActivityImg() {
        return activityImg;
    }

    public void setActivityImg(String activityImg) {
        this.activityImg = activityImg;
    }

    @Override
    public String toString() {
        return "LocalActivity{" +
        "id=" + id +
        ", activityNo=" + activityNo +
        ", userNo=" + userNo +
        ", activityTitle=" + activityTitle +
        ", activityDesc=" + activityDesc +
        ", cityName=" + cityName +
        ", address=" + address +
        ", createTime=" + createTime +
        ", activityTime=" + activityTime +
        ", personNum=" + personNum +
        ", personNumScope=" + personNumScope +
        ", hasCharge=" + hasCharge +
        ", price=" + price +
        ", activityType=" + activityType +
        ", activityImg=" + activityImg +
        "}";
    }
}
