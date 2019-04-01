package cn.gokong.www.base.sms.config;

import cn.gokong.www.base.sms.constant.SMSConstant;
import cn.hutool.setting.dialect.Props;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.Properties;

/**
 * 说明:阿里云短信配置
 *
 * @author Mick
 * CreateDate 2018/6/4 14:35
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class SMSConfig {
    /**
     * 产品名称:云通信短信API产品,开发者无需替换
     */
    private static final String product = "Dysmsapi";

    /**
     * 产品域名,开发者无需替换
     */
    private static final String domain = "dysmsapi.aliyuncs.com";

    /**
     *KEY ID
     */
    private static String accessKeyId;

    /**
     *KEY SECRET
     */
    private static String accessKeySecret;

    /**
     * 签名名称
     */
    private static String sign;

    /**
     * 短信客户端
     */
    private static IClientProfile iClientProfile;

    static {
        Properties prop = Props.getProp("ali-sms.properties");
        accessKeyId = prop.getProperty(SMSConstant.SMS_ACCESS_KEY);
        accessKeySecret = prop.getProperty(SMSConstant.SMS_SECRET_KEY);
        sign = prop.getProperty(SMSConstant.SMS_SIGN_KEY);
    }

    public static String getProduct() {
        return product;
    }

    public static String getDomain() {
        return domain;
    }

    public static String getSign() {
        return sign;
    }

    /**
     * 获得初始化的iClientProfile
     * @return 短信客户端
     */
    public static IClientProfile getIClientProfile() {
        if (iClientProfile == null) {
            synchronized (SMSConfig.class) {
                if (null == iClientProfile) {
                    iClientProfile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
                }
            }
        }
        return iClientProfile;
    }
}
