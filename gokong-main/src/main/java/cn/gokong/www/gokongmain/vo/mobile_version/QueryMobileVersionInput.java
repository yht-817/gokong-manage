package cn.gokong.www.gokongmain.vo.mobile_version;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 说明:获取APP版本信息入参
 *
 * @author ikook
 * @CreateDate 2018/9/26 14:19
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "QueryMobileVersionInput", description = "获取APP版本信息入参")
public class QueryMobileVersionInput {

    @ApiModelProperty(dataType = "String",name = "appName",value = "APP名称")
    @NotNull(message = "APP名称不能为空")
    @Length(max = 10,message = "APP名称长度<=10")
    private String appName;

    @ApiModelProperty(dataType = "String",name = "appType",value = "APP类型")
    @Range(max = 1,min = 0,message = "APP类型最大1 最小0")
    private int appType;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getAppType() {
        return appType;
    }

    public void setAppType(int appType) {
        this.appType = appType;
    }
}
