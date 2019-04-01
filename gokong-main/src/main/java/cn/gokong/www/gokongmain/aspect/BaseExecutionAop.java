package cn.gokong.www.gokongmain.aspect;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.json.JSONUtil;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 说明:公用切点类
 *
 * @author ikook
 * @CreateDate 2018/9/6 18:27
 * @Email ：ideacoding@163.com
 * @Version 1.0
 **/
public class BaseExecutionAop {

    /**
     * Controller切点定义
     */
    @Pointcut("execution(* cn.gokong.www.gokongmain.web.*.*(..))")
    public void aspectController(){

    }

    /**
     * 将内容输出到浏览器
     *
     * @param obj 输出内容
     */
    public void writeContent(Object obj) throws IOException {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.reset();
        response.setCharacterEncoding(CharsetUtil.UTF_8);
        response.setHeader("Content-Type", "application/json");
        //IoUtil.write(response.getOutputStream(), CharsetUtil.UTF_8,true,obj);

        PrintWriter out = response.getWriter();
        out.println(JSONUtil.toJsonStr(obj));
        out.flush();
        out.close();
    }
}
