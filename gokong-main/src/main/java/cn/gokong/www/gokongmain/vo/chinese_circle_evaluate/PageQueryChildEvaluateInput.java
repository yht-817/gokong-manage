package cn.gokong.www.gokongmain.vo.chinese_circle_evaluate;

import cn.gokong.www.gokongmain.vo.base.PageQueryBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:分页查询子评论入参
 *
 * @author ikook
 * @CreateDate 2018/9/15 18:57
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryChildEvaluateInput", description = "分页查询子评论入参")
public class PageQueryChildEvaluateInput extends PageQueryBase {
    @ApiModelProperty(dataType = "String",name = "evaluateNo",value = "评论编码")
    @NotNull(message = "评论编码不能为空")
    @Length(max = 50,message = "评论编码长度<=50")
    private String evaluateNo;

    public String getEvaluateNo() {
        return evaluateNo;
    }

    public void setEvaluateNo(String evaluateNo) {
        this.evaluateNo = evaluateNo;
    }
}
