package cn.gokong.www.gokongmain.vo.chinese_circle;

import cn.gokong.www.gokongmain.vo.base.PageQueryBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:分页查询华人圈详情入参
 *
 * @author ikook
 * @CreateDate 2018/9/18 11:08
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryCircleDetailsInput", description = "分页查询华人圈详情入参")
public class PageQueryCircleDetailsInput extends PageQueryBase {

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    @NotNull(message = "用户编码不能为空")
    @Length(max = 50,message = "用户编码长度<=50")
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "circleNo",value = "华人圈编码")
    @NotNull(message = "华人圈编码不能为空")
    @Length(max = 50,message = "华人圈编码长度<=50")
    private String circleNo;

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
