package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * APP版本跟新控制
 * </p>
 *
 * @author ikook
 * @since 2018-09-26
 */
@TableName("mobile_version")
public class MobileVersion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    private String id;

    /**
     * APP名称
     */
    private String appName;

    /**
     * APP版本
     */
    private String appVersion;

    /**
     * APP类型
     */
    private String appType;

    /**
     * 文件下载路径
     */
    private String appFilePath;

    /**
     * 二维码下载路径
     */
    private String appQrcodePath;

    /**
     * 版本跟新提示语
     */
    private String appLog;

    /**
     * 更新时间
     */
    private Date appTime;

    /**
     * 强制更新
     */
    private String constraints;

    /**
     * 文件大小
     */
    private String targetSize;

    /**
     * 升级开关
     */
    private String updateSwitch;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppFilePath() {
        return appFilePath;
    }

    public void setAppFilePath(String appFilePath) {
        this.appFilePath = appFilePath;
    }

    public String getAppQrcodePath() {
        return appQrcodePath;
    }

    public void setAppQrcodePath(String appQrcodePath) {
        this.appQrcodePath = appQrcodePath;
    }

    public String getAppLog() {
        return appLog;
    }

    public void setAppLog(String appLog) {
        this.appLog = appLog;
    }

    public Date getAppTime() {
        return appTime;
    }

    public void setAppTime(Date appTime) {
        this.appTime = appTime;
    }

    public String getConstraints() {
        return constraints;
    }

    public void setConstraints(String constraints) {
        this.constraints = constraints;
    }

    public String getTargetSize() {
        return targetSize;
    }

    public void setTargetSize(String targetSize) {
        this.targetSize = targetSize;
    }

    public String getUpdateSwitch() {
        return updateSwitch;
    }

    public void setUpdateSwitch(String updateSwitch) {
        this.updateSwitch = updateSwitch;
    }

    @Override
    public String toString() {
        return "MobileVersion{" +
        "id=" + id +
        ", appName=" + appName +
        ", appVersion=" + appVersion +
        ", appType=" + appType +
        ", appFilePath=" + appFilePath +
        ", appQrcodePath=" + appQrcodePath +
        ", appLog=" + appLog +
        ", appTime=" + appTime +
        ", constraints=" + constraints +
        ", targetSize=" + targetSize +
        ", updateSwitch=" + updateSwitch +
        "}";
    }
}
