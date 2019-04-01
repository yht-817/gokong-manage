package cn.gokong.www.gokongmain.vo.used_deal;

import cn.gokong.www.gokongmain.vo.base.PageQuerySearchBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:分页查询二手交易列表
 *
 * @author ikook
 * @CreateDate 2018/10/1 16:27
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@ApiModel(value = "PageQueryListInput", description = "分页查询二手交易列表")
public class PageQueryListInput extends PageQuerySearchBase {
    @ApiModelProperty(dataType = "String",name = "typeNo",value = "类型编码")
    @Length(max = 50,message = "类型编码长度<=50")
    private String typeNo;

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

    public String getTypeNo() {
        return typeNo;
    }

    public void setTypeNo(String typeNo) {
        this.typeNo = typeNo;
    }
}
