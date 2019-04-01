package cn.gokong.www.gokongmain.vo.group;

import cn.gokong.www.gokongmain.vo.base.PageQueryBase;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

/**
 * 说明:分页查询群成员列表
 *
 * @author ikook
 * @CreateDate 2018/9/13 16:37
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryGroupUserInput", description = "分页查询群成员列表")
public class PageQueryGroupUserInput extends PageQueryBase {

    @ApiModelProperty(dataType = "String",name = "groupNo",value = "群聊编码")
    @NotNull(message = "群聊编码不能为空")
    @Length(max = 50,message = "群聊编码长度<=50")
    private String groupNo;

    @ApiModelProperty(dataType = "String",name = "content",value = "检索内容")
    @Length(max = 50,message = "检索内容长度<=50")
    private String content;

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
