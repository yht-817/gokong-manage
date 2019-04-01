package cn.gokong.www.gokongmain.vo.used_deal_leave;

import cn.gokong.www.base.util.RelativeDateFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 说明:分页查询留言列表
 *
 * @author ikook
 * @CreateDate 2018/10/1 17:20
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryListOutput", description = "分页查询留言列表")
public class PageQueryListOutput {
    @ApiModelProperty(dataType = "String",name = "fromUserNo",value = "回复者编码")
    private String fromUserNo;
    @ApiModelProperty(dataType = "String",name = "fromUserHead",value = "回复者头像")
    private String fromUserHead;
    @ApiModelProperty(dataType = "String",name = "fromUserNick",value = "回复者昵称")
    private String fromUserNick;

    @ApiModelProperty(dataType = "String",name = "toUserNo",value = "被回复者编码")
    private String toUserNo;
    @ApiModelProperty(dataType = "String",name = "toUserNick",value = "被回复者昵称")
    private String toUserNick;

    @ApiModelProperty(dataType = "String",name = "leaveNo",value = "留言编码")
    private String leaveNo;
    @ApiModelProperty(dataType = "String",name = "leaveContent",value = "留言内容")
    private String leaveContent;
    @ApiModelProperty(dataType = "String",name = "leaveTime",value = "留言时间")
    private String leaveTime;

    public String getLeaveNo() {
        return leaveNo;
    }

    public void setLeaveNo(String leaveNo) {
        this.leaveNo = leaveNo;
    }

    public String getFromUserNo() {
        return fromUserNo;
    }

    public void setFromUserNo(String fromUserNo) {
        this.fromUserNo = fromUserNo;
    }

    public String getFromUserHead() {
        return fromUserHead;
    }

    public void setFromUserHead(String fromUserHead) {
        this.fromUserHead = fromUserHead;
    }

    public String getFromUserNick() {
        return fromUserNick;
    }

    public void setFromUserNick(String fromUserNick) {
        this.fromUserNick = fromUserNick;
    }

    public String getToUserNo() {
        return toUserNo;
    }

    public void setToUserNo(String toUserNo) {
        this.toUserNo = toUserNo;
    }

    public String getToUserNick() {
        return toUserNick;
    }

    public void setToUserNick(String toUserNick) {
        this.toUserNick = toUserNick;
    }

    public String getLeaveContent() {
        return leaveContent;
    }

    public void setLeaveContent(String leaveContent) {
        this.leaveContent = leaveContent;
    }

    public String getLeaveTime() {
        return leaveTime;
    }

    public void setLeaveTime(Date leaveTime) {
        this.leaveTime = RelativeDateFormat.format(leaveTime);
    }
}
