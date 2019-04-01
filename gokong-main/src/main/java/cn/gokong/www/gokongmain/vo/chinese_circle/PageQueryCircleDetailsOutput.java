package cn.gokong.www.gokongmain.vo.chinese_circle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 说明:分页查询华人圈详情出参
 *
 * @author ikook
 * @CreateDate 2018/9/18 11:12
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryCircleDetailsOutput", description = "分页查询华人圈详情出参")
public class PageQueryCircleDetailsOutput {

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    private String userNo;
    @ApiModelProperty(dataType = "String",name = "userHead",value = "用户头像")
    private String userHead;
    @ApiModelProperty(dataType = "String",name = "nickName",value = "用户昵称")
    private String nickName;
    @ApiModelProperty(dataType = "String",name = "focus",value = "是否关注")
    private boolean focus;

    @ApiModelProperty(dataType = "String", name = "circleDetails", value = "资讯详情")
    private List<CircleDetails> circleDetails;

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

    public boolean isFocus() {
        return focus;
    }

    public void setFocus(boolean focus) {
        this.focus = focus;
    }

    public List<CircleDetails> getCircleDetails() {
        return circleDetails;
    }

    public void setCircleDetails(List<CircleDetails> circleDetails) {
        this.circleDetails = circleDetails;
    }
}
