package cn.gokong.www.gokongmain.vo.merchants;

import cn.gokong.www.gokongmain.vo.base.PageQuerySearchBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:分页查询商户数据入参数据
 *
 * @author ikook
 * @CreateDate 2018/9/11 11:18
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryMerchantsInput", description = "分页查询商户数据入参数据")
public class PageQueryMerchantsInput extends PageQuerySearchBase {

    @ApiModelProperty(dataType = "int",name = "cityName",value = "发布城市")
    @NotNull(message = "发布城市不能为空")
    @Length(max = 10,message = "发布城市长度<=10")
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
