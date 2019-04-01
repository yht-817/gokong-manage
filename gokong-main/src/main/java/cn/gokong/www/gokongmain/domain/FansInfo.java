package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 粉丝表
 * </p>
 *
 * @author ikook
 * @since 2018-09-14
 */
@TableName("fans_info")
public class FansInfo implements Serializable {

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
     * 粉丝编码
     */
    private String fansUserNo;

    /**
     * 关注时间
     */
    private Date createTime;


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

    public String getFansUserNo() {
        return fansUserNo;
    }

    public void setFansUserNo(String fansUserNo) {
        this.fansUserNo = fansUserNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "FansInfo{" +
        "id=" + id +
        ", userNo=" + userNo +
        ", fansUserNo=" + fansUserNo +
        ", createTime=" + createTime +
        "}";
    }
}
