package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 职位类型
 * </p>
 *
 * @author ikook
 * @since 2018-10-02
 */
@TableName("position_type")
public class PositionType implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 职位编码
     */
    private String typeNo;

    /**
     * 职位名称
     */
    private String typeName;


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

    @Override
    public String toString() {
        return "PositionType{" +
        "id=" + id +
        ", typeNo=" + typeNo +
        ", typeName=" + typeName +
        "}";
    }

    public PositionType(String id, String typeNo, String typeName) {
        this.id = id;
        this.typeNo = typeNo;
        this.typeName = typeName;
    }
}
