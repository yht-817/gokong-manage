package cn.gokong.www.gokongmain.vo.merchants;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:分页查询商户数据-出参
 *
 * @author ikook
 * @CreateDate 2018/9/11 11:30
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryMerchantsOutput", description = "分页查询商户数据-出参")
public class PageQueryMerchantsOutput {

    @ApiModelProperty(dataType = "String",name = "merchantsNo",value = "商户编码")
    private String merchantsNo;

    @ApiModelProperty(dataType = "String",name = "merchantsName",value = "商户名称")
    private String merchantsName;

    @ApiModelProperty(dataType = "String",name = "titleImg",value = "标题图片")
    private String titleImg;

    @ApiModelProperty(dataType = "String",name = "address",value = "商户地址")
    private String address;

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
}
