package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 用户主页
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@TableName("user_home_page")
public class UserHomePage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 用户编码
     */
    private String userNo;

    /**
     * 介绍图片1
     */
    private String homePhoto1;

    /**
     * 介绍图片1
     */
    private String homePhoto2;

    /**
     * 介绍图片1
     */
    private String homePhoto3;

    /**
     * 介绍图片1
     */
    private String homePhoto4;

    /**
     * 介绍图片1
     */
    private String homePhoto5;

    /**
     * 介绍图片1
     */
    private String homePhoto6;

    /**
     * 介绍图片1
     */
    private String homePhoto7;

    /**
     * 介绍图片1
     */
    private String homePhoto8;

    /**
     * 介绍图片1
     */
    private String homePhoto9;

    /**
     * 封面标识
     */
    private String coverNo;


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

    @Override
    public String toString() {
        return "UserHomePage{" +
        "id=" + id +
        ", userNo=" + userNo +
        ", homePhoto1=" + homePhoto1 +
        ", homePhoto2=" + homePhoto2 +
        ", homePhoto3=" + homePhoto3 +
        ", homePhoto4=" + homePhoto4 +
        ", homePhoto5=" + homePhoto5 +
        ", homePhoto6=" + homePhoto6 +
        ", homePhoto7=" + homePhoto7 +
        ", homePhoto8=" + homePhoto8 +
        ", homePhoto9=" + homePhoto9 +
        ", coverNo=" + coverNo +
        "}";
    }
}
