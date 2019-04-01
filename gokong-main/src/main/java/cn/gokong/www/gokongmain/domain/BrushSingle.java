package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author tom
 * @since 2018-09-25
 */
@TableName("brush_single")
public class BrushSingle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 上传用户ID
     */
    private String updateId;

    /**
     * 商品编码
     */
    private String productNo;

    /**
     * 是否留好评
     */
    private Integer highPraise;

    /**
     * 剩余的时间
     */
    private Date remaineTime;

    /**
     * 限制领取的单数
     */
    private Integer limitOrder;

    /**
     * 总单数
     */
    private Integer totalOrder;

    /**
     * 领取的单数
     */
    private Integer getOrder;

    /**
     * 领取的关键词
     */
    private String keyword;

    /**
     * 页码
     */
    private Integer pageNo;

    /**
     * 店铺名
     */
    private String businessName;

    /**
     * 上传商品的图片
     */
    private String pictureUrl;

    /**
     * 价格
     */
    private BigDecimal price;

    /**
     * 公司名称
     */
    private String branch;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpdateId() {
        return updateId;
    }

    public void setUpdateId(String updateId) {
        this.updateId = updateId;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public Integer getHighPraise() {
        return highPraise;
    }

    public void setHighPraise(Integer highPraise) {
        this.highPraise = highPraise;
    }

    public Date getRemaineTime() {
        return remaineTime;
    }

    public void setRemaineTime(Date remaineTime) {
        this.remaineTime = remaineTime;
    }

    public Integer getLimitOrder() {
        return limitOrder;
    }

    public void setLimitOrder(Integer limitOrder) {
        this.limitOrder = limitOrder;
    }

    public Integer getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(Integer totalOrder) {
        this.totalOrder = totalOrder;
    }

    public Integer getGetOrder() {
        return getOrder;
    }

    public void setGetOrder(Integer getOrder) {
        this.getOrder = getOrder;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public String toString() {
        return "BrushSingle{" +
                "id=" + id +
                ", updateId=" + updateId +
                ", productNo=" + productNo +
                ", highPraise=" + highPraise +
                ", remaineTime=" + remaineTime +
                ", limitOrder=" + limitOrder +
                ", totalOrder=" + totalOrder +
                ", getOrder=" + getOrder +
                ", keyword=" + keyword +
                ", pageNo=" + pageNo +
                ", businessName=" + businessName +
                ", pictureUrl=" + pictureUrl +
                ", price=" + price +
                ", branch=" + branch +
                "}";
    }
}
