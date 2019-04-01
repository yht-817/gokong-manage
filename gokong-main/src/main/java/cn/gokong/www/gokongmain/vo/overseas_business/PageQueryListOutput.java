package cn.gokong.www.gokongmain.vo.overseas_business;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:分页查询列表出参
 *
 * @author ikook
 * @CreateDate 2018/9/25 11:09
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryListOutput", description = "分页查询列表出参")
public class PageQueryListOutput {
    /**
     * 创业编码
     */
    @ApiModelProperty(dataType = "String",name = "businessNo",value = "创业编码")
    private String businessNo;

    /**
     * 创业标题
     */
    @ApiModelProperty(dataType = "String",name = "businessTitle",value = "创业标题")
    private String businessTitle;

    /**
     * 标题图片
     */
    @ApiModelProperty(dataType = "String",name = "titleImg",value = "标题图片")
    private String titleImg;

    /**
     * 目标内容
     */
    @ApiModelProperty(dataType = "String",name = "targetContent",value = "目标内容")
    private String targetContent;

    /**
     * 目标值
     */
    @ApiModelProperty(dataType = "String",name = "targetContent",value = "目标内容")
    @JsonIgnore
    private Integer targetValue;

    /**
     * 已完成目标百分比
     */
    @ApiModelProperty(dataType = "double",name = "completeTarget",value = "已完成目标百分比")
    private String completeTarget;

    public String getBusinessNo() {
        return businessNo;
    }

    public void setBusinessNo(String businessNo) {
        this.businessNo = businessNo;
    }

    public String getBusinessTitle() {
        return businessTitle;
    }

    public void setBusinessTitle(String businessTitle) {
        this.businessTitle = businessTitle;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getTargetContent() {
        return targetContent;
    }

    public void setTargetContent(String targetContent) {
        this.targetContent = targetContent;
    }

    public Integer getTargetValue() {
        return targetValue;
    }

    public void setTargetValue(Integer targetValue) {
        this.targetValue = targetValue;
    }

    public String getCompleteTarget() {
        return completeTarget;
    }

    public void setCompleteTarget(String completeTarget) {
        this.completeTarget = completeTarget;
    }
}
