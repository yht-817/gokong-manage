package cn.gokong.www.gokongmain.vo.service_message;

import cn.gokong.www.base.util.RelativeDateFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * 说明:分页查询服务消息出参
 *
 * @author ikook
 * @CreateDate 2018/9/15 20:47
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@ApiModel(value = "PageQueryServiceMessageOutput", description = "分页查询服务消息出参")
public class PageQueryServiceMessageOutput {

    @ApiModelProperty(dataType = "String",name = "serviceNo",value = "业务单号")
    private String serviceNo;

    @ApiModelProperty(dataType = "String",name = "noticeType",value = "通知类型(普通服务消息:10390001 好友申请通知:10390002 群聊邀请通知:10390003 群聊申请通知:10390004 链接通知:10390005 同城活动申请：10390006)")
    private String noticeType;

    @ApiModelProperty(dataType = "String",name = "sendNo",value = "发送方编码")
    private String sendNo;

    @ApiModelProperty(dataType = "String",name = "sendHead",value = "发送方头像")
    private String sendHead;

    @ApiModelProperty(dataType = "String",name = "sendTitle",value = "通知主题")
    private String sendTitle;

    @ApiModelProperty(dataType = "String",name = "sendDate",value = "通知时间")
    private String sendDate;

    @ApiModelProperty(dataType = "String",name = "sendText",value = "通知内容")
    private String sendText;

    @ApiModelProperty(dataType = "String",name = "sendUrl",value = "跳转链接")
    private String sendUrl;

    @ApiModelProperty(dataType = "String",name = "msgState",value = "处理状态(未处理：10200001 已处理：10200002)")
    private String msgState;

    @ApiModelProperty(dataType = "String",name = "joinGroup",value = "加入的群")
    private String joinGroup;

    public String getSendNo() {
        return sendNo;
    }

    public void setSendNo(String sendNo) {
        this.sendNo = sendNo;
    }

    public String getSendHead() {
        return sendHead;
    }

    public void setSendHead(String sendHead) {
        this.sendHead = sendHead;
    }

    public String getJoinGroup() {
        return joinGroup;
    }

    public void setJoinGroup(String joinGroup) {
        this.joinGroup = joinGroup;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public String getSendTitle() {
        return sendTitle;
    }

    public void setSendTitle(String sendTitle) {
        this.sendTitle = sendTitle;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = RelativeDateFormat.format(sendDate);
    }

    public String getSendText() {
        return sendText;
    }

    public void setSendText(String sendText) {
        this.sendText = sendText;
    }

    public String getSendUrl() {
        return sendUrl;
    }

    public void setSendUrl(String sendUrl) {
        this.sendUrl = sendUrl;
    }

    public String getServiceNo() {
        return serviceNo;
    }

    public void setServiceNo(String serviceNo) {
        this.serviceNo = serviceNo;
    }

    public String getMsgState() {
        return msgState;
    }

    public void setMsgState(String msgState) {
        this.msgState = msgState;
    }
}
