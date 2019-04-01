package cn.gokong.www.gokongmain.vo.overseas_business;

import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import cn.hutool.core.util.StrUtil;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:查询详情出参
 *
 * @author ikook
 * @CreateDate 2018/9/25 11:46
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "QueryDetailsOutput", description = "查询详情出参")
public class QueryDetailsOutput {
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
     * 内容
     */
    @ApiModelProperty(dataType = "String",name = "content",value = "内容")
    private String content;

    /**
     * 参加状态
     */
    @ApiModelProperty(dataType = "boolean",name = "joinState",value = "是否已参加 (10460001 未参加 10460002申请中 10460003 已参加)")
    private String joinState;

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getJoinState() {
        return joinState;
    }

    public void setJoinState(String joinState) {
        this.joinState = StrUtil.isEmpty(joinState)? SysCodeEnum.CODE_10460001.getCode():

                joinState.equals(SysCodeEnum.CODE_10460004.getCode())?SysCodeEnum.CODE_10460001.getCode():joinState;
    }
}
