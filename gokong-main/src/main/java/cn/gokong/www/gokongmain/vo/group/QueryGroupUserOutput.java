package cn.gokong.www.gokongmain.vo.group;

import cn.gokong.www.gokongmain.vo.user.QueryUserOutput;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:群用户出参
 *
 * @author Mick
 * CreateDate 2018/7/11/011 22:16
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@ApiModel(value = "QueryGroupUserOutput",description = "群用户出参")
public class QueryGroupUserOutput extends QueryUserOutput {

    @ApiModelProperty(dataType = "boolean",name = "isokOwner",value = "是否是群主")
    private boolean isokOwner = false;

    public boolean isIsokOwner() {
        return isokOwner;
    }

    public void setIsokOwner(boolean isokOwner) {
        this.isokOwner = isokOwner;
    }
}
