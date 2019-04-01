package cn.gokong.www.gokongmain.domain;

import lombok.Data;

/**
 * 说明:
 *
 * @author ikook
 * @CreateDate 2018/12/13 15:49
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@Data
public class Ticket {

    private String tid;
    private String ticket;
    private String errcode;
    private String errmsg;
    private String expires_in;
    private String acquiretime;
    private String noncestr;
    private String timestamp;
}
