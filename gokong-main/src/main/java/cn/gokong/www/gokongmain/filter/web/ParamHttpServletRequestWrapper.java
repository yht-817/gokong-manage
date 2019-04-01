package cn.gokong.www.gokongmain.filter.web;

import cn.gokong.www.base.crypto.AesBase64Util;
import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.util.JSONUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.ServletUtil;
import cn.hutool.json.JSONObject;
import cn.ikeek.www.emoji.EmojiUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 类说明:WEB过滤处理类
 *
 * @author ideacoding
 * create 2018-05-09 14:13
 * Email ：ideacoding@163.com
 **/
public class ParamHttpServletRequestWrapper extends HttpServletRequestWrapper {

    /**
     * 是否开启多种参数传输方式（true：同时接收APP和WEB端参数    false：只接收APP端参数）
     */
    private boolean enableOnParam = false;

    /**
     * 是否开启参数解密
     */
    private boolean enableDecrypt = false;

    /**
     * 封装,需要解密的字段
     */
    protected List<String> decryptField = null;

    /**
     * 构造函数
     * @param request
     * @param enableOnParam     是否开启多参数传输
     * @param enableDecrypt     是否开启参数解密
     * @param decryptField      解密字段
     */
    public ParamHttpServletRequestWrapper(HttpServletRequest request, boolean enableOnParam, boolean enableDecrypt, List<String> decryptField) {
        super(request);
        this.decryptField = decryptField;
        this.enableOnParam = enableOnParam;
        this.enableDecrypt = enableDecrypt;
        this.decryptField = decryptField;
    }



    /**
     * 数组参数过滤
     * @param parameter 过滤参数
     * @return
     */
    @Override
    public String[] getParameterValues(String parameter) {
        String[] results = super.getParameterValues(parameter);
        if (results == null)
            return null;
        int count = results.length;
        String[] trimResults = new String[count];
        for (int i = 0; i < count; i++) {
            trimResults[i] = EmojiUtil.emojiToStr(results[i].trim());

        }
        return trimResults;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        ServletInputStream servletInputStream = null;
        String jsonBodyStr = IoUtil.read(super.getInputStream(), "UTF-8");
        if (StrUtil.isNotBlank(jsonBodyStr)&&!"{}".equals(jsonBodyStr)){
            JSONObject jsonBody = new JSONObject(jsonBodyStr);
            decryptField.forEach(field->{
                Object o = jsonBody.get(field);
                if (!ObjectUtil.isNull(o) &&!"{}".equals(o.toString())){
                    jsonBody.put(field, filterFieldString(String.valueOf(jsonBody.get(field))));
                }
            });
            byte[] body = jsonBody.toString().getBytes();
            servletInputStream = getServletInputStream(body);
        }else {
            if (enableOnParam){
                //将URL参数转换为body参数
                servletInputStream =  getServletInputStream(paramToBody());
            }else {
                servletInputStream = getServletInputStream(jsonBodyStr.getBytes());
            }
        }
        return servletInputStream;
    }

    /**
     * 解密value
     * @param rawValue  待解密字符
     * @return
     */
    protected JSONObject filterFieldString(String rawValue){
        String tmpStr = rawValue;
        //是否开启参数解密
        if (this.enableDecrypt) {
            tmpStr = AesBase64Util.decryptAES(rawValue);
        }
        //转换表情符号
        tmpStr = EmojiUtil.emojiToStr(tmpStr);
        return new JSONObject(tmpStr);
    }

    /**
     * 将修改后的值转换为ServletInputStream
     * @param body  修改后的body[]值
     * @return  返回ServletInputStream对象
     * @throws IOException  统一抛出异常
     */
    private ServletInputStream getServletInputStream(byte[] body) throws IOException {
        return new ServletInputStream() {
            private int lastIndexRetrieved = -1;
            private ReadListener readListener = null;
            @Override
            public boolean isFinished() {
                return (lastIndexRetrieved == body.length-1);
            }

            @Override
            public boolean isReady() {
                // This implementation will never block
                // We also never need to call the readListener from this method, as this method will never return false
                return isFinished();
            }

            @Override
            public void setReadListener(ReadListener listener) {
                this.readListener = listener;
                if (!isFinished()) {
                    try {
                        readListener.onDataAvailable();
                    } catch (IOException e) {
                        readListener.onError(e);
                    }
                } else {
                    try {
                        readListener.onAllDataRead();
                    } catch (IOException e) {
                        readListener.onError(e);
                    }
                }
            }

            @Override
            public int read() throws IOException {
                int i;
                if (!isFinished()) {
                    i = body[lastIndexRetrieved+1];
                    lastIndexRetrieved++;
                    if (isFinished() && (readListener != null)) {
                        try {
                            readListener.onAllDataRead();
                        } catch (IOException ex) {
                            readListener.onError(ex);
                            throw ex;
                        }
                    }
                    return i;
                } else {
                    return -1;
                }
                //return IoUtil.toStream(jsonBody.toString(), "utf-8").read();
            }
        };
    }

    /**
     * 获取URL参数，封装成入参JSON字符 转为byte
     * @return  返回封装后的byte[]
     */
    private byte[] paramToBody() {
        Map<String, String> paramMap = ServletUtil.getParamMap(getRequest());
        RequestData<Map> requestData = new RequestData<>();
        requestData.setData(paramMap);
        return EmojiUtil.emojiToStr(JSONUtil.bean2json(requestData)).getBytes();
    }
}
