package cn.gokong.www.gokongmain.aspect;

import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.exception.LogAspectException;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 说明:日志切点
 *
 * @author ikook
 * @CreateDate 2018/9/7 10:35
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@Aspect     //作用是把当前类标识为一个切面供容器读取
@Component
@Order(3)
public class LogAspectAop extends BaseExecutionAop {

    /**
     * 声明前置通知，相当于BeforeAdvice的功能
     * @param point
     * @throws GrilException
     */
    @Before("aspectController()")
    public void doBefore(JoinPoint point) throws GrilException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        StaticLog.info("URL            ：{}",request.getRequestURI());
        StaticLog.info("HTTP_METHOD    ：{}",request.getMethod());
        StaticLog.info("IP             ：{}",request.getRemoteAddr());
        StaticLog.info("CLASS_METHOD   ：{}", point.getSignature().getDeclaringType() + "." + point.getSignature().getName());
        if (point.getArgs()[0]!=null){
            StaticLog.info("PARAM          ：{}", JSONUtil.toJsonStr(point.getArgs()[0]));
        }


    }

    /**
     * 声明例外通知，相当于ThrowsAdvice
     */
    @AfterThrowing(value = "aspectController()",throwing = "ex")
    public void doAfterThrowing(LogAspectException ex){
        StaticLog.error("Throwing---------------------{}---{}",this.getClass().getName(),ex.getMessage());
        throw new GrilException(ex.getMessage());
    }

    /**
     * 声明最终通知，不管是抛出异常或者正常退出都会执行
     * @param point
     */
    @After("aspectController()")
    public void doAfter(JoinPoint point){
        //System.out.println("方法最后执行.....");
    }

    /**
     * 声明后置通知，相当于AfterReturningAdvice，方法退出时执行
     * @param object
     */
    @AfterReturning(returning = "object", pointcut = "aspectController()")
    public void doAfterReturning(Object object){
        // 处理完请求，返回内容
        if (ObjectUtil.isNotNull(object)){
            StaticLog.info("return         ：{}",JSONUtil.toJsonStr(object));
        }
    }

    /**
     * 环绕增强，相当于MethodInterceptor
     * @param pjp
     * @return
     */
    /*@Around("aspectController()")
    public Object doAround(ProceedingJoinPoint pjp) {
        try {
            //访问目标方法的参数
            Object[] args = pjp.getArgs();
            if (args != null && args.length > 0) {
                for (int i = 0; i < args.length; i++) {
                    Map<String, Object> map = BeanUtil.beanToMap(args[i]);
                    RequestData requestData = BeanUtil.mapToBean(map, RequestData.class, true);
                    System.out.println(requestData.toString());
                    args[i] = requestData;
                }
            }
            //执行目标方法
            Object o =  pjp.proceed(args);
            return o;
        } catch (Throwable e) {
            e.printStackTrace();
            return null;
        }
    }*/
}
