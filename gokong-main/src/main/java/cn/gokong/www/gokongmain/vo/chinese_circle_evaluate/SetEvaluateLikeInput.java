package cn.gokong.www.gokongmain.vo.chinese_circle_evaluate;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:设置评论点赞入参
 *
 * @author ikook
 * @CreateDate 2018/9/15 19:36
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "SetEvaluateLikeInput", description = "设置评论点赞入参")
public class SetEvaluateLikeInput extends UserNoBase {

    @ApiModelProperty(dataType = "String",name = "evaluateNo",value = "评论编码")
    @NotNull(message = "评论编码不能为空")
    @Length(max = 50,message = "评论编码长度<=50")
    private String evaluateNo;

    @ApiModelProperty(dataType = "String",name = "liked",value = "ture:点赞 false:取消点赞")
    private boolean liked;

    public String getEvaluateNo() {
        return evaluateNo;
    }

    public void setEvaluateNo(String evaluateNo) {
        this.evaluateNo = evaluateNo;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}
