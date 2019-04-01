package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 特权用户
 * </p>
 *
 * @author ikook
 * @since 2018-10-19
 */
@TableName("privilege_user")
public class PrivilegeUser implements Serializable {

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
     * 华人圈
     */
         private Integer chineseCircle;

        /**
     * 招聘求职
     */
         private Integer companyRecruitment;

        /**
     * 二手交易
     */
         private Integer usedDeal;

        /**
     * 资讯
     */
         private Integer information;

        /**
     * 同城活动
     */
         private Integer localActivity;


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

    public Integer getChineseCircle() {
        return chineseCircle;
    }

    public void setChineseCircle(Integer chineseCircle) {
        this.chineseCircle = chineseCircle;
    }

    public Integer getCompanyRecruitment() {
        return companyRecruitment;
    }

    public void setCompanyRecruitment(Integer companyRecruitment) {
        this.companyRecruitment = companyRecruitment;
    }

    public Integer getUsedDeal() {
        return usedDeal;
    }

    public void setUsedDeal(Integer usedDeal) {
        this.usedDeal = usedDeal;
    }

    public Integer getInformation() {
        return information;
    }

    public void setInformation(Integer information) {
        this.information = information;
    }

    public Integer getLocalActivity() {
        return localActivity;
    }

    public void setLocalActivity(Integer localActivity) {
        this.localActivity = localActivity;
    }

    @Override
    public String toString() {
        return "PrivilegeUser{" +
        "id=" + id +
        ", userNo=" + userNo +
        ", chineseCircle=" + chineseCircle +
        ", companyRecruitment=" + companyRecruitment +
        ", usedDeal=" + usedDeal +
        ", information=" + information +
        ", localActivity=" + localActivity +
        "}";
    }
}
