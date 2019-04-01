package cn.gokong.www.gokongmain.vo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:城市名称
 *
 * @author ikook
 * @CreateDate 2018/10/11 16:34
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "CityNameBase", description = "城市名称")
public class CityNameBase {

    @ApiModelProperty(dataType = "String",name = "cityName",value = "城市名称")
    @NotNull(message = "城市名称不能为空")
    @Length(max = 20,message = "城市名称长度<=20")
    private String cityName;

    @NotNull
    public String getCityName() {
        return cityName;
    }

    public void setCityName(@NotNull String cityName) {
        this.cityName = cityName;
    }
}
