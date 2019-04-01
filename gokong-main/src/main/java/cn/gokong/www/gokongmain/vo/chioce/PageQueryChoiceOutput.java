package cn.gokong.www.gokongmain.vo.chioce;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:分页获取精选收藏出参
 *
 * @author ikook
 * @CreateDate 2018/9/11 15:34
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryInformationCollectionOutput", description = "分页获取资讯收藏出参")
public class PageQueryChoiceOutput {
    @ApiModelProperty(dataType = "String",name = "resourceNo",value = "精选编码")
    private String resourceNo;
    @ApiModelProperty(dataType = "String",name = "resourceName",value = "精选名称")
    private String resourceName;
    @ApiModelProperty(dataType = "String",name = "bannerPhoto",value = "精选图片")
    private String bannerPhoto;
    @ApiModelProperty(dataType = "String",name = "payAmount",value = "购买金额")
    private String payAmount;
    @ApiModelProperty(dataType = "String",name = "costAmount",value = "原价")
    private String costAmount;

    public String getResourceNo() {
        return resourceNo;
    }

    public void setResourceNo(String resourceNo) {
        this.resourceNo = resourceNo;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getBannerPhoto() {
        return bannerPhoto;
    }

    public void setBannerPhoto(String bannerPhoto) {
        this.bannerPhoto = bannerPhoto;
    }

    public String getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(String payAmount) {
        this.payAmount = payAmount;
    }

    public String getCostAmount() {
        return costAmount;
    }

    public void setCostAmount(String costAmount) {
        this.costAmount = costAmount;
    }
}
