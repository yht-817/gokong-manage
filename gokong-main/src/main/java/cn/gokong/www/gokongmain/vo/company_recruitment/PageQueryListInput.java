package cn.gokong.www.gokongmain.vo.company_recruitment;

import cn.gokong.www.gokongmain.vo.base.PageQuerySearchBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:分页查询招聘求职
 *
 * @author ikook
 * @CreateDate 2018/10/2 11:52
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryListInput", description = "分页查询招聘求职")
public class PageQueryListInput extends PageQuerySearchBase {

    @ApiModelProperty(dataType = "String",name = "cityName",value = "城市名称")
    @NotNull(message = "城市名称不能为空")
    @Length(max = 50,message = "城市名称长度<=50")
    private String cityName;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
