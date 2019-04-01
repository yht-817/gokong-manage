package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 华人圈点赞表
 * </p>
 *
 * @author ikook
 * @since 2018-09-18
 */
@TableName("chinese_circle_praise")
public class ChineseCirclePraise implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 华人圈编码
     */
    private String circleNo;

    /**
     * 用户编码
     */
    private String userNo;

    /**
     * 点赞时间
     */
    private Date createTime;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCircleNo() {
        return circleNo;
    }

    public void setCircleNo(String circleNo) {
        this.circleNo = circleNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "ChineseCirclePraise{" +
        "id=" + id +
        ", circleNo=" + circleNo +
        ", userNo=" + userNo +
        ", createTime=" + createTime +
        "}";
    }
}
