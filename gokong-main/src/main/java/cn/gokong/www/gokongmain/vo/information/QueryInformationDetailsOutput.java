package cn.gokong.www.gokongmain.vo.information;

import cn.gokong.www.base.util.RelativeDateFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 说明:查询资讯详情出参
 *
 * @author ikook
 * @CreateDate 2018/9/12 18:15
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "QueryInformationDetailsOutput", description = "查询资讯详情出参")
public class QueryInformationDetailsOutput {
    @ApiModelProperty(dataType = "String", name = "informationNo", value = "资讯编码")
    private String informationNo;

    @ApiModelProperty(dataType = "String", name = "informationName", value = "资讯名称")
    private String informationName;

    @ApiModelProperty(dataType = "String", name = "userNo", value = "用户编码")
    private String userNo;

    @ApiModelProperty(dataType = "String", name = "userHead", value = "用户头像")
    private String userHead;

    @ApiModelProperty(dataType = "String", name = "nickName", value = "用户昵称")
    private String nickName;

    @ApiModelProperty(dataType = "String", name = "releaseDate", value = "发布时间")
    private String releaseDate;

    @ApiModelProperty(dataType = "String", name = "informationUrl", value = "资讯链接")
    private String informationUrl;

    @ApiModelProperty(dataType = "String", name = "evaluateNum", value = "评论数量")
    private int evaluateNum;

    @ApiModelProperty(dataType = "String", name = "collection", value = "是否收藏")
    private boolean collection;

    @ApiModelProperty(dataType = "boolean", name = "focus", value = "是否关注")
    private boolean focus;

    @ApiModelProperty(dataType = "String", name = "informationMode", value = "判断是否是原创转载")
    private String informationMode;

    @ApiModelProperty(dataType = "String",name = "informationPhoto",value = "资讯图片")
    private String informationPhoto;

    @ApiModelProperty(dataType = "String", name = "informationContent", value = "资讯内容")
    private String informationContent;

    public String getInformationContent() {
        return informationContent;
    }

    public void setInformationContent(String informationContent) {
        this.informationContent = informationContent;
    }

    public String getInformationPhoto() {
        return informationPhoto;
    }

    public void setInformationPhoto(String informationPhoto) {
        this.informationPhoto = informationPhoto;
    }

    public boolean isFocus() {
        return focus;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }

    public String getInformationNo() {
        return informationNo;
    }

    public void setInformationNo(String informationNo) {
        this.informationNo = informationNo;
    }

    public String getInformationName() {
        return informationName;
    }

    public void setInformationName(String informationName) {
        this.informationName = informationName;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = RelativeDateFormat.format(releaseDate);
    }

    public String getInformationUrl() {
        return informationUrl;
    }

    public void setInformationUrl(String informationUrl) {
        this.informationUrl = informationUrl;
    }

    public int getEvaluateNum() {
        return evaluateNum;
    }

    public void setEvaluateNum(int evaluateNum) {
        this.evaluateNum = evaluateNum;
    }

    public boolean isCollection() {
        return collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }

    public String getInformationMode() {
        return informationMode;
    }

    public void setInformationMode(String informationMode) {
        this.informationMode = informationMode;
    }
}
