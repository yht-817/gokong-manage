package cn.gokong.www.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 说明:数据解密及过滤
 *
 * @author ikook
 * @CreateDate 2018/9/7 10:35
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FieldReq {
    /**
     * 需要包含的字段
     * @return 返回包含的字段
     */
    String[] includes() default {};

    /**
     * 数据是否需要解密
     * @return 如果需要解密 返回true 否则返回false
     */
    boolean decode() default true;

    /**
     * 需要解密的字段
     * @return 返回解密字段数组
     */
    Field[] fields() default {Field.ALL};
}
