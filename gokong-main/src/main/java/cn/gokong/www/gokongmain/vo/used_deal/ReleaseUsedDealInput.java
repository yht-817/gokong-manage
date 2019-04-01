package cn.gokong.www.gokongmain.vo.used_deal;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 说明:发布二手交易
 *
 * @author ikook
 * @CreateDate 2018/10/1 15:32
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "ReleaseUsedDealInput", description = "发布二手交易")
public class ReleaseUsedDealInput extends UserNoBase {

    @ApiModelProperty(dataType = "String",name = "usedTitle",value = "标题")
    @NotNull(message = "标题不能为空")
    @Length(max = 50,message = "标题长度<=50")
    private String usedTitle;

    @ApiModelProperty(dataType = "String",name = "usedContent",value = "描述")
    @NotNull(message = "描述不能为空")
    @Length(max = 100,message = "描述长度<=100")
    private String usedContent;

    @ApiModelProperty(dataType = "String",name = "usedImgs",value = "交易图片")
    @NotNull(message = "交易图片不能为空")
    @Length(max = 15,message = "交易图片长度<=15")
    private MultipartFile[] usedImgs;

    @ApiModelProperty(dataType = "String",name = "typeNo",value = "类型编码")
    @NotNull(message = "类型编码不能为空")
    @Length(max = 50,message = "类型编码长度<=50")
    private String typeNo;

    @ApiModelProperty(dataType = "String",name = "price",value = "价格")
    private BigDecimal price;

    @ApiModelProperty(dataType = "String",name = "cityName",value = "发布城市名称")
    @NotNull(message = "发布城市名称不能为空")
    private String cityName;

    public String getUsedTitle() {
        return usedTitle;
    }

    public void setUsedTitle(String usedTitle) {
        this.usedTitle = usedTitle;
    }

    public String getUsedContent() {
        return usedContent;
    }

    public void setUsedContent(String usedContent) {
        this.usedContent = usedContent;
    }

    public MultipartFile[] getUsedImgs() {
        return usedImgs;
    }

    public void setUsedImgs(MultipartFile[] usedImgs) {
        this.usedImgs = usedImgs;
    }

    public String getTypeNo() {
        return typeNo;
    }

    public void setTypeNo(String typeNo) {
        this.typeNo = typeNo;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
}
