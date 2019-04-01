package cn.gokong.www.gokongmain.vo.user_collection;

import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:分页获取资讯收藏出参
 *
 * @author ikook
 * @CreateDate 2018/9/11 15:34
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryInformationCollectionOutput", description = "分页获取资讯收藏出参")
public class PageQueryInformationCollectionOutput {
    @ApiModelProperty(dataType = "String",name = "informationNo",value = "资讯编码")
    private String informationNo;
    @ApiModelProperty(dataType = "String",name = "informationName",value = "资讯名称")
    private String informationName;
    @ApiModelProperty(dataType = "String",name = "setTopState",value = "资讯置顶")
    private String setTopState;
    @ApiModelProperty(dataType = "String",name = "hotState",value = "资讯热点")
    private String hotState;
    @ApiModelProperty(dataType = "String",name = "userHead",value = "用户头像")
    private String userHead;
    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    private String userNo;
    @ApiModelProperty(dataType = "String",name = "informationPhoto",value = "资讯图片")
    private String informationPhoto;
    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户名称")
    private String nickName;



    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public String getHotState() {
        return hotState;
    }

    public void setHotState(String hotState) {
        if (hotState.equals(SysCodeEnum.CODE_10240001.getCode())){
            hotState = SysCodeEnum.CODE_10240001.getMsg();
        }else if (hotState.equals(SysCodeEnum.CODE_10240002.getCode())){
            hotState = SysCodeEnum.CODE_10240002.getMsg();
        }
        this.hotState = hotState;
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
