package cn.gokong.www.gokongmain.vo.marketing_pop;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:查询营销弹窗出参
 *
 * @author ikook
 * @CreateDate 2018/9/19 15:29
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "QueryMarketingPopOutput", description = "查询营销弹窗出参")
public class QueryMarketingPopOutput {

    @ApiModelProperty(dataType = "String",name = "popNo",value = "弹窗编码")
    private String popNo;

    @ApiModelProperty(dataType = "String",name = "popPhoto",value = "弹窗图片")
    private String popPhoto;

    @ApiModelProperty(dataType = "String",name = "popUrl",value = "弹出跳转路径")
    private String popUrl;

    @ApiModelProperty(dataType = "String",name = "popRemark",value = "弹出说明")
    private String popRemark;

    public String getPopNo() {
        return popNo;
    }

    public void setPopNo(String popNo) {
        this.popNo = popNo;
    }

    public String getPopPhoto() {
        return popPhoto;
    }

    public void setPopPhoto(String popPhoto) {
        this.popPhoto = popPhoto;
    }

    public String getPopUrl() {
        return popUrl;
    }

    public void setPopUrl(String popUrl) {
        this.popUrl = popUrl;
    }

    public String getPopRemark() {
        return popRemark;
    }

    public void setPopRemark(String popRemark) {
        this.popRemark = popRemark;
    }
}
