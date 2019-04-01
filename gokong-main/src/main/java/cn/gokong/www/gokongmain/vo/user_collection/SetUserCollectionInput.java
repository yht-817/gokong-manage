package cn.gokong.www.gokongmain.vo.user_collection;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:设置用户收藏入参
 *
 * @author ikook
 * @CreateDate 2018/9/11 15:13
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "SetUserCollectionInput", description = "设置用户收藏入参")
public class SetUserCollectionInput extends UserNoBase {

    @ApiModelProperty(dataType = "String",name = "contentNo",value = "内容编码")
    @NotNull(message = "内容编码不能为空")
    @Length(max = 100,message = "内容编码长度<=50")
    private String contentNo;

    @ApiModelProperty(dataType = "String",name = "collectionType",value = "收藏类型")
    @NotNull(message = "收藏类型不能为空")
    @Length(max = 50,message = "收藏类型长度<=50")
    private String collectionType;

    @ApiModelProperty(dataType = "boolean",name = "collection",value = "是否收藏标识")
    private boolean collection;

    public String getContentNo() {
        return contentNo;
    }

    public void setContentNo(String contentNo) {
        this.contentNo = contentNo;
    }

    public String getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }

    public boolean isCollection() {
        return collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }
}
