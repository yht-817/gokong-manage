package cn.gokong.www.gokongmain.vo.chinese_circle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:分页获取华人圈收藏出参
 *
 * @author ikook
 * @CreateDate 2018/9/11 15:34
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryChineseCircleOutput", description = "分页获取华人圈收藏出参")
public class PageQueryChineseCircleOutput {

    @ApiModelProperty(dataType = "String",name = "circleNo",value = "编码")
    private String circleNo;

    @ApiModelProperty(dataType = "String",name = "circleImg",value = "图片")
    private String circleImg;

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "userHead",value = "用户头像")
    private String userHead;

    @ApiModelProperty(dataType = "String",name = "nickName",value = "用户昵称")
    private String nickName;

    @ApiModelProperty(dataType = "String",name = "instructions",value = "说明")
    private String instructions;

    public String getCircleNo() {
        return circleNo;
    }

    public void setCircleNo(String circleNo) {
        this.circleNo = circleNo;
    }

    public String getCircleImg() {
        return circleImg;
    }

    public void setCircleImg(String circleImg) {
        this.circleImg = circleImg;
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

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }
}
