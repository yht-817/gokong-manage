package cn.gokong.www.gokongmain.service.impl;

import cn.gokong.www.gokongmain.domain.ShareAccessToken;
import cn.gokong.www.gokongmain.domain.Ticket;
import cn.gokong.www.gokongmain.service.WeiXinJsSdkService;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import cn.ikeek.www.weixin.js.dao.ShareDao;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 说明:
 *
 * @author ikook
 * @CreateDate 2018/12/13 15:55
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@Service
public class WeiXinJsSdkServiceImpl implements WeiXinJsSdkService {

    private static String APP_ID = "wx5bcd387699293534";

    private static String APP_SECRET = "60815f0862f60ba3458cb11f5e50d520";

    @Override
    public Map<String, String> getSignature(String url) {
        return ShareDao.getSignature(url);
    }
}
