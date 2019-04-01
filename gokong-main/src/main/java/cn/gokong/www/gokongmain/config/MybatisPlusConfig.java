package cn.gokong.www.gokongmain.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 说明:MybatisPlus配置
 *
 * @author ideac
 * @CreateDate 2018/9/10 23:23
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@EnableTransactionManagement
@Configuration
@MapperScan("cn.gokong.www.gokongmain.dao.mapper")
public class MybatisPlusConfig {

    /**
     * SQL执行效率插件
     *
     * 注意！该插件只用于开发环境，不建议生产环境使用。。。
     */
    @Bean
    @Profile({"dev","test"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
//        <!--SQL是否格式化 默认false-->
        performanceInterceptor.setFormat(false);
//        <!-- SQL 执行性能分析，开发环境使用，线上不推荐。 maxTime 指的是 sql 最大执行时长 -->
        performanceInterceptor.setMaxTime(2000);
        return performanceInterceptor;
    }

    /**
     * mybatis-plus分页插件
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
