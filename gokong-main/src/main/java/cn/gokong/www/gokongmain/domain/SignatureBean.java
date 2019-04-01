package cn.gokong.www.gokongmain.domain;

import lombok.Data;

/**
 * 说明:
 *
 * @author ikook
 * @CreateDate 2018/12/13 15:52
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Data
public class SignatureBean {
    private String noncestr;
    private String url;
    private String timestamp;
    private String signature;
}
