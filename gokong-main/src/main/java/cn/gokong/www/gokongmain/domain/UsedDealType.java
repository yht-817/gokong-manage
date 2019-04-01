package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 二手交易分类
 * </p>
 *
 * @author ikook
 * @since 2018-10-01
 */
@TableName("used_deal_type")
public class UsedDealType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 二手分类编码
     */
    private String typeNo;

    /**
     * 二手分类名称
     */
    private String typeName;

    /**
     * 优先级
     */
    private Integer priority;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeNo() {
        return typeNo;
    }

    public void setTypeNo(String typeNo) {
        this.typeNo = typeNo;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "UsedDealType{" +
        "id=" + id +
        ", typeNo=" + typeNo +
        ", typeName=" + typeName +
        ", priority=" + priority +
        "}";
    }

    public UsedDealType(String id, String typeNo, String typeName) {
        this.id = id;
        this.typeNo = typeNo;
        this.typeName = typeName;
    }
}
