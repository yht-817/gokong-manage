package cn.gokong.www.gokongmain.aspect;

import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.exception.IpAstrictException;
import cn.hutool.log.StaticLog;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 说明:IP限制切点
 *
 * @author ikook
 * @CreateDate 2018/9/6 18:27
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
@Aspect
@Component
@Order(2)
public class IpAstrictAop extends BaseExecutionAop {

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

    @Before("aspectController()")
    public void doBefore() throws GrilException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(request.getRemoteAddr()).append(request.getRequestURI());
        //频控检测
        requestFrequencyCheck(stringBuffer.toString());

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
     * 访问频率检查
     * @param key    key
     */
    private void requestFrequencyCheck(String key){
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
     * 声明例外通知，相当于ThrowsAdvice
     */
    @AfterThrowing(value = "aspectController()",throwing = "ex")
    public void doAfterThrowing(IpAstrictException ex){
        StaticLog.error("Throwing---------------------{}---{}",this.getClass().getName(),ex.getMessage());
        throw new GrilException(ex.getMessage());
    }
}
