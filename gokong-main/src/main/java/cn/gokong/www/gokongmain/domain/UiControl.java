package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author tom
 * @since 2018-09-12
 */
@TableName("ui_control")
public class UiControl implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 城市编码
     */
    private String cityNo;

    /**
     * ui的姓名
     */
    private String uiName;

    /**
     * 图标地址
     */
    private String iconPath;

    /**
     * 展示的类型数据
     */
    private String uiType;

    /**
     * 跳转地址
     */
    private String jumpPath;


    public String getJumpPath() {
        return jumpPath;
    }

    public void setJumpPath(String jumpPath) {
        this.jumpPath = jumpPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
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

    public String getUiType() {
        return uiType;
    }

    public void setUiType(String uiType) {
        this.uiType = uiType;
    }

    @Override
    public String toString() {
        return "UiControl{" +
        "id=" + id +
        ", cityNo=" + cityNo +
        ", uiName=" + uiName +
        ", iconPath=" + iconPath +
        ", uiType=" + uiType +
        "}";
    }
}
