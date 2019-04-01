package cn.gokong.www.gokongmain.vo.group;

import cn.gokong.www.gokongmain.vo.base.PageQuerySearchUserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
 * 说明:分页查询更多热门群
 *
 * @author ikook
 * @CreateDate 2018/9/27 16:08
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageSearchGroupInput", description = "分页查询更多热门群")
public class PageSearchGroupInput extends PageQuerySearchUserNoBase {

    @ApiModelProperty(dataType = "String",name = "cityName",value = "城市名称")
    @Length(max = 50,message = "城市名称长度<=50")
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
