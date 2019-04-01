package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

/**
 * <p>
 * 二级服务范围
 * </p>
 *
 * @author tom
 * @since 2018-09-13
 */
@TableName("service_scope_two")
public class ServiceScopeTwo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 一级服务范围编码
     */
    private String scopeOneNo;

    /**
     * 二级服务范围编码
     */
    private String scopeTwoNo;

    /**
     * 二级服务范围名称
     */
    private String scopeTwoName;

    /**
     * 二级城市的图标
     */
    private String scopeTwoIcon;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScopeOneNo() {
        return scopeOneNo;
    }

    public void setScopeOneNo(String scopeOneNo) {
        this.scopeOneNo = scopeOneNo;
    }

    public String getScopeTwoNo() {
        return scopeTwoNo;
    }

    public void setScopeTwoNo(String scopeTwoNo) {
        this.scopeTwoNo = scopeTwoNo;
    }

    public String getScopeTwoName() {
        return scopeTwoName;
    }

    public void setScopeTwoName(String scopeTwoName) {
        this.scopeTwoName = scopeTwoName;
    }

    public String getScopeTwoIcon() {
        return scopeTwoIcon;
    }

    public void setScopeTwoIcon(String scopeTwoIcon) {
        this.scopeTwoIcon = scopeTwoIcon;
    }

    @Override
    public String toString() {
        return "ServiceScopeTwo{" +
                "id='" + id + '\'' +
                ", scopeOneNo='" + scopeOneNo + '\'' +
                ", scopeTwoNo='" + scopeTwoNo + '\'' +
                ", scopeTwoName='" + scopeTwoName + '\'' +
                ", scopeTwoIcon='" + scopeTwoIcon + '\'' +
                '}';
    }
}
