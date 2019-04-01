package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tom
 * @since 2019-01-18
 */
@TableName("user_log")
public class UserLog implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
     * ID
     */
         private String id;

        /**
     * 用户编码
     */
         private String userNo;

        /**
     * 用户登陆时间（只传天数）
     */
         private String userLoginDate;


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

    public String getUserLoginDate() {
        return userLoginDate;
    }

    public void setUserLoginDate(String userLoginDate) {
        this.userLoginDate = userLoginDate;
    }

    @Override
    public String toString() {
        return "UserLog{" +
        "id=" + id +
        ", userNo=" + userNo +
        ", userLoginDate=" + userLoginDate +
        "}";
    }
}
