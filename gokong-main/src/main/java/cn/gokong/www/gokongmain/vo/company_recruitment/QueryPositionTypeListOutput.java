package cn.gokong.www.gokongmain.vo.company_recruitment;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:职位类型列表
 *
 * @author ikook
 * @CreateDate 2018/10/2 15:23
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "QueryPositionTypeListOutput", description = "职位类型列表")
public class QueryPositionTypeListOutput {
    @ApiModelProperty(dataType = "String",name = "typeNo",value = "职位编码")
    private String typeNo;

    @ApiModelProperty(dataType = "String",name = "typeNo",value = "职位名称")
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
