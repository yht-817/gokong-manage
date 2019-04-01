package cn.gokong.www.gokongmain.aspect;

import cn.gokong.www.base.entity.RequestData;
import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.gokongmain.exception.IpAstrictException;
import cn.gokong.www.gokongmain.exception.ParamValidException;
import cn.hutool.json.JSONUtil;
import cn.hutool.log.StaticLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 说明:配置AOP
 *
 * @author ikook
 * @CreateDate 2018/9/19 10:48
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
//@Aspect
//@Component
public class SpringBootAop extends BaseExecutionAop {

    /**
     * 全局MAP存储IP地址
     */
    private Map<String, List<Long>> accessDatas = new ConcurrentHashMap<>();

    /**
     * 限制时间(妙)
     */
    private Long astrictTime = 60L;

    /**
     * 限制最高次数
     */
    private int astrictMaxCount = 50;


    @Around("aspectController()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        //IP限流
        ipAstrict(request);

        //处理输入参数
        processInputArg(pjp.getArgs());

        //打印参数
        StaticLog.error("URL            ：{}",request.getRequestURI());
        StaticLog.error("HTTP_METHOD    ：{}",request.getMethod());
        StaticLog.error("IP             ：{}",request.getRemoteAddr());
        StaticLog.error("CLASS_METHOD   ：{}",pjp.getSignature().getDeclaringType()+"."+pjp.getSignature().getName());
        StaticLog.error("PARAM          ：{}", JSONUtil.toJsonStr(pjp.getArgs()[0]));

        try {//obj之前可以写目标方法执行前的逻辑

            Object obj = pjp.proceed();//调用执行目标方法

            //处理响应参数
            processOutPutObj(obj);


            StaticLog.error("return         ：{}",JSONUtil.toJsonStr(obj));
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();

        }

        return null;
    }

    /**
     * IP限流
     */
    private void ipAstrict(HttpServletRequest request) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(request.getRemoteAddr()).append(request.getRequestURI());
        String key = stringBuffer.toString();
        List<Long> list = accessDatas.get(key);
        if (list==null || list.size()==0){
            List<Long> lt = new ArrayList<>();
            lt.add(System.currentTimeMillis());
            accessDatas.put(key,lt);
            //StaticLog.info("当前KEY[{}] 首次访问",key);
        }else {
            removeExpirePoints(key);
            //判断是否超出限制
            if (list.size()>=astrictMaxCount){
                //当前IP已经超出访问限制
                throw new IpAstrictException("用户KEY[" + key + "]  超过了限定的访问次数[" + list.size() + "] 请[" + astrictTime/60 + "]分钟后再试!");
            }else {
                list.add(System.currentTimeMillis());
                //StaticLog.info("当前KEY[{}] 访问次数[{}]",key,list.size());
            }
        }
    }

    /**
     * 移除过期的请求记录
     * @param key    请求KEY
     */
    private void removeExpirePoints(String key){
        List<Long> list = accessDatas.get(key);
        Iterator<Long> iterator = list.iterator();
        while (iterator.hasNext()){
            Long next = iterator.next();
            if (astrictTime.compareTo((System.currentTimeMillis()-next)/1000) < 0){
                //记录已过期
                iterator.remove();
                //StaticLog.info("当前KEY[{}] 记录已过期",key);
            }else {
                //StaticLog.info("当前KEY[{}] 记录未过期",key);
            }
        }
    }

    /**
     * 处理返回对象
     */
    private void processOutPutObj(Object obj) {
        System.out.println("OBJ 原本为：" + obj.toString());
        if (obj instanceof RequestData) {
            ResponseData responseData = (ResponseData) obj;
            responseData.setMessage("哈哈，我把值修改了" + responseData.getMessage());
        }
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
                    StaticLog.error("AOP检测到参数不规范:{}",errorMap.getData());
                    throw new ParamValidException(String.valueOf(errorMap.getData()));
                }
            }
        }
    }

    /**
     * 校验参数
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
