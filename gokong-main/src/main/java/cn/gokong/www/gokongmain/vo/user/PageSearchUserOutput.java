package cn.gokong.www.gokongmain.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:分页查询用户列表出参
 *
 * @author ikook
 * @CreateDate 2018/9/24 15:55
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageSearchUserOutput", description = "分页查询用户列表出参")
public class PageSearchUserOutput extends QueryUserOutput {

    @ApiModelProperty(dataType = "String",name = "friend",value = "是否是好友")
    private boolean friend;

    public boolean isFriend() {
        return friend;
    }

    public void setFriend(boolean friend) {
        this.friend = friend;
    }
}
