package cn.gokong.www.gokongmain.vo.chinese_circle;

import cn.gokong.www.base.util.RelativeDateFormat;
import cn.gokong.www.gokongmain.vo.chinese_circle_evaluate.PageQueryCircleEvaluateOutput;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * 说明:分页查询资讯详情
 *
 * @author ikook
 * @CreateDate 2018/9/18 15:06
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "CircleDetails", description = "分页查询资讯详情")
public class CircleDetails {
    @ApiModelProperty(dataType = "String",name = "circleNo",value = "华人圈编码")
    private String circleNo;

    @ApiModelProperty(dataType = "String",name = "circleImg",value = "图片")
    private String circleImg;

    @ApiModelProperty(dataType = "String",name = "liked",value = "是否点赞")
    private boolean liked;

    @ApiModelProperty(dataType = "String",name = "likeNum",value = "点赞数量")
    private int likeNum;

    @ApiModelProperty(dataType = "String",name = "collection",value = "是否收藏")
    private boolean collection;

    @ApiModelProperty(dataType = "String",name = "instructions",value = "说明")
    private String instructions;

    @ApiModelProperty(dataType = "String",name = "commentsNum",value = "评论数量")
    private int commentsNum;

    @ApiModelProperty(dataType = "String",name = "createTime",value = "发布时间")
    private String createTime;

    @ApiModelProperty(dataType = "String",name = "evaluates",value = "2条评论数据")
    private List<PageQueryCircleEvaluateOutput> evaluates;

    public List<PageQueryCircleEvaluateOutput> getEvaluates() {
        return evaluates;
    }

    public void setEvaluates(List<PageQueryCircleEvaluateOutput> evaluates) {
        this.evaluates = evaluates;
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

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public boolean isCollection() {
        return collection;
    }

    public void setCollection(boolean collection) {
        this.collection = collection;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getCommentsNum() {
        return commentsNum;
    }

    public void setCommentsNum(int commentsNum) {
        this.commentsNum = commentsNum;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = RelativeDateFormat.format(createTime);
    }
}
