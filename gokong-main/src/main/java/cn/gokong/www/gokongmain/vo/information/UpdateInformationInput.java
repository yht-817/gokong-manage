package cn.gokong.www.gokongmain.vo.information;

import cn.gokong.www.gokongmain.vo.base.InformationNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 说明:修改资讯入参
 *
 * @author ikook
 * @CreateDate 2018/9/12 19:47
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "UpdateInformationInput", description = "修改资讯入参")
public class UpdateInformationInput extends InformationNoBase {

    @ApiModelProperty(dataType = "String",name = "informationMode",value = "资讯模式")
    @NotNull(message = "资讯模式不能为空")
    @Pattern(regexp = "^(10230001)|(10230002)$",
            message = "资讯模式格式错误")
    @Length(max = 50,message = "资讯模式长度<=50")
    private String informationMode;

    @ApiModelProperty(dataType = "String",name = "informationName",value = "资讯名称")
    @NotNull(message = "资讯名称不能为空")
    @Length(max = 50,message = "资讯名称长度<=50")
    private String informationName;

    @ApiModelProperty(dataType = "String",name = "informationPhoto",value = "资讯图片")
    @Length(max = 300,message = "资讯图片长度<=300")
    private String informationPhoto;

    @ApiModelProperty(dataType = "String",name = "photoSizeType",value = "布局")
    @NotNull(message = "布局不能为空")
    @Length(max = 50,message = "布局长度<=50")
    private String photoSizeType;

    @ApiModelProperty(dataType = "String",name = "informationContent",value = "资讯内容")
    @NotNull(message = "资讯内容不能为空")
    private String informationContent;

    public String getInformationMode() {
        return informationMode;
    }

    public void setInformationMode(String informationMode) {
        this.informationMode = informationMode;
    }

    public String getInformationName() {
        return informationName;
    }

    public void setInformationName(String informationName) {
        this.informationName = informationName;
    }

    public String getInformationPhoto() {
        return informationPhoto;
    }

    public void setInformationPhoto(String informationPhoto) {
        this.informationPhoto = informationPhoto;
    }

    public String getPhotoSizeType() {
        return photoSizeType;
    }

    public void setPhotoSizeType(String photoSizeType) {
        this.photoSizeType = photoSizeType;
    }

    public String getInformationContent() {
        return informationContent;
    }

    public void setInformationContent(String informationContent) {
        this.informationContent = informationContent;
    }
}
