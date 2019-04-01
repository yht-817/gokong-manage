package cn.gokong.www.gokongmain.vo.city;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 二级服务范围的实体类是一级服务的子类
 */
@ApiModel(value = "ServiceScopeOne", description = "城市服务范围二级目录")
public class ServiceScopeTwo {
    @ApiModelProperty(dataType = "String", name = "id", value = "插入数据时的ID")
    private String id;
    @ApiModelProperty(dataType = "String", name = "scopeTwoNo", value = "二级范围的编码")
    private String scopeTwoNo;
    @ApiModelProperty(dataType = "String", name = "scopeTwoName", value = "二级范围的城市名")
    private String scopeTwoName;
    @ApiModelProperty(dataType = "double", name = "lng", value = "经度")
    private double lng;
    @ApiModelProperty(dataType = "double", name = "lat", value = "纬度")
    private double lat;
    @ApiModelProperty(dataType = "String", name = "currency", value = "金钱符号")
    private String currency;

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getScopeTwoNo() {
        return scopeTwoNo;
    }

    public void setScopeTwoNo(String scopeTwoNo) {
        this.scopeTwoNo = scopeTwoNo == null ? null : scopeTwoNo.trim();
    }

    public String getScopeTwoName() {
        return scopeTwoName;
    }

    public void setScopeTwoName(String scopeTwoName) {
        this.scopeTwoName = scopeTwoName == null ? null : scopeTwoName.trim();
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }
}