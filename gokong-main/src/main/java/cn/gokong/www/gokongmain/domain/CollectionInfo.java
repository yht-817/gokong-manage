package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 收藏信息表
 * </p>
 *
 * @author iKook
 * @since 2018-09-11
 */
@TableName("collection_info")
public class CollectionInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 用户编码
     */
    private String userNo;

    /**
     * 收藏类型 1011
     */
    private String collectionType;

    /**
     * 内容编码
     */
    private String contentNo;

    /**
     * 收藏日期
     */
    private Date collectionDate;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getCollectionType() {
        return collectionType;
    }

    public void setCollectionType(String collectionType) {
        this.collectionType = collectionType;
    }

    public String getContentNo() {
        return contentNo;
    }

    public void setContentNo(String contentNo) {
        this.contentNo = contentNo;
    }

    public Date getCollectionDate() {
        return collectionDate;
    }

    public void setCollectionDate(Date collectionDate) {
        this.collectionDate = collectionDate;
    }

    @Override
    public String toString() {
        return "CollectionInfo{" +
        "id=" + id +
        ", userNo=" + userNo +
        ", collectionType=" + collectionType +
        ", contentNo=" + contentNo +
        ", collectionDate=" + collectionDate +
        "}";
    }
}
