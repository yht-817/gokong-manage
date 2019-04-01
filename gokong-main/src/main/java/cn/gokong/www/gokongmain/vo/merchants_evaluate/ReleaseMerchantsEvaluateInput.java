package cn.gokong.www.gokongmain.vo.merchants_evaluate;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * 说明:发布商户评价入参
 *
 * @author ikook
 * @CreateDate 2018/9/14 10:37
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "ReleaseMerchantsEvaluateInput", description = "发布商户评价入参")
public class ReleaseMerchantsEvaluateInput extends UserNoBase {

    @ApiModelProperty(dataType = "String",name = "merchantsNo",value = "商户编码")
    @NotNull(message = "商户编码不能为空")
    @Length(max = 50,message = "商户编码长度<=50")
    private String merchantsNo;

    @ApiModelProperty(dataType = "String",name = "evaluateContent",value = "评价内容")
    @NotNull(message = "评价内容不能为空")
    @Length(max = 120,message = "评价内容长度<=120")
    private String evaluateContent;

    @ApiModelProperty(dataType = "String",name = "evaluateScore",value = "评分")
    @NotNull(message = "评分不能为空")
    @Range(min = 1,max = 5,message = "请选择1~5个评分")
    private int evaluateScore;

    public String getMerchantsNo() {
        return merchantsNo;
    }

    public void setMerchantsNo(String merchantsNo) {
        this.merchantsNo = merchantsNo;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent;
    }

    public int getEvaluateScore() {
        return evaluateScore;
    }

    public void setEvaluateScore(int evaluateScore) {
        this.evaluateScore = evaluateScore;
    }
}
