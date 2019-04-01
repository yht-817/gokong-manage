package cn.gokong.www.gokongmain.aspect;

import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.exception.TimeConsumingException;
import cn.hutool.log.StaticLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 说明:接口耗时切面
 *
 * @author ikook
 * @CreateDate 2018/9/7 13:22
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

@Aspect
@Component
@Order(1)
public class TimeConsumingAOP extends BaseExecutionAop{


    /**
     * 环绕增强，相当于MethodInterceptor
     * @param pjp
     * @return
     */
    @Around("aspectController()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        StaticLog.info("");
        StaticLog.info("");
        long startTime = System.currentTimeMillis();
        //执行目标方法
        Object o = pjp.proceed();
        StaticLog.info("TIME CONSUMING ：{}ms",System.currentTimeMillis()-startTime);
        return o;
    }

    /**
     * 声明例外通知，相当于ThrowsAdvice
     * @param ex
     */
    @AfterThrowing(value = "aspectController()",throwing = "ex")
    public void doAfterThrowing(TimeConsumingException ex){
        StaticLog.error("Throwing---------------------{}---{}",this.getClass().getName(),ex.getMessage());
        throw new GrilException(ex.getMessage());
    }
}
