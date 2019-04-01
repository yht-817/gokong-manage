package cn.gokong.www.gokongmain.vo.used_deal;

import cn.gokong.www.base.util.RelativeDateFormat;
import cn.gokong.www.gokongmain.vo.user.QueryUserOutput;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 说明:分页查询二手交易列表出参
 *
 * @author ikook
 * @CreateDate 2018/10/1 16:31
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryListOutput", description = "分页查询二手交易列表出参")
public class PageQueryListOutput extends QueryUserOutput {
    @ApiModelProperty(dataType = "String",name = "usedNo",value = "编码")
    private String usedNo;
    @ApiModelProperty(dataType = "String",name = "usedContent",value = "内容")
    private String usedContent;
    @ApiModelProperty(dataType = "String",name = "usedImg",value = "图片")
    private String usedImg;
    @ApiModelProperty(dataType = "String",name = "createTime",value = "创建时间")
    private String createTime;
    @ApiModelProperty(dataType = "String",name = "browseNum",value = "浏览量")
    private String browseNum;
    @ApiModelProperty(dataType = "String",name = "commentNum",value = "评论量")
    private String commentNum;
    @ApiModelProperty(dataType = "String",name = "price",value = "价格")
    private String price;
    @ApiModelProperty(dataType = "String",name = "usedTitle",value = "标题")
    private String usedTitle;

    public String getUsedTitle() {
        return usedTitle;
    }

    public void setUsedTitle(String usedTitle) {
        this.usedTitle = usedTitle;
    }

    public String getUsedNo() {
        return usedNo;
    }

    public void setUsedNo(String usedNo) {
        this.usedNo = usedNo;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = RelativeDateFormat.format(createTime);
    }

    public String getBrowseNum() {
        return browseNum;
    }

    public void setBrowseNum(String browseNum) {
        this.browseNum = browseNum;
    }

    public String getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(String commentNum) {
        this.commentNum = commentNum;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
