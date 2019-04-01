package cn.gokong.www.gokongmain.vo.chinese_circle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:华人圈编码入参
 *
 * @author ikook
 * @CreateDate 2018/9/28 17:46
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "CircleNoBase", description = "华人圈编码入参")
public class CircleNoBase {

    @ApiModelProperty(dataType = "String", name = "circleNo", value = "华人圈编码")
    @NotNull(message = "华人圈编码不能为空")
    @Length(max = 50, message = "华人圈编码长度<=50")
    private String circleNo;

    public String getCircleNo() {
        return circleNo;
    }

    public void setCircleNo(String circleNo) {
        this.circleNo = circleNo;
    }
}
