package cn.gokong.www.gokongmain.aspect;

import cn.gokong.www.base.entity.ResponseData;
import cn.gokong.www.base.jedis.KeysUtil;
import cn.gokong.www.gokongmain.exception.GrilException;
import cn.gokong.www.gokongmain.exception.TokenAspectException;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.log.StaticLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 说明:Token认证切面
 *
 * @author ikook
 * @CreateDate 2018/9/7 13:56
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/

//@Aspect
//@Component
//@Order(4)
public class TokenAspectAop extends BaseExecutionAop {

    protected List<String> urlExclusion = Arrays.asList(
            "/sendSecurityCode",    //发送验证码
            "/checkSecurityCode",   //校验验证码
            "/securityCodeLogin",   //验证码登录
            "/homepage",            //查询首页信息
            "/queryMobileVersion",  //查询版本信息
            "/getMobilePrefixs",    //查询电话区号列表
            "/cattlepeople"         //查询更多牛人
    );

    /**
     * 环绕增强，相当于MethodInterceptor
     * @param pjp
     * @return
     */
    @Around("aspectController()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        Object[] args = pjp.getArgs();
        Map<String, Object> map = BeanUtil.beanToMap(args[0]);

        Set<String> strings = map.keySet();

        strings.forEach(s -> {
            if (s.equals("token")){
                String token = String.valueOf(map.get("token"));

                String userNo = String.valueOf(map.get("userNo"));

                if (StrUtil.isNotEmpty(token)&&StrUtil.isNotEmpty(userNo)) {
                    String serverToken = KeysUtil.get(userNo);
                    if (!String.valueOf(token).equals(serverToken)) {
                        throw new TokenAspectException("登录失效");
                    }
                }
            }
        });

        /*System.out.println(map.toString()+"=============================");

        RequestData requestData = BeanUtil.mapToBean(map, RequestData.class, true);*/

        /*HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String path = request.getRequestURI();


        if (urlExclusion.size()>0){
            boolean isok = false;
            for (int i = 0; i < urlExclusion.size(); i++) {
                if (path.contains(urlExclusion.get(i))){
                    isok = true;
                }
            }
            if (!isok){
                String token = KeysUtil.get(requestData.getUserNo());
                *//*if (ObjectUtil.isNull(token) || !JwtUtil.checkJWT(token) || !token.equals(requestData.getToken())) {
                    throw new TokenAspectException("登录失效");
                }*//*
                if (ObjectUtil.isNotNull(token) && !token.equals(requestData.getToken())) {
                    throw new TokenAspectException("登录失效");
                }
            }
        }else {
            String token = KeysUtil.get(requestData.getUserNo());
            if (ObjectUtil.isNull(token) || !!JwtUtil.checkJWT(token) || !token.equals(requestData.getToken())) {
                throw new TokenAspectException("登录失效");
            }
        }*/
        //执行目标方法
        Object o = pjp.proceed();
        return o;
    }

    /**
     * 声明前置通知，相当于BeforeAdvice的功能
     * @param point
     * @throws GrilException
     */
    /*@Before("aspectController()")
    public void doBefore(JoinPoint point){
        Object[] args = point.getArgs();
        Map<String, Object> map = BeanUtil.beanToMap(args[0]);
        RequestData requestData = BeanUtil.mapToBean(map, RequestData.class, true);

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String path = request.getRequestURI();


        if (urlExclusion.size()>0){
            boolean isok = false;
            for (int i = 0; i < urlExclusion.size(); i++) {
                if (path.contains(urlExclusion.get(i))){
                    isok = true;
                }
            }
            if (!isok){
                if (DataMemory.tokenMap.size()==0){
                    throw new GrilException("未登录");
                }
                String token = DataMemory.tokenMap.get(requestData.getUserNo());
                if (ObjectUtil.isNull(token) || !JwtUtil.checkJWT(token) || !token.equals(requestData.getToken())) {
                    throw new GrilException("登录失效");
                }
            }
        }else {
            if (DataMemory.tokenMap.size()==0){
                throw new GrilException("未登录");
            }

            String token = DataMemory.tokenMap.get(requestData.getUserNo());

            if (ObjectUtil.isNull(token) || !!JwtUtil.checkJWT(token) || !token.equals(requestData.getToken())) {
                throw new GrilException("登录失效");
            }
        }
    }*/

    /**
     * 声明例外通知，相当于ThrowsAdvice
     * @param ex
     */
    @AfterThrowing(value = "aspectController()",throwing = "ex")
    public void doAfterThrowing(TokenAspectException ex) throws IOException {
        StaticLog.error("Throwing---------------------{}---{}",this.getClass().getName(),ex.getMessage());
        ResponseData responseData = new ResponseData();
        responseData.setMessage(ex.getMessage());
        responseData.setCode(401);
        writeContent(responseData);
    }
}
