package cn.gokong.www.gokongmain.vo.friend;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:分页查询好友列表
 *
 * @author Mick
 * CreateDate 2018/7/11/011 22:16
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@ApiModel(value = "QueryUserOutput",description = "分页查询好友列表")
public class PageQueryFriendOutput {

    @ApiModelProperty(dataType = "String",name = "inviteNo",value = "好友关系编码")
    private String inviteNo;

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "userHead",value = "用户头像")
    private String userHead;

    @ApiModelProperty(dataType = "String",name = "nickName",value = "用户昵称")
    private String nickName;

    public String getInviteNo() {
        return inviteNo;
    }

    public void setInviteNo(String inviteNo) {
        this.inviteNo = inviteNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
}
