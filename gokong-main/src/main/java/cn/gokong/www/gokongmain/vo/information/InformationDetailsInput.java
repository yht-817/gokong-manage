package cn.gokong.www.gokongmain.vo.information;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:查询资讯详情入参
 *
 * @author ikook
 * @CreateDate 2018/9/12 18:13
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@ApiModel(value = "InformationDetailsInput", description = "查询资讯详情入参")
public class InformationDetailsInput extends UserNoBase {

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
