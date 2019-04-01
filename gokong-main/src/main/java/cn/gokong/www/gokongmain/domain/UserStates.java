package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ikook
 * @since 2018-09-14
 */
@TableName("user_states")
public class UserStates implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户编码
     */
    private String userNo;

    /**
     * 存入时间
     */
    private Date onlineDate;

    /**
     * 经度
     */
    private Float longitude;

    /**
     * 纬度
     */
    private Float latitude;


    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public Date getOnlineDate() {
        return onlineDate;
    }

    public void setOnlineDate(Date onlineDate) {
        this.onlineDate = onlineDate;
    }

    public Float getLongitude() {
        return longitude;
    }

    public void setLongitude(Float longitude) {
        this.longitude = longitude;
    }

    public Float getLatitude() {
        return latitude;
    }

    public void setLatitude(Float latitude) {
        this.latitude = latitude;
    }

    @Override
    public String toString() {
        return "UserStates{" +
        "userNo=" + userNo +
        ", onlineDate=" + onlineDate +
        ", longitude=" + longitude +
        ", latitude=" + latitude +
        "}";
    }
}
