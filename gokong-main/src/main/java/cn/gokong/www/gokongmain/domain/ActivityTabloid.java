package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 活动小报
 * </p>
 *
 * @author ikook
 * @since 2018-10-22
 */
@TableName("activity_tabloid")
public class ActivityTabloid implements Serializable {

    private static final long serialVersionUID = 1L;

        /**
     * ID
     */
         private String id;

        /**
     * 活动编码
     */
         private String activityNo;

        /**
     * 小报内容
     */
         private String content;

        /**
     * 活动时间
     */
         private Date activityTime;

        /**
     * 所在城市
     */
         private String cityName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getActivityNo() {
        return activityNo;
    }

    public void setActivityNo(String activityNo) {
        this.activityNo = activityNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Date activityTime) {
        this.activityTime = activityTime;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    @Override
    public String toString() {
        return "ActivityTabloid{" +
        "id=" + id +
        ", activityNo=" + activityNo +
        ", content=" + content +
        ", activityTime=" + activityTime +
        ", cityName=" + cityName +
        "}";
    }
}
