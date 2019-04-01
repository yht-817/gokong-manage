package cn.gokong.www.gokongmain.vo.user;

import cn.gokong.www.base.data.DataMemory;
import cn.gokong.www.base.jwt.JwtUtil;
import cn.gokong.www.gokongmain.enums.SysCodeEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:响应用户登录信息
 *
 * @author ideac
 * @CreateDate 2018/9/9 20:48
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "UserInfoOutput", description = "响应用户登录信息")
public class UserInfoOutput {

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "userHead",value = "头像")
    private String userHead;

    @ApiModelProperty(dataType = "String",name = "userName",value = "用户名")
    private String userName;

    @ApiModelProperty(dataType = "String",name = "nickName",value = "用户昵称")
    private String nickName;

    @ApiModelProperty(dataType = "String",name = "sexNo",value = "用户性别")
    private String sexNo;

    @ApiModelProperty(dataType = "String",name = "phoneNo",value = "电话号码")
    private String phoneNo;

    @ApiModelProperty(dataType = "String",name = "mailboxNo",value = "邮箱")
    private String mailboxNo;

    @ApiModelProperty(dataType = "String",name = "userType",value = "用户类型")
    private String userType;

    @ApiModelProperty(dataType = "String",name = "lastLoginDate",value = "最后登录时间")
    private String lastLoginDate;

    @ApiModelProperty(dataType = "String",name = "userGrade",value = "用户等级")
    private String userGrade;

    @ApiModelProperty(dataType = "String",name = "userSign",value = "签名")
    private String userSign;

    @ApiModelProperty(dataType = "String",name = "userLocal",value = "常住地")
    private String userLocal;

    @ApiModelProperty(dataType = "String",name = "token",value = "token")
    private String token;

    @ApiModelProperty(dataType = "String",name = "cattlePeople",value = "是否是牛人")
    private boolean cattlePeople;

    public boolean isCattlePeople() {
        return cattlePeople;
    }

    public void setCattlePeople(boolean cattlePeople) {
        this.cattlePeople = cattlePeople;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        // 设置登录失效时间 token设置有效期为30天
        String token = JwtUtil.buildJWT(userNo, 60 * 60 * 24 * 30);
        DataMemory.tokenMap.put(userNo,token);

        this.userNo = userNo;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSexNo() {
        return sexNo;
    }

    public void setSexNo(String sexNo) {

        this.sexNo = sexNo.equals(SysCodeEnum.CODE_10010001.getCode())?SysCodeEnum.CODE_10010001.getMsg():SysCodeEnum.CODE_10010002.getMsg();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getMailboxNo() {
        return mailboxNo;
    }

    public void setMailboxNo(String mailboxNo) {
        this.mailboxNo = mailboxNo;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        /*if (userType.equals(SysCodeEnum.CODE_10020001.getCode())){
            userType = SysCodeEnum.CODE_10020001.getMsg();
        }else if (userType.equals(SysCodeEnum.CODE_10020002.getCode())){
            userType = SysCodeEnum.CODE_10020002.getMsg();
        }else if (userType.equals(SysCodeEnum.CODE_10020003.getCode())){
            userType = SysCodeEnum.CODE_10020003.getMsg();
        }else if (userType.equals(SysCodeEnum.CODE_10020004.getCode())){
            userType = SysCodeEnum.CODE_10020004.getMsg();
        }*/
        this.userType = userType;
    }

    public String getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(String lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
    }

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public String getUserLocal() {
        return userLocal;
    }

    public void setUserLocal(String userLocal) {
        this.userLocal = userLocal;
    }

    @Override
    public String toString() {
        return "UserInfoOutput{" +
                "userNo='" + userNo + '\'' +
                ", userHead='" + userHead + '\'' +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sexNo='" + sexNo + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", mailboxNo='" + mailboxNo + '\'' +
                ", userType='" + userType + '\'' +
                ", lastLoginDate='" + lastLoginDate + '\'' +
                ", userGrade='" + userGrade + '\'' +
                ", userSign='" + userSign + '\'' +
                ", userLocal='" + userLocal + '\'' +
                '}';
    }
}
