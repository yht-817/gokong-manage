package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 营销弹窗设置表
 * </p>
 *
 * @author ikook
 * @since 2018-09-19
 */
@TableName("marketing_pop_set")
public class MarketingPopSet implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 弹窗编码
     */
    private String popNo;

    /**
     * 弹窗图片
     */
    private String popPhoto;

    /**
     * url路径
     */
    private String popUrl;

    /**
     * 说明
     */
    private String popRemark;

    /**
     * 开始时间
     */
    private Date popBeginDate;

    /**
     * 结束时间
     */
    private Date popEndDate;

    /**
     * 创建时间
     */
    private Date createTime;

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPopNo() {
        return popNo;
    }

    public void setPopNo(String popNo) {
        this.popNo = popNo;
    }

    public String getPopPhoto() {
        return popPhoto;
    }

    public void setPopPhoto(String popPhoto) {
        this.popPhoto = popPhoto;
    }

    public String getPopUrl() {
        return popUrl;
    }

    public void setPopUrl(String popUrl) {
        this.popUrl = popUrl;
    }

    public String getPopRemark() {
        return popRemark;
    }

    public void setPopRemark(String popRemark) {
        this.popRemark = popRemark;
    }

    public Date getPopBeginDate() {
        return popBeginDate;
    }

    public void setPopBeginDate(Date popBeginDate) {
        this.popBeginDate = popBeginDate;
    }

    public Date getPopEndDate() {
        return popEndDate;
    }

    public void setPopEndDate(Date popEndDate) {
        this.popEndDate = popEndDate;
    }

    @Override
    public String toString() {
        return "MarketingPopSet{" +
        "id=" + id +
        ", popNo=" + popNo +
        ", popPhoto=" + popPhoto +
        ", popUrl=" + popUrl +
        ", popRemark=" + popRemark +
        ", popBeginDate=" + popBeginDate +
        ", popEndDate=" + popEndDate +
        "}";
    }
}
