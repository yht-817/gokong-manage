package cn.gokong.www.gokongmain.vo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

/**
 * 说明:分页搜索查询入参
 *
 * @author ikook
 * @CreateDate 2018/9/12 17:34
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQuerySearchBase", description = "分页搜索查询入参")
public class PageQuerySearchBase extends PageQueryBase {

    @ApiModelProperty(dataType = "String",name = "keyWord",value = "关键字")
    @Length(max = 20,message = "关键字长度<=20")
    private String keyWord;

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }
}
