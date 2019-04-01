package cn.gokong.www.gokongmain.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:用户出参
 *
 * @author Mick
 * CreateDate 2018/7/11/011 22:16
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
@ApiModel(value = "QueryUserOutput",description = "用户出参")
public class QueryUserOutput {

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "userHead",value = "用户头像")
    private String userHead;

    @ApiModelProperty(dataType = "String",name = "nickName",value = "用户昵称")
    private String nickName;

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
