package cn.gokong.www.gokongmain.domain;

import java.io.Serializable;

/**
 * my_ui
 *
 * @author 大狼狗 2018-10-24
 */
public class MyUi implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ui的名字
     */
    private String uiName;

    /**
     * ui的链接
     */
    private String uiUrl;

    /**
     * 图标
     */
    private String uiIcon;

    /**
     * 对显示的数据控制显不显示
     */
    private int uiShow;

    /**
     * 按钮的排序
     */
    private int uiSort;


    public String getUiName() {
        return uiName;
    }

    public void setUiName(String uiName) {
        this.uiName = uiName;
    }

    public String getUiUrl() {
        return uiUrl;
    }

    public void setUiUrl(String uiUrl) {
        this.uiUrl = uiUrl;
    }

    public String getUiIcon() {
        return uiIcon;
    }

    public void setUiIcon(String uiIcon) {
        this.uiIcon = uiIcon;
    }

    public int getUiShow() {
        return uiShow;
    }

    public void setUiShow(int uiShow) {
        this.uiShow = uiShow;
    }

    public int getUiSort() {
        return uiSort;
    }

    public void setUiSort(int uiSort) {
        this.uiSort = uiSort;
    }

}