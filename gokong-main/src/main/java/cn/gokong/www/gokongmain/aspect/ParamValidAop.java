package cn.gokong.www.gokongmain.aspect;

import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.base.util.JSONUtil;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.exception.ParamValidException;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.log.StaticLog;
import cn.ikeek.www.emoji.EmojiUtil;
import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 说明:参数校验切面
 *
 * @author ikook
 * @CreateDate 2018/9/7 10:35
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@Aspect
@Component
@Order(5)
public class ParamValidAop extends BaseExecutionAop {

    /**
     * 环绕增强，相当于MethodInterceptor(注意环绕不能try异常 只能抛出异常)
     *
     * @param pjp
     * @return
     */
    @Around("aspectController()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        //处理输入参数
        Object[] args = pjp.getArgs();
        processInputArg(args);
        //执行目标方法
        Object o = pjp.proceed();

        //处理响应参数
        o = processOutPutObj(o);
        return o;
    }

    /**
     * 声明前置通知，相当于BeforeAdvice的功能
     * @param point
     * @throws IOException
     */
    /*@Before("aspectController()")
    public void paramValid(JoinPoint point){
        Object[] paramObj = point.getArgs();
        for (int i = 0; i < paramObj.length; i++) {
            if (paramObj[i] instanceof BindingResult) {
                BindingResult result = (BindingResult) paramObj[i];
                ResponseData errorMap = this.validRequestParams(result);
                if (errorMap != null) {
                    StaticLog.error("AOP检测到参数不规范:{}",errorMap.getData());
                    throw new ParamValidException(String.valueOf(errorMap.getData()));
                }
            }
        }
    }*/

    /**
     * 声明例外通知，相当于ThrowsAdvice
     *
     * @param ex
     */
    @AfterThrowing(value = "aspectController()", throwing = "ex")
    public void doAfterThrowing(ParamValidException ex) {
        StaticLog.error("Throwing---------------------{}---{}", this.getClass().getName(), ex.getMessage());
        throw new GrilException(ex.getMessage());
    }

    /**
     * 处理返回对象
     */
    private Object processOutPutObj(Object obj) {
        if (ObjectUtil.isNotNull(obj)) {
            if(obj instanceof ResponseData){
                Object data = ((ResponseData) obj).getData();
                if (ObjectUtil.isNotNull(data)){
                    return JSONObject.parse(EmojiUtil.strToEmoji(JSONUtil.object2json(obj)));
                }
            }
        }

        return obj;
    }

    /**
     * 处理输入参数
     *
     * @param args 入参列表
     */
    private void processInputArg(Object[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof BindingResult) {
                BindingResult result = (BindingResult) args[i];
                ResponseData errorMap = this.validRequestParams(result);
                if (errorMap != null) {
                    StaticLog.error("AOP检测到参数不规范:{}", errorMap.getData());
                    throw new ParamValidException(String.valueOf(errorMap.getData()));
                }
            }
        }
    }

    /**
     * 校验参数
     *
     * @param result
     * @return
     */
    private ResponseData validRequestParams(BindingResult result) {
        if (result.hasErrors()) {
            List<ObjectError> allErrors = result.getAllErrors();
            List<String> lists = new ArrayList<>();
            for (ObjectError objectError : allErrors) {
                lists.add(objectError.getDefaultMessage());
            }
            ResponseData responseData = new ResponseData();
            responseData.setData(lists);
            return responseData;
        }
        return null;
    }
}
