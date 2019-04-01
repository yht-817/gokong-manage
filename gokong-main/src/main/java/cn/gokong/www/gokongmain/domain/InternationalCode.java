package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author ikook
 * @since 2018-09-14
 */
@TableName("international_code")
public class InternationalCode implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 英文名
     */
    private String enName;

    /**
     * 中文名
     */
    private String zhName;

    /**
     * 国际域名缩写
     */
    private String acronym;

    /**
     * 国家代码
     */
    private String code;

    /**
     * 时差
     */
    private String jetLag;

    /**
     * 优先级
     */
    private Integer priority;

    /**
     * 首字母
     */
    private String firstLetter;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    public String getZhName() {
        return zhName;
    }

    public void setZhName(String zhName) {
        this.zhName = zhName;
    }

    public String getAcronym() {
        return acronym;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getJetLag() {
        return jetLag;
    }

    public void setJetLag(String jetLag) {
        this.jetLag = jetLag;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getFirstLetter() {
        return firstLetter;
    }

    public void setFirstLetter(String firstLetter) {
        this.firstLetter = firstLetter;
    }

    @Override
    public String toString() {
        return "InternationalCode{" +
        "id=" + id +
        ", enName=" + enName +
        ", zhName=" + zhName +
        ", acronym=" + acronym +
        ", code=" + code +
        ", jetLag=" + jetLag +
        ", priority=" + priority +
        ", firstLetter=" + firstLetter +
        "}";
    }
}
