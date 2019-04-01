package cn.gokong.www.gokongmain.vo.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:公用用户编码数据
 *
 * @author ideac
 * @CreateDate 2018/9/10 22:02
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "UserNoBase", description = "公用用户编码数据")
public class InformationNoBase {

    @ApiModelProperty(dataType = "String",name = "informationNo",value = "资讯编码")
    @NotNull(message = "资讯编码不能为空")
    @Length(max = 50,message = "资讯编码长度<=50")
    private String informationNo;

    public String getInformationNo() {
        return informationNo;
    }

    public void setInformationNo(String informationNo) {
        this.informationNo = informationNo;
    }
}
