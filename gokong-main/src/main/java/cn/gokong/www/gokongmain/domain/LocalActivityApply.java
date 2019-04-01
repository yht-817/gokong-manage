package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 同城活动用户申请表
 * </p>
 *
 * @author ikook
 * @since 2018-10-05
 */
@TableName("local_activity_apply")
public class LocalActivityApply implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
     * ID
     */
         private String id;


         private String applyNo;

        /**
     * 用户编码
     */
         private String userNo;

        /**
     * 活动编码
     */
         private String activityNo;

        /**
     * 联系电话
     */
         private String phone;

        /**
     * 介绍
     */
         private String introduce;

        /**
     * 性别
     */
         private String sex;

        /**
     * 申请状态
     */
         private String applyState;

        /**
     * 申请时间
     */
         private Date applyTime;

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
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

    public String getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(String activityNo) {
        this.activityNo = activityNo;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getApplyState() {
        return applyState;
    }

    public void setApplyState(String applyState) {
        this.applyState = applyState;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    @Override
    public String toString() {
        return "LocalActivityApply{" +
        "id=" + id +
        ", userNo=" + userNo +
        ", activityNo=" + activityNo +
        ", phone=" + phone +
        ", introduce=" + introduce +
        ", sex=" + sex +
        ", applyState=" + applyState +
        ", applyTime=" + applyTime +
        "}";
    }
}
