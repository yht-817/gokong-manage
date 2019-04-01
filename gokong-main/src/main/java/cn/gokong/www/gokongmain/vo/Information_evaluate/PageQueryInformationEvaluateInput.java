package cn.gokong.www.gokongmain.vo.Information_evaluate;

import cn.gokong.www.gokongmain.vo.base.PageQueryBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:分页查询资讯评论入参
 *
 * @author ikook
 * @CreateDate 2018/9/15 18:17
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryInformationEvaluateInput", description = "分页查询资讯评论入参")
public class PageQueryInformationEvaluateInput extends PageQueryBase {

    @ApiModelProperty(dataType = "String",name = "informationNo",value = "资讯编码")
    @NotNull(message = "资讯编码不能为空")
    @Length(max = 50,message = "资讯编码长度<=50")
    private String informationNo;

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    @NotNull(message = "用户编码不能为空")
    @Length(max = 50,message = "用户编码长度<=50")
    private String userNo;

    public String getInformationNo() {
        return informationNo;
    }

    public void setInformationNo(String informationNo) {
        this.informationNo = informationNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }
}
