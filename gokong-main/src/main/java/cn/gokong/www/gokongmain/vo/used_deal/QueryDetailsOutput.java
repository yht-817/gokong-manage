package cn.gokong.www.gokongmain.vo.used_deal;

import cn.gokong.www.base.util.RelativeDateFormat;
import cn.gokong.www.gokongmain.vo.user.QueryUserOutput;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 说明:查询二手交易详情
 *
 * @author ikook
 * @CreateDate 2018/10/1 16:44
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "QueryDetailsOutput", description = "查询二手交易详情")
public class QueryDetailsOutput extends QueryUserOutput {

    @ApiModelProperty(dataType = "String", name = "createTime", value = "创建时间")
    private String createTime;
    @ApiModelProperty(dataType = "String", name = "price", value = "价格")
    private String price;
    @ApiModelProperty(dataType = "String",name = "usedContent",value = "内容")
    private String usedContent;
    @ApiModelProperty(dataType = "String",name = "usedImg",value = "图片")
    private String usedImg;
    @ApiModelProperty(dataType = "String",name = "collection",value = "是否收藏")
    private boolean collection;
    @ApiModelProperty(dataType = "String",name = "usedTitle",value = "标题")
    private String usedTitle;

    public String getUsedTitle() {
        return usedTitle;
    }

    public void setUsedTitle(String usedTitle) {
        this.usedTitle = usedTitle;
    }

    public boolean isCollection() {
        return collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {

        this.createTime = RelativeDateFormat.format(createTime);
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
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
}
