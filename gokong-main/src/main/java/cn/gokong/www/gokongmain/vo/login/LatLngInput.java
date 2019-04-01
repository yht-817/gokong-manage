package cn.gokong.www.gokongmain.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;

/**
 * 说明:经纬度入参
 *
 * @author ikook
 * @CreateDate 2018/9/14 15:48
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "LatLngInput", description = "经纬度入参")
public class LatLngInput {

    @ApiModelProperty(dataType = "String",name = "groupNo",value = "纬度")
    @NotNull(message = "纬度不能为空")
    private double lat;

    @ApiModelProperty(dataType = "String",name = "groupNo",value = "经度")
    @NotNull(message = "经度不能为空")
    private double lng;

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
