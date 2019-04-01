package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 配置表
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@TableName("sys_config")
public class SysConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 配置代码
     */
    private String configCode;

    /**
     * 备注
     */
    private String configRemark;

    /**
     * 标准值
     */
    private Integer configVal;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConfigCode() {
        return configCode;
    }

    public void setConfigCode(String configCode) {
        this.configCode = configCode;
    }

    public String getConfigRemark() {
        return configRemark;
    }

    public void setConfigRemark(String configRemark) {
        this.configRemark = configRemark;
    }

    public Integer getConfigVal() {
        return configVal;
    }

    public void setConfigVal(Integer configVal) {
        this.configVal = configVal;
    }

    @Override
    public String toString() {
        return "SysConfig{" +
        "id=" + id +
        ", configCode=" + configCode +
        ", configRemark=" + configRemark +
        ", configVal=" + configVal +
        "}";
    }
}
