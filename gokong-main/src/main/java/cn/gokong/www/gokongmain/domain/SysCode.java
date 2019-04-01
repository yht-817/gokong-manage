package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 控制代码表
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@TableName("sys_code")
public class SysCode implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String codeType;

    private String codeNo;

    private String codeName;

    private String codeRemark;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getCodeNo() {
        return codeNo;
    }

    public void setCodeNo(String codeNo) {
        this.codeNo = codeNo;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getCodeRemark() {
        return codeRemark;
    }

    public void setCodeRemark(String codeRemark) {
        this.codeRemark = codeRemark;
    }

    @Override
    public String toString() {
        return "SysCode{" +
        "id=" + id +
        ", codeType=" + codeType +
        ", codeNo=" + codeNo +
        ", codeName=" + codeName +
        ", codeRemark=" + codeRemark +
        "}";
    }
}
