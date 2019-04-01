package cn.gokong.www.gokongmain.domain;

import lombok.Data;

/**
 * 说明:
 *
 * @author ikook
 * @CreateDate 2018/12/13 16:00
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@Data
public class ShareAccessToken {
    private String access_token;
    private String expires_in;
}
