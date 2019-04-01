package cn.gokong.www.base.crypto;




import cn.gokong.www.base.crypto.config.AesConfig;
import cn.hutool.core.codec.Base64Decoder;
import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 类说明:AES加Base64
 *
 * @author yangzhuang
 * create 2018-01-11 14:51
 * Email ：ideacoding@163.com
 **/
public class AesBase64Util {
    private static SecretKeySpec secretKeySpec = AesConfig.getSecretKeySpec();
    private static IvParameterSpec ivParameterSpec = AesConfig.getIvParameterSpec();
    /**
     * AES+Base64加密
     * @param content 加密内容
     * @return  返回加密后的内容
     */
    public static String encryptAES(String content){
        if(content==null){
            return null;
        }
        try {
            byte[] byteContent = StrUtil.bytes(content, CharsetUtil.UTF_8);
            // 指定加密的算法、工作模式和填充方式
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] encryptedBytes = cipher.doFinal(byteContent);
            // 同样对加密后数据进行 base64 编码
            return Base64Encoder.encode(encryptedBytes);
            //return new String(encryptedBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * AES+Base64解密
     * @param content
     * @return
     */
    public static String decryptAES(String content){
        if(content==null){
            return null;
        }
        try {
            // base64 解码
            byte[] encryptedBytes = Base64Decoder.decode(content);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
            byte[] result = cipher.doFinal(encryptedBytes);
            return StrUtil.str(result,CharsetUtil.UTF_8);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args){
        String str = "{\n" +
                "    \"userName\": \"打滴滴\",\n" +
                "    \"password\": \"ddd\"\n" +
                "  }";
        String en = encryptAES(str);
        System.out.println("加密后:"+en);

        String de = decryptAES(en);
        System.out.println("解密后:"+de);

        /*String en = encrypt("nihao");
        System.out.println("加密后:"+en);
        String des = decrypt(en);
        System.out.println("解密后:"+des);*/
    }
}
