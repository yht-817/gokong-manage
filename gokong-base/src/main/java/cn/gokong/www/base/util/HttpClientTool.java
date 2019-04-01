package cn.gokong.www.base.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * HttpClient 工具类
 */
public class HttpClientTool {

    private static Logger logger = LoggerFactory.getLogger(HttpClientTool.class);

    public static SSLContext createIgnoreVerifySSL() throws Exception {
        SSLContext sc = SSLContext.getInstance("SSLv3");
        // 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
        X509TrustManager trustManager = new X509TrustManager() {
            public void checkClientTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                                           String paramString) throws CertificateException {
            }

            public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                                           String paramString) throws CertificateException {
            }

            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        sc.init(null, new TrustManager[]{trustManager}, null);
        return sc;
    }

    /**
     * 进行post 请求
     *
     * @param method 传递的url参数
     * @param body   传递信息结构
     */
    public static Map<String, Object> requestPost(String method, Map<String, String> body) {
        CloseableHttpResponse response = null;
        CloseableHttpClient httpclient = null;
        try {
            String url = "https://pay.gokong.cn:8043/" + method;
            int code = 200;
            // 采用绕过验证的方式处理https请求
            SSLContext sslcontext = createIgnoreVerifySSL();
            // 设置协议http和https对应的处理socket链接工厂的对象
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslcontext)).build();
            PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            HttpClients.custom().setConnectionManager(connManager);
            // 创建自定义的httpClient对象
            httpclient = HttpClients.custom().setConnectionManager(connManager).build();
            // 实例化post方法
            HttpPost httpPost = new HttpPost(url);
            // 指定报文头Content-type、User-Agent
            httpPost.setHeader("Content-type", "application/x-www-form-urlencoded");
            httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; rv:6.0.2) Gecko/20100101 Firefox/6.0.2");
            // 处理参数
            List<NameValuePair> nvps = new ArrayList<>();
            Set<String> keySet = body.keySet();
            for (String key : keySet) {
                nvps.add(new BasicNameValuePair(key, body.get(key)));
            }
            Map<String, Object> content_map = null;
            // 提交的参数
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(nvps, "UTF-8");
            // 将参数给post方法
            httpPost.setEntity(uefEntity);
            // 执行post方法
            response = httpclient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == 200) {
                String content = EntityUtils.toString(response.getEntity(), "utf-8");
                content_map = (Map<String, Object>) JSON.parse(content);
            } else {
                return null;
            }
            response.close();
            httpclient.close();
            return content_map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 进行get 请求
     *
     * @param url 传递的url参数
     */
    public static Map<String, Object> requestGet(String url) {
        CloseableHttpClient httpclient = null;
        try {
            int code = 200;
            SSLContext sslcontext = createIgnoreVerifySSL();
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", PlainConnectionSocketFactory.INSTANCE)
                    .register("https", new SSLConnectionSocketFactory(sslcontext)).build();
            PoolingHttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            HttpClients.custom().setConnectionManager(connManager);
            // 创建HttpClient对象
            httpclient = HttpClients.custom().setConnectionManager(connManager).build();
            // 实例化GET方法
            HttpGet httpGet = new HttpGet(url);
            // 执行GET方法
            HttpResponse response = httpclient.execute(httpGet);
            Map<String, Object> content_map = null;
            if (response.getStatusLine().getStatusCode() == 200) {
                String strResult = EntityUtils.toString(response.getEntity());
                content_map = (Map<String, Object>) JSON.parse(strResult.substring(11, strResult.length() - 1));
                httpclient.close();
            } else {
                return null;
            }
            return content_map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}