package com.yosoro.ssm.controller;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;
import com.yosoro.ssm.domain.SysLog;
import com.yosoro.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component
@Aspect
public class LogAop {

    @Autowired
    private HttpServletRequest request;

    private Date startTime; // 开始时间
    private Class clazz; // 访问的类
    private Method method; // 访问的方法
    private String sysLogStr = "syslog";

    @Autowired
    private ISysLogService sysLogService;

    // 前置通知 获取开始时间，执行的类 执行的方法
    @Before("execution(* com.yosoro.ssm.controller.*.*(..))")
    public void doBefore(JoinPoint jp) throws Exception {
        startTime = new Date(); // 当前时间开始访问的时间
        clazz = jp.getTarget().getClass(); // 具体要访问的lei
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();//获取访问的方法的参数

        //获取具体执行的方法的Method对象
        if (args == null || args.length == 0) {
            method = clazz.getMethod(methodName); //只能获取无参数的方法
        } else {
            Class[] classArgs = new Class[args.length];
            for (int i = 0; i < args.length; i++) {
                classArgs[i] = args[i].getClass();
            }
            clazz.getMethod(methodName, classArgs);
        }

    }

    @After("execution(* com.yosoro.ssm.controller.*.*(..))")
    public void doAfter(JoinPoint jp) throws Exception {

        // 获取访问时长
        long executionTime = new Date().getTime() - startTime.getTime();
        if (clazz != null && method != null && clazz != LogAop.class) {
        // 获取URL
        String url = request.getRequestURL().toString();
        // 获取当前访问ip
        String ip = request.getRemoteAddr();

        // 获取当前操作对象
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User)context.getAuthentication().getPrincipal();
//        User user = (User)request.getSession().getAttribute("SPRING_SECURITY_CONTEXT");
        String username = user.getUsername();


        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(executionTime);
        sysLog.setIp(ip);
        sysLog.setMethod("[类名]" + clazz.getName() + "[方法]" + method.getName());
        sysLog.setUrl(url);
        sysLog.setUsername(username);
        sysLog.setVisitTime(startTime);
        if (!sysLogStr.contains(url)) {
            sysLogService.save(sysLog);
        }



        }
    }
}
