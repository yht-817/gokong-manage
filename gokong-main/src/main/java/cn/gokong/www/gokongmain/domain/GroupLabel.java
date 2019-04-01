package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 群标签表
 * </p>
 *
 * @author ikook
 * @since 2018-09-13
 */
@TableName("group_label")
public class GroupLabel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 标签编码
     */
    private String labelNo;

    /**
     * 标签名称
     */
    private String labelName;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabelNo() {
        return labelNo;
    }

    public void setLabelNo(String labelNo) {
        this.labelNo = labelNo;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    @Override
    public String toString() {
        return "GroupLabel{" +
        "id=" + id +
        ", labelNo=" + labelNo +
        ", labelName=" + labelName +
        "}";
    }
}
