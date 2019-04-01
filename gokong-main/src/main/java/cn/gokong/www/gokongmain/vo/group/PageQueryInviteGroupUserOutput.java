package cn.gokong.www.gokongmain.vo.group;

import cn.gokong.www.gokongmain.vo.user.QueryUserOutput;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:分页查询邀请群成员列表出参
 *
 * @author Mick
 * CreateDate 2018/7/11/011 22:16
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@ApiModel(value = "PageQueryInviteGroupUserOutput",description = "分页查询邀请群成员列表出参")
public class PageQueryInviteGroupUserOutput extends QueryUserOutput {

    @ApiModelProperty(dataType = "boolean",name = "isokInvite",value = "是否已经邀请(默认可以邀请)")
    private boolean isokInvite = false;

    public boolean isIsokInvite() {
        return isokInvite;
    }

    public void setIsokInvite(boolean isokInvite) {
        this.isokInvite = isokInvite;
    }
}
