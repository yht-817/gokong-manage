package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 用户主页访问记录
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@TableName("user_home_page_visit")
public class UserHomePageVisit implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 主页所属用户编码
     */
    private String userNo;

    /**
     * 访问者编码
     */
    private String visitNo;

    /**
     * 访问日期
     */
    private Date visitDate;


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

    public String getVisitNo() {
        return visitNo;
    }

    public void setVisitNo(String visitNo) {
        this.visitNo = visitNo;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    @Override
    public String toString() {
        return "UserHomePageVisit{" +
        "id=" + id +
        ", userNo=" + userNo +
        ", visitNo=" + visitNo +
        ", visitDate=" + visitDate +
        "}";
    }
}
