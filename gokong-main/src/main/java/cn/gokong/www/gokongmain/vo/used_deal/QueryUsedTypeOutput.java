package cn.gokong.www.gokongmain.vo.used_deal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:查询二手交易分类
 *
 * @author ikook
 * @CreateDate 2018/10/1 15:27
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "QueryUsedTypeOutput", description = "查询二手交易分类")
public class QueryUsedTypeOutput {

    @ApiModelProperty(dataType = "String",name = "typeNo",value = "类型编码")
    private String typeNo;

    @ApiModelProperty(dataType = "String",name = "typeName",value = "类型名称")
    private String typeName;

    public String getTypeNo() {
        return typeNo;
    }

    public void setTypeNo(String typeNo) {
        this.typeNo = typeNo;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
