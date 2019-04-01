package cn.gokong.www.gokongmain.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.Date;
import java.io.Serializable;

/**
 * <p>
 * 服务通知模板
 * </p>
 *
 * @author ikook
 * @since 2018-09-13
 */
@TableName("chat_service_message")
public class ChatServiceMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 用户编码
     */
    private String userNo;

    /**
     * 发送方编码
     */
    private String sendNo;
    /**
     * 发送方头像
     */
    private String sendHead;

    /**
     * 通知类型 1039
     */
    private String noticeType;

    /**
     * 通知主题
     */
    private String sendTitle;

    /**
     * 通知时间
     */
    private Date sendDate;

    /**
     * 通知内容
     */
    private String sendText;

    /**
     * 通知详细项目1
     */
    private String sendProject1;

    /**
     * 通知详细项目2
     */
    private String sendProject2;

    /**
     * 跳转地址
     */
    private String sendUrl;

    /**
     * 业务单号
     */
    private String serviceNo;

    /**
     * 消息状态
     */
    private String msgState;

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

    public Date getSendDate() {
        return sendDate;
    }

    public void setSendDate(Date sendDate) {
        this.sendDate = sendDate;
    }

    public String getSendText() {
        return sendText;
    }

    public void setSendText(String sendText) {
        this.sendText = sendText;
    }

    public String getSendProject1() {
        return sendProject1;
    }

    public void setSendProject1(String sendProject1) {
        this.sendProject1 = sendProject1;
    }

    public String getSendProject2() {
        return sendProject2;
    }

    public void setSendProject2(String sendProject2) {
        this.sendProject2 = sendProject2;
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
