package cn.gokong.www.gokongmain.vo.information;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 说明:查询URL链接内容出参
 *
 * @author ikook
 * @CreateDate 2018/9/12 20:10
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "QueryArticleContentOuput", description = "查询URL链接内容出参")
public class QueryArticleContentOuput {
    /**
     * 文章标题
     */
    @ApiModelProperty(dataType = "String",name = "title",value = "文章标题")
    private String title;
    /**
     * 发布者信息
     */
    @ApiModelProperty(dataType = "String",name = "author",value = "发布者信息")
    private String author;
    /**
     * 发布时间
     */
    @ApiModelProperty(dataType = "String",name = "time",value = "发布时间")
    private String time;
    /**
     * 文章HTML内容
     */
    @ApiModelProperty(dataType = "String",name = "content",value = "文章HTML内容")
    private String content;
    /**
     * 文章文本内容
     */
    @ApiModelProperty(dataType = "String",name = "contentText",value = "文章文本内容")
    private String contentText;
    /**
     * 标题图片
     */
    @ApiModelProperty(dataType = "String",name = "titleImg",value = "标题图片")
    private String titleImg;

    /**
     * 图片布局
     */
    @ApiModelProperty(dataType = "String",name = "imgLayout",value = "图片布局")
    private String imgLayout;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getTitleImg() {
        return titleImg;
    }

    public void setTitleImg(String titleImg) {
        this.titleImg = titleImg;
    }

    public String getImgLayout() {
        return imgLayout;
    }

    public void setImgLayout(String imgLayout) {
        this.imgLayout = imgLayout;
    }
}
