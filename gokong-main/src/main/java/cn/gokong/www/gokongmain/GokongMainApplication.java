package cn.gokong.www.gokongmain;

import cn.hutool.log.StaticLog;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@MapperScan("cn.gokong.www.gokongmain.dao")//将项目中对应的mapper类的路径加进来就可以了
public class GokongMainApplication {
    public static void main(String[] args) {
        /**
         * 隐藏banner启动方式
         */
        SpringApplication springApplication = new SpringApplication(GokongMainApplication.class);
        //设置banner的模式为控制台显示
        springApplication.setBannerMode(Banner.Mode.CONSOLE);
        //启动springboot应用程序
        springApplication.run(args);

        //原启动方式
        /*SpringApplication.run(GokongMainApplication.class, args);*/
        StaticLog.error("========================启动完毕========================");
    }

    /**
     * 文件上传配置
     * @return
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        //单个文件最大
        factory.setMaxFileSize("10240KB"); //KB,MB
        /// 设置总上传数据总大小
        factory.setMaxRequestSize("102400KB");
        factory.setLocation("/");
        return factory.createMultipartConfig();
    }
}
