package cn.gokong.www.gokongmain.vo.used_deal;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:查询二手交易详情
 *
 * @author ikook
 * @CreateDate 2018/10/1 16:43
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "QueryDetailsInput", description = "查询二手交易详情")
public class QueryDetailsInput extends UserNoBase {

    @ApiModelProperty(dataType = "String", name = "usedNo", value = "二手交易编码")
    @NotNull(message = "二手交易编码不能为空")
    @Length(max = 50, message = "二手交易编码长度<=50")
    private String usedNo;

    public String getUsedNo() {
        return usedNo;
    }

    public void setUsedNo(String usedNo) {
        this.usedNo = usedNo;
    }
}
