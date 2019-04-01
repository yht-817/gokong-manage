package cn.gokong.www.gokongmain.domain;

import java.io.Serializable;


public class BlacFriendInfo implements Serializable {

    private static final long serialVersionUID = 1L;


    private String userNo;

    private String userHead;

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

    @Override
    public String toString() {
        return "BlacFriendInfo{" +
                "userNo='" + userNo + '\'' +
                ", userHead='" + userHead + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}
