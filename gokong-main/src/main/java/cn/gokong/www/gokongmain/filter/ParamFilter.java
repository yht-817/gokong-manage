package cn.gokong.www.gokongmain.filter;


import cn.gokong.www.gokongmain.filter.web.ParamHttpServletRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * 类说明:参数过滤器
 *
 * @author ideacoding
 * create 2018-05-09 14:09
 * Email ：ideacoding@163.com
 **/
public class ParamFilter implements Filter {

    /**
     * 封装，不需要过滤URL路径列表
     */
    protected List<String> urlExclusion = null;

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

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        String path = httpServletRequest.getServletPath();

        if (!path.contains("swagger") && !path.contains("api-doc")){
            if (urlExclusion!=null){
                boolean isok = false;
                for (int i = 0; i < urlExclusion.size(); i++) {
                    if (path.contains(urlExclusion.get(i))) {
                        isok = true;
                        chain.doFilter(request, response);
                    }
                }
                if (!isok){
                    chain.doFilter(new ParamHttpServletRequestWrapper((HttpServletRequest) request, enableOnParam, enableDecrypt, decryptField), response);
                }
            }else {
                chain.doFilter(new ParamHttpServletRequestWrapper((HttpServletRequest) request, enableOnParam, enableDecrypt, decryptField),response);

            }

        }else {
            chain.doFilter(request,response);
        }

    }

    @Override
    public void destroy() {

    }

    public List<String> getUrlExclusion() {
        return urlExclusion;
    }

    public void setUrlExclusion(List<String> urlExclusion) {
        this.urlExclusion = urlExclusion;
    }

    public boolean isEnableOnParam() {
        return enableOnParam;
    }

    public void setEnableOnParam(boolean enableOnParam) {
        this.enableOnParam = enableOnParam;
    }

    public boolean isEnableDecrypt() {
        return enableDecrypt;
    }

    public void setEnableDecrypt(boolean enableDecrypt) {
        this.enableDecrypt = enableDecrypt;
    }

    public List<String> getDecryptField() {
        return decryptField;
    }

    public void setDecryptField(List<String> decryptField) {
        this.decryptField = decryptField;
    }
}