package cn.gokong.www.gokongmain.vo.friend;

import cn.gokong.www.gokongmain.vo.base.UserNoBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:申请添加好友入参
 *
 * @author ikook
 * @CreateDate 2018/9/13 23:15
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "ApplyFriendInput", description = "申请添加好友入参")
public class ApplyFriendInput extends UserNoBase {
    @ApiModelProperty(dataType = "String",name = "friendUserNo",value = "好友编码")
    @NotNull(message = "好友编码不能为空")
    @Length(max = 50,message = "好友编码长度<=50")
    private String friendUserNo;

    @ApiModelProperty(dataType = "String",name = "verifyInfo",value = "验证信息")
    @Length(max = 50,message = "验证信息长度<=50")
    private String verifyInfo;

    public String getFriendUserNo() {
        return friendUserNo;
    }

    public void setFriendUserNo(String friendUserNo) {
        this.friendUserNo = friendUserNo;
    }

    public String getVerifyInfo() {
        return verifyInfo;
    }

    public void setVerifyInfo(String verifyInfo) {
        this.verifyInfo = verifyInfo;
    }
}
