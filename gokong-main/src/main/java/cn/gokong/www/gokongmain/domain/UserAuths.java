package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 用户认证信息表
 * </p>
 *
 * @author iKook
 * @since 2018-09-09
 */
@TableName("user_auths")
public class UserAuths implements Serializable {

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
     * 应用Key
     */
    private String thirdKey;

    /**
     * 应用类型
     */
    private String thirdType;

    /**
     * 绑定日期
     */
    private Date thirdDate;


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

    public String getThirdKey() {
        return thirdKey;
    }

    public void setThirdKey(String thirdKey) {
        this.thirdKey = thirdKey;
    }

    public String getThirdType() {
        return thirdType;
    }

    public void setThirdType(String thirdType) {
        this.thirdType = thirdType;
    }

    public Date getThirdDate() {
        return thirdDate;
    }

    public void setThirdDate(Date thirdDate) {
        this.thirdDate = thirdDate;
    }

    @Override
    public String toString() {
        return "UserAuths{" +
        "id=" + id +
        ", userNo=" + userNo +
        ", thirdKey=" + thirdKey +
        ", thirdType=" + thirdType +
        ", thirdDate=" + thirdDate +
        "}";
    }
}
