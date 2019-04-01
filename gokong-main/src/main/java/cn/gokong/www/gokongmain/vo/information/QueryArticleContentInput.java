package cn.gokong.www.gokongmain.vo.information;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:获取URL链接内容入参
 *
 * @author ikook
 * @CreateDate 2018/9/12 19:41
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "QueryArticleContentInput", description = "获取URL链接内容入参")
public class QueryArticleContentInput {

    @ApiModelProperty(dataType = "String",name = "userNo",value = "链接地址")
    @NotNull(message = "链接地址不能为空")
    @Length(max = 300,message = "链接地址长度<=300")
    private String url;
    @ApiModelProperty(dataType = "String",name = "userNo",value = "链接类型")
    @NotNull(message = "链接类型不能为空")
    @Length(max = 50,message = "链接类型长度<=50")
    private String type;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
