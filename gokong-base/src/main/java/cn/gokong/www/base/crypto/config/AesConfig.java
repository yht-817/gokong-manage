package cn.gokong.www.base.crypto.config;



import cn.hutool.core.lang.Singleton;
import cn.hutool.setting.dialect.Props;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Properties;

/**
 * 说明:AES加密配置
 *
 * @author Mick
 * CreateDate 2018/7/18/018 1:11
 * Email ：ideacoding@163.com
 * Version 1.0
 **/
public class AesConfig {
    //key(密钥)
    private static String key;
    //向量
    private static String vi;

    static {
        Properties props = Props.getProp("crypot-aes.properties");
        key = props.getProperty("key");
        vi = props.getProperty("vi");
    }

    /**
     * 获得初始化的SecretKeySpec
     * @return
     */
    public static SecretKeySpec getSecretKeySpec() {
        return Singleton.get(SecretKeySpec.class, key.getBytes(), "AES");
    }

    /**
     * 获得初始化的IvParameterSpec
     * @return
     */
    public static IvParameterSpec getIvParameterSpec() {
        return Singleton.get(IvParameterSpec.class, vi.getBytes());
    }
}
