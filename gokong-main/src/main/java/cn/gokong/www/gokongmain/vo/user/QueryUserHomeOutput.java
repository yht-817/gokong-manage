package cn.gokong.www.gokongmain.vo.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:查询个人主页出参
 *
 * @author ikook
 * @CreateDate 2018/9/12 11:50
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "UserInfoOutput", description = "响应用户登录信息")
public class QueryUserHomeOutput {

    @ApiModelProperty(dataType = "String",name = "userNo",value = "用户编码")
    private String userNo;

    @ApiModelProperty(dataType = "String",name = "userHead",value = "用户头像")
    private String userHead;

    @ApiModelProperty(dataType = "String",name = "nickName",value = "用户昵称")
    private String nickName;

    @ApiModelProperty(dataType = "String",name = "userGrade",value = "用户等级")
    private String userGrade;

    @ApiModelProperty(dataType = "String",name = "userSign",value = "用户签名")
    private String userSign;

    @ApiModelProperty(dataType = "String",name = "fansNo",value = "粉丝数量")
    private Integer fansNo;

    @ApiModelProperty(dataType = "String",name = "total",value = "总访问量")
    private Integer total;

    @ApiModelProperty(dataType = "String",name = "today",value = "今日访问量")
    private Integer today;

    @ApiModelProperty(dataType = "String",name = "friend",value = "是否是好友")
    private boolean friend;

    @ApiModelProperty(dataType = "String",name = "homePhoto1",value = "图片")
    private String homePhoto1;

    @ApiModelProperty(dataType = "String",name = "homePhoto2",value = "图片")
    private String homePhoto2;

    @ApiModelProperty(dataType = "String",name = "homePhoto3",value = "图片")
    private String homePhoto3;

    @ApiModelProperty(dataType = "String",name = "homePhoto4",value = "图片")
    private String homePhoto4;

    @ApiModelProperty(dataType = "String",name = "homePhoto5",value = "图片")
    private String homePhoto5;

    @ApiModelProperty(dataType = "String",name = "homePhoto6",value = "图片")
    private String homePhoto6;

    @ApiModelProperty(dataType = "String",name = "homePhoto7",value = "图片")
    private String homePhoto7;

    @ApiModelProperty(dataType = "String",name = "homePhoto8",value = "图片")
    private String homePhoto8;

    @ApiModelProperty(dataType = "String",name = "homePhoto9",value = "图片")
    private String homePhoto9;

    @ApiModelProperty(dataType = "String",name = "coverNo",value = "封面标识")
    private String coverNo;

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

    public Integer getFansNo() {
        return fansNo;
    }

    public void setFansNo(Integer fansNo) {
        this.fansNo = fansNo;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getToday() {
        return today;
    }

    public void setToday(Integer today) {
        this.today = today;
    }

    public boolean isFriend() {
        return friend;
    }

    public void setFriend(boolean friend) {
        this.friend = friend;
    }

    public String getHomePhoto1() {
        return homePhoto1;
    }

    public void setHomePhoto1(String homePhoto1) {
        this.homePhoto1 = homePhoto1;
    }

    public String getHomePhoto2() {
        return homePhoto2;
    }

    public void setHomePhoto2(String homePhoto2) {
        this.homePhoto2 = homePhoto2;
    }

    public String getHomePhoto3() {
        return homePhoto3;
    }

    public void setHomePhoto3(String homePhoto3) {
        this.homePhoto3 = homePhoto3;
    }

    public String getHomePhoto4() {
        return homePhoto4;
    }

    public void setHomePhoto4(String homePhoto4) {
        this.homePhoto4 = homePhoto4;
    }

    public String getHomePhoto5() {
        return homePhoto5;
    }

    public void setHomePhoto5(String homePhoto5) {
        this.homePhoto5 = homePhoto5;
    }

    public String getHomePhoto6() {
        return homePhoto6;
    }

    public void setHomePhoto6(String homePhoto6) {
        this.homePhoto6 = homePhoto6;
    }

    public String getHomePhoto7() {
        return homePhoto7;
    }

    public void setHomePhoto7(String homePhoto7) {
        this.homePhoto7 = homePhoto7;
    }

    public String getHomePhoto8() {
        return homePhoto8;
    }

    public void setHomePhoto8(String homePhoto8) {
        this.homePhoto8 = homePhoto8;
    }

    public String getHomePhoto9() {
        return homePhoto9;
    }

    public void setHomePhoto9(String homePhoto9) {
        this.homePhoto9 = homePhoto9;
    }

    public String getCoverNo() {
        return coverNo;
    }

    public void setCoverNo(String coverNo) {
        this.coverNo = coverNo;
    }
}
