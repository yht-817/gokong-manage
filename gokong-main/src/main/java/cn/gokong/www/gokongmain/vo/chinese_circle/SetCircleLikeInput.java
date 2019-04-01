package cn.gokong.www.gokongmain.vo.chinese_circle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:设置华人圈点赞入参
 *
 * @author ikook
 * @CreateDate 2018/9/18 11:49
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "SetCircleLikeInput", description = "设置华人圈点赞入参")
public class SetCircleLikeInput {
    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    @NotNull(message = "用户编码不能为空")
    @Length(max = 50,message = "用户编码长度<=50")
    private String userNo;

    @ApiModelProperty(dataType = "String", name = "circleNo", value = "华人圈编码")
    @NotNull(message = "华人圈编码不能为空")
    @Length(max = 50, message = "华人圈编码长度<=50")
    private String circleNo;

    @ApiModelProperty(dataType = "boolean", name = "like", value = "点赞：true 取消：false")
    private boolean like;

    public boolean isLike() {
        return like;
    }

    public void setLike(boolean like) {
        this.like = like;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getCircleNo() {
        return circleNo;
    }

    public void setCircleNo(String circleNo) {
        this.circleNo = circleNo;
    }
}
