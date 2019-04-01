package cn.gokong.www.gokongmain.domain;


import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户基础信息表
 * </p>
 *
 * @author iKook
 * @since 2018-09-08
 */
@TableName("user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private String id;

    /**
     * 用户编码
     */
    private String userNo;

    /**
     * 头像
     */
    private String userHead;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 用户性别
     */
    private String sexNo;

    /**
     * 手机号
     */
    private String phoneNo;

    /**
     * 邮箱
     */
    private String mailboxNo;

    /**
     * 登录口令
     */
    private String password;

    /**
     * 注册日期
     */
    private Date regionDate;

    /**
     * 用户类型
     */
    private String userType;

    /**
     * 最后登录日期
     */
    private Date lastLoginDate;

    /**
     * 用户状态
     */
    private String userState;

    /**
     * 用户等级
     */
    private String userGrade;

    /**
     * 用户等级变更日期
     */
    private Date userGradeChangeDate;

    /**
     * 用户签名
     */
    private String userSign;

    /**
     * 常住地
     */
    private String userLocal;

    /**
     * 电话代码
     */
    private String phoneCode;

    public String getPhoneCode() {
        return phoneCode;
    }

    public void setPhoneCode(String phoneCode) {
        this.phoneCode = phoneCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        this.sexNo = sexNo;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegionDate() {
        return regionDate;
    }

    public void setRegionDate(Date regionDate) {
        this.regionDate = regionDate;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserGrade() {
        return userGrade;
    }

    public void setUserGrade(String userGrade) {
        this.userGrade = userGrade;
    }

    public Date getUserGradeChangeDate() {
        return userGradeChangeDate;
    }

    public void setUserGradeChangeDate(Date userGradeChangeDate) {
        this.userGradeChangeDate = userGradeChangeDate;
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
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", userNo='" + userNo + '\'' +
                ", userHead='" + userHead + '\'' +
                ", userName='" + userName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sexNo='" + sexNo + '\'' +
                ", phoneNo='" + phoneNo + '\'' +
                ", mailboxNo='" + mailboxNo + '\'' +
                ", password='" + password + '\'' +
                ", regionDate=" + regionDate +
                ", userType='" + userType + '\'' +
                ", lastLoginDate=" + lastLoginDate +
                ", userState='" + userState + '\'' +
                ", userGrade='" + userGrade + '\'' +
                ", userGradeChangeDate=" + userGradeChangeDate +
                ", userSign='" + userSign + '\'' +
                ", userLocal='" + userLocal + '\'' +
                '}';
    }
}
