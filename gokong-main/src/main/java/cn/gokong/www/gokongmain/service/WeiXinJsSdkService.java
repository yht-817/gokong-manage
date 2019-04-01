package cn.gokong.www.gokongmain.service;

import java.util.Map;

/**
 * 说明:
 *
 * @author ikook
 * @CreateDate 2018/12/13 15:54
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

public interface WeiXinJsSdkService {

    Map<String, String> getSignature(String url);
}
