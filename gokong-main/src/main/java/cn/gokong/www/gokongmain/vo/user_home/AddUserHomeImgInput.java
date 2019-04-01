package cn.gokong.www.gokongmain.vo.user_home;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 说明:添加个人主页图片入参
 *
 * @author ikook
 * @CreateDate 2018/9/12 13:52
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "AddUserHomeImgInput", description = "添加个人主页图片入参")
public class AddUserHomeImgInput extends UserNoBase {

    @ApiModelProperty(dataType = "int",name = "imgNo",value = "图片index")
    @Pattern(regexp = "^[0-9]{1}$",
            message = "图片index只能一位数")
    @NotNull(message = "图片index不能为空")
    @Length(message = "图片index长度<=1",max = 1)
    private String imgNo;

    @ApiModelProperty(dataType = "String",name = "homePhoto",value = "图片地址")
    @NotNull(message = "图片地址不能为空")
    @Length(message = "图片地址长度<=300",max = 300)
    private String homePhoto;

    public String getImgNo() {
        return imgNo;
    }

    public void setImgNo(String imgNo) {
        this.imgNo = imgNo;
    }

    public String getHomePhoto() {
        return homePhoto;
    }

    public void setHomePhoto(String homePhoto) {
        this.homePhoto = homePhoto;
    }
}
