package cn.gokong.www.gokongmain.config;

import cn.gokong.www.gokongmain.filter.ParamFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

/**
 * web 配置类
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午5:03:32
 */
@Configuration
public class WebConfig{

    /**
     * 参数过滤器
     */
    @Bean
    public FilterRegistrationBean paramFilterRegistration() {
        ParamFilter paramFilter = new ParamFilter();
        //不解密的路径
        paramFilter.setUrlExclusion(Arrays.asList("/asyn"));
        //是否开启解密
        paramFilter.setEnableDecrypt(true);
        //解密字段
        paramFilter.setDecryptField(Arrays.asList("data"));
        //开启swagger参入传入
        paramFilter.setEnableOnParam(true);
        FilterRegistrationBean registration = new FilterRegistrationBean(paramFilter);
        registration.addUrlPatterns("/*");
        return registration;
    }
}

