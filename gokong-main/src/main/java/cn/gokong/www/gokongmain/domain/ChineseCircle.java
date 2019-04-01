package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 华人圈
 * </p>
 *
 * @author iKook
 * @since 2018-09-12
 */
@TableName("chinese_circle")
public class ChineseCircle implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private String id;

    /**
     * 华人圈编码
     */
    private String circleNo;

    /**
     * 说明
     */
    private String instructions;

    /**
     * 图片
     */
    private String circleImg;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 点赞数量
     */
    private Integer likeNum;

    /**
     * 评论数量
     */
    private Integer commentsNum;

    /**
     * 分享数量
     */
    private Integer shareNum;

    /**
     * 发布人编码
     */
    private String userNo;

    /**
     * 城市编码
     */
    private String cityNo;


    /**
     * 纬度
     */
    private String lat;
    /**
     * 经度
     */
    private String lng;

    public String getCityNo() {
        return cityNo;
    }

    public void setCityNo(String cityNo) {
        this.cityNo = cityNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCircleNo() {
        return circleNo;
    }

    public void setCircleNo(String circleNo) {
        this.circleNo = circleNo;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getCircleImg() {
        return circleImg;
    }

    public void setCircleImg(String circleImg) {
        this.circleImg = circleImg;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(Integer likeNum) {
        this.likeNum = likeNum;
    }

    public Integer getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(Integer commentsNum) {
        this.commentsNum = commentsNum;
    }

    public Integer getShareNum() {
        return shareNum;
    }

    public void setShareNum(Integer shareNum) {
        this.shareNum = shareNum;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "ChineseCircle{" +
                "id='" + id + '\'' +
                ", circleNo='" + circleNo + '\'' +
                ", instructions='" + instructions + '\'' +
                ", circleImg='" + circleImg + '\'' +
                ", createTime=" + createTime +
                ", likeNum=" + likeNum +
                ", commentsNum=" + commentsNum +
                ", shareNum=" + shareNum +
                ", userNo='" + userNo + '\'' +
                ", cityNo='" + cityNo + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}
