package cn.gokong.www.gokongmain.vo.brush;


import io.swagger.annotations.ApiModelProperty;

/**
 * 购买的订单列表
 */
public class BuyOrder {

    @ApiModelProperty(dataType = "String", name = "orderNo", value = "订单号")
    private String orderNo;
    @ApiModelProperty(dataType = "String", name = "productNo", value = "商品的编码")
    private String productNo;
    @ApiModelProperty(dataType = "String", name = "highPraise", value = "是好评还是不需要好评")
    private String highPraise;
    @ApiModelProperty(dataType = "String", name = "pictureUrl", value = "上传的截图")
    private String pictureUrl;
    @ApiModelProperty(dataType = "String", name = "payNo", value = "收款的账号")
    private String payNo;
    @ApiModelProperty(dataType = "String", name = "phoneNo", value = "手机号码")
    private String phoneNo;
    @ApiModelProperty(dataType = "String", name = "price", value = "价格")
    private String price;
    @ApiModelProperty(dataType = "String", name = "businessName", value = "商品的编码")
    private String businessName;
    @ApiModelProperty(dataType = "String", name = "shopurl", value = "购买的商品的图片")
    private String shopUrl;
    @ApiModelProperty(dataType = "String", name = "countries", value = "国家")
    private String countries;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getProductNo() {
        return productNo;
    }

    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }

    public String getHighPraise() {
        return highPraise;
    }

    public void setHighPraise(String highPraise) {
        this.highPraise = highPraise;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getPayNo() {
        return payNo;
    }

    public void setPayNo(String payNo) {
        this.payNo = payNo;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getShopUrl() {
        return shopUrl;
    }

    public void setShopUrl(String shopUrl) {
        this.shopUrl = shopUrl;
    }

    public String getCountries() {
        return countries;
    }

    public void setCountries(String countries) {
        this.countries = countries;
    }
}
