package cn.gokong.www.gokongmain.vo.information;

import cn.gokong.www.base.util.RelativeDateFormat;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 说明:分页获取资讯首页出参
 *
 * @author ikook
 * @CreateDate 2018/9/11 15:34
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryInformationHomeOutput", description = "分页获取资讯首页出参")
public class PageQueryInformationHomeOutput {
    @ApiModelProperty(dataType = "String",name = "informationNo",value = "资讯编码")
    private String informationNo;
    @ApiModelProperty(dataType = "String",name = "informationName",value = "资讯名称")
    private String informationName;
    @ApiModelProperty(dataType = "String",name = "informationPhoto",value = "资讯图片")
    private String informationPhoto;
    @ApiModelProperty(dataType = "String",name = "setTopState",value = "资讯置顶")
    private String setTopState;
    @ApiModelProperty(dataType = "String",name = "userHead",value = "用户头像")
    private String userHead;
    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    private String userNo;
    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户名称")
    private String nickName;
    @ApiModelProperty(dataType = "String",name = "clickNum",value = "点击数量")
    private int clickNum;
    @ApiModelProperty(dataType = "String",name = "evaluateNum",value = "评论数量")
    private int evaluateNum;
    @ApiModelProperty(dataType = "String",name = "releaseDate",value = "发布时间")
    private String releaseDate;
    @ApiModelProperty(dataType = "String",name = "photoSizeType",value = "布局方式")
    private String photoSizeType;

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = RelativeDateFormat.format(releaseDate);
    }

    public String getPhotoSizeType() {
        return photoSizeType;
    }

    public void setPhotoSizeType(String photoSizeType) {
        this.photoSizeType = photoSizeType;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getClickNum() {
        return clickNum;
    }

    public void setClickNum(int clickNum) {
        this.clickNum = clickNum;
    }

    public int getEvaluateNum() {
        return evaluateNum;
    }

    public void setEvaluateNum(int evaluateNum) {
        this.evaluateNum = evaluateNum;
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

    public String getSetTopState() {
        return setTopState;
    }

    public void setSetTopState(String setTopState) {
        if (setTopState.equals(SysCodeEnum.CODE_10250001.getCode())){
            setTopState = SysCodeEnum.CODE_10250001.getMsg();
        }else if (setTopState.equals(SysCodeEnum.CODE_10250002.getCode())){
            setTopState = SysCodeEnum.CODE_10250002.getMsg();
        }
        this.setTopState = setTopState;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getInformationPhoto() {
        return informationPhoto;
    }

    public void setInformationPhoto(String informationPhoto) {
        this.informationPhoto = informationPhoto;
    }
}
