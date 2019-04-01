package cn.gokong.www.gokongmain.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 说明:响应用户登录信息
 *
 * @author ideac
 * @CreateDate 2018/9/9 20:48
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "UserNoLoginOutput", description = "响应用户登录信息")
public class UserNoLoginOutput {

    @ApiModelProperty(dataType = "String", name = "userNo", value = "用户编码")
    private String userNo;

    @ApiModelProperty(dataType = "String", name = "userHead", value = "头像")
    private String userHead;

    @ApiModelProperty(dataType = "String", name = "nickName", value = "用户昵称")
    private String nickName;

    @ApiModelProperty(dataType = "String", name = "sexNo", value = "性别")
    private String sexNo;

    @ApiModelProperty(dataType = "String", name = "cattlePeople", value = "是否是牛人")
    private boolean cattlePeople;

    @ApiModelProperty(dataType = "String", name = "accountAmount", value = "蟠桃余额")
    private BigDecimal accountAmount;

    private boolean booleanP;

    public String getSexNo() {
        return sexNo;
    }

    public void setSexNo(String sexNo) {
        this.sexNo = sexNo;
    }

    public boolean isCattlePeople() {
        return cattlePeople;
    }

    public void setCattlePeople(boolean cattlePeople) {
        this.cattlePeople = cattlePeople;
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

    public BigDecimal getAccountAmount() {
        return accountAmount;
    }

    public void setAccountAmount(BigDecimal accountAmount) {
        this.accountAmount = accountAmount;
    }

    public boolean isBooleanP() {
        return booleanP;
    }

    public void setBooleanP(boolean booleanP) {
        this.booleanP = booleanP;
    }
}
