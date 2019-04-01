package cn.gokong.www.gokongmain.vo.login;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 获取国际电话号码区号列表
 * </p>
 *
 * @author iKook
 * @since 2018-09-10
 */
@ApiModel(value = "CountryMobilePrefixOutput", description = "获取国际电话号码区号列表")
public class CountryMobilePrefixOutput{
    /**
     * 国家名称
     */
    @ApiModelProperty(dataType = "String",name = "country",value = "国家名称")
    private String zhName;

    /**
     * 区号
     */
    @ApiModelProperty(dataType = "String",name = "mobilePrefix",value = "区号")
    private int code;

    /**
     * 优先级排行
     */
    @ApiModelProperty(dataType = "String",name = "priority",value = "优先级排行")
    private Integer priority;

    /**
     * 首字母
     */
    @ApiModelProperty(dataType = "String",name = "initial",value = "首字母")
    private String firstLetter;

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }
}
