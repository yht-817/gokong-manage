package cn.gokong.www.gokongmain.vo.merchants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:获取商户详情出参
 *
 * @author ikook
 * @CreateDate 2018/9/11 14:23
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "QueryMerchantsDetailsOutput", description = "获取商户详情出参")
public class QueryMerchantsDetailsOutput {

    @ApiModelProperty(dataType = "String",name = "merchantsNo",value = "商户编码")
    private String merchantsNo;

    @ApiModelProperty(dataType = "String",name = "merchantsName",value = "商户名称")
    private String merchantsName;

    @ApiModelProperty(dataType = "String",name = "titleImg",value = "标题图片")
    private String titleImg;

    @ApiModelProperty(dataType = "String",name = "address",value = "商户地址")
    private String address;

    @ApiModelProperty(dataType = "String",name = "phoneNo",value = "电话号码")
    private String phoneNo;

    @ApiModelProperty(dataType = "String",name = "merchantsDesc",value = "描述")
    private String merchantsDesc;

    @ApiModelProperty(dataType = "String",name = "merchantsScore",value = "商户评分")
    private int merchantsScore;

    @ApiModelProperty(dataType = "String",name = "collection",value = "是否收藏")
    private boolean collection;

    @ApiModelProperty(dataType = "String",name = "evaluateCount",value = "评论数量")
    private int evaluateCount;

    public int getEvaluateCount() {
        return evaluateCount;
    }

    public void setEvaluateCount(int evaluateCount) {
        this.evaluateCount = evaluateCount;
    }

    public int getMerchantsScore() {
        return merchantsScore;
    }

    public void setMerchantsScore(int merchantsScore) {
        this.merchantsScore = merchantsScore;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getMerchantsDesc() {
        return merchantsDesc;
    }

    public void setMerchantsDesc(String merchantsDesc) {
        this.merchantsDesc = merchantsDesc;
    }

    public boolean isCollection() {
        return collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }
}
