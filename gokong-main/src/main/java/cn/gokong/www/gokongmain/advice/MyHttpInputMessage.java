package cn.gokong.www.gokongmain.advice;
import cn.gokong.www.base.annotation.Field;
import cn.gokong.www.base.annotation.FieldReq;
import cn.gokong.www.base.crypto.AesBase64Util;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;

import java.io.IOException;
import java.io.InputStream;

/**
 * 类说明:请求消息处理类
 *
 * @author ideacoding
 * create 2018-04-03 12:17
 * Email ：ideacoding@163.com
 **/
public class MyHttpInputMessage implements HttpInputMessage {

    /**
     * 定义空的json对象
     */
    private static final String jsonObjNull = "{}";

    //需要解密的字段
    private Field[] fields = {};
    //包含项
    private String[] includes = {};
    //是否解密
    private boolean decode = true;

    private HttpHeaders headers;

    private InputStream body;

    public MyHttpInputMessage(HttpInputMessage inputMessage, MethodParameter methodParameter) throws Exception {
        includes = new String[]{};

        decode = true;

        fields = new Field[]{};


        if(methodParameter.getMethod().isAnnotationPresent(FieldReq.class)){
            //获取注解配置的包含和去除字段
            FieldReq fieldReq = methodParameter.getMethodAnnotation(FieldReq.class);
            //包含项
            includes = fieldReq.includes();
            //是否解密
            decode = fieldReq.decode();
            //需要解密的字段
            fields = fieldReq.fields();
        }

        //解密
        if (decode){
            //获取body内容并转为字符
            String jsonBodyStr = IoUtil.read(inputMessage.getBody(), "UTF-8");
            if (!StrUtil.isBlank(jsonBodyStr)&&!jsonObjNull.equals(String.valueOf(jsonBodyStr))){
                if (fields.length>0){
                    if (fields[0]== Field.ALL){
                        this.body = IoUtil.toStream(AesBase64Util.decryptAES(jsonBodyStr),"utf-8");
                    }else {
                        JSONObject jsonObject = JSONUtil.parseObj(jsonBodyStr);
                        for (int i = 0;i<fields.length;i++){
                            if (fields[i]==Field.DATA){
                                jsonObject.put(String.valueOf(fields[i]),JSONUtil.parseObj(AesBase64Util.decryptAES(String.valueOf(jsonObject.get(String.valueOf(fields[i]))))));
                            }else {
                                jsonObject.put(String.valueOf(fields[i]),AesBase64Util.decryptAES(String.valueOf(jsonObject.get(String.valueOf(fields[i])))));
                            }
                        }
                        this.body = IoUtil.toStream(jsonObject.toString(), "utf-8");
                    }
                }else {
                    this.body = IoUtil.toStream(jsonBodyStr,"utf-8");
                }
            }else {
                this.body = IoUtil.toStream(jsonBodyStr,"utf-8");
            }
        }else {
            this.body = inputMessage.getBody();
        }

        this.headers = inputMessage.getHeaders();
    }

    @Override
    public InputStream getBody() throws IOException {
        return body;
    }

    @Override
    public HttpHeaders getHeaders() {
        return headers;
    }
}
