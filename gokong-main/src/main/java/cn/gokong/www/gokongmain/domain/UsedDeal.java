package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 二手交易
 * </p>
 *
 * @author ikook
 * @since 2018-10-01
 */
@TableName("used_deal")
public class UsedDeal implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 二手交易编码
     */
    private String usedNo;

    /**
     * 标题
     */
    private String usedTitle;

    /**
     * 发布者编码
     */
    private String userNo;

    /**
     * 二手交易内容
     */
    private String usedContent;

    /**
     * 交易图片
     */
    private String usedImg;

    /**
     * 发布时间
     */
    private Date createTime;

    /**
     * 类型编码
     */
    private String typeNo;

    /**
     * 浏览数量
     */
    private Integer browseNum;

    /**
     * 评论数量
     */
    private Integer commentNum;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 发布城市
     */
    private String cityName;

    /**
     * 金币符号
     */
    private String goldSymbols;

    public String getGoldSymbols() {
        return goldSymbols;
    }

    public void setGoldSymbols(String goldSymbols) {
        this.goldSymbols = goldSymbols;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsedNo() {
        return usedNo;
    }

    public void setUsedNo(String usedNo) {
        this.usedNo = usedNo;
    }

    public String getUsedTitle() {
        return usedTitle;
    }

    public void setUsedTitle(String usedTitle) {
        this.usedTitle = usedTitle;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUsedContent() {
        return usedContent;
    }

    public void setUsedContent(String usedContent) {
        this.usedContent = usedContent;
    }

    public String getUsedImg() {
        return usedImg;
    }

    public void setUsedImg(String usedImg) {
        this.usedImg = usedImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTypeNo() {
        return typeNo;
    }

    public void setTypeNo(String typeNo) {
        this.typeNo = typeNo;
    }

    public Integer getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(Integer browseNum) {
        this.browseNum = browseNum;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "UsedDeal{" +
        "id=" + id +
        ", usedNo=" + usedNo +
        ", usedTitle=" + usedTitle +
        ", userNo=" + userNo +
        ", usedContent=" + usedContent +
        ", usedImg=" + usedImg +
        ", createTime=" + createTime +
        ", typeNo=" + typeNo +
        ", browseNum=" + browseNum +
        ", commentNum=" + commentNum +
        ", price=" + price +
        "}";
    }
}
