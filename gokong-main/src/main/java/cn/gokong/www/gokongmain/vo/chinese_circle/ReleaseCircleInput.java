package cn.gokong.www.gokongmain.vo.chinese_circle;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:发布华人圈入参
 *
 * @author ikook
 * @CreateDate 2018/9/14 14:00
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@ApiModel(value = "ReleaseCircleInput", description = "发布华人圈入参")
public class ReleaseCircleInput extends UserNoBase {

    @ApiModelProperty(dataType = "String", name = "instructions", value = "发布内容")
    @NotNull(message = "发布内容不能为空")
    @Length(max = 1000,message = "请精简您的描述，超过字数限制啦！")
    private String instructions;

//    @ApiModelProperty(dataType = "String", name = "circleImg", value = "华人圈图片")
//    @NotNull(message = "华人圈图片不能为空")
//    private MultipartFile circleImg;

    @ApiModelProperty(dataType = "String", name = "cityName", value = "城市名")
    @NotNull(message = "城市名不能为空")
    private String cityName;

    @ApiModelProperty(dataType = "String", name = "cityName", value = "纬度")
    @NotNull(message = "纬度不能为空")
    private String lat;

    @ApiModelProperty(dataType = "String", name = "cityName", value = "经度")
    @NotNull(message = "经度不能为空")
    private String lng;

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

//    public MultipartFile getCircleImg() {
//        return circleImg;
//    }
//
//    public void setCircleImg(MultipartFile circleImg) {
//        this.circleImg = circleImg;
//    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
