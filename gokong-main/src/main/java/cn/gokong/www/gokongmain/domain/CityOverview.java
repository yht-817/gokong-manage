package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tom
 * @since 2018-10-11
 */
@TableName("city_overview")
public class CityOverview implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

        /**
     * 城市编码
     */
         private String cityName;

        /**
     * ui的姓名
     */
         private String uiName;

        /**
     * 图标地址
     */
         private String iconPath;

        /**
     * 跳转地址
     */
         private String jumpPath;

        /**
     * 排序
     */
         private Integer descs;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getUiName() {
        return uiName;
    }

    public void setUiName(String uiName) {
        this.uiName = uiName;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getJumpPath() {
        return jumpPath;
    }

    public void setJumpPath(String jumpPath) {
        this.jumpPath = jumpPath;
    }

    public Integer getDescs() {
        return descs;
    }

    public void setDescs(Integer descs) {
        this.descs = descs;
    }

    @Override
    public String toString() {
        return "CityOverview{" +
        "id=" + id +
        ", cityName=" + cityName +
        ", uiName=" + uiName +
        ", iconPath=" + iconPath +
        ", jumpPath=" + jumpPath +
        ", descs=" + descs +
        "}";
    }
}
