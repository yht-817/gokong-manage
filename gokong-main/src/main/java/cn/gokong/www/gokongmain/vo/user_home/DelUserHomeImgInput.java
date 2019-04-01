package cn.gokong.www.gokongmain.vo.user_home;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 说明:删除个人主页图片入参
 *
 * @author ikook
 * @CreateDate 2018/9/12 14:42
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "DelUserHomeImgInput", description = "删除个人主页图片入参")
public class DelUserHomeImgInput extends UserNoBase {

    @ApiModelProperty(dataType = "String",name = "imgNo",value = "图片index")
    @Pattern(regexp = "^[0-9]{1}$",
            message = "图片index只能一位数")
    @NotNull(message = "图片index不能为空")
    @Length(message = "图片index长度<=10",max = 1)
    private String imgNo;

    public String getImgNo() {
        return imgNo;
    }

    public void setImgNo(String imgNo) {
        this.imgNo = imgNo;
    }
}
