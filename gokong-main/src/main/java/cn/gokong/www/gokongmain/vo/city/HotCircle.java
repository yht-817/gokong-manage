package cn.gokong.www.gokongmain.vo.city;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("chinese_circle")
public class HotCircle {
    private static final long serialVersionUID = 1L;

    /**
     * 华人圈编码
     */
    private String circleNo;

    /**
     * 图片
     */
    private String circleImg;


    /**
     * 点赞数量
     */
    private Integer likeNum;

    /**
     * 评论数量
     */
    private Integer commentsNum;

    /**
     * 用户头像
     */
    private String userHead;

    /**
     * 用户的昵称
     */
    private String userName;

    /**
     * 距离当前用户多少米
     */

    private String distance;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCircleNo() {
        return circleNo;
    }

    public void setCircleNo(String circleNo) {
        this.circleNo = circleNo;
    }

    public String getCircleImg() {
        return circleImg;
    }

    public void setCircleImg(String circleImg) {
        this.circleImg = circleImg;
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

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "HotCircle{" +
                "circleNo='" + circleNo + '\'' +
                ", circleImg='" + circleImg + '\'' +
                ", likeNum=" + likeNum +
                ", commentsNum=" + commentsNum +
                ", userHead='" + userHead + '\'' +
                ", userName='" + userName + '\'' +
                ", distance='" + distance + '\'' +
                '}';
    }
}
