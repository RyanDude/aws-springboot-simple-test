package com.example.authserver.annotations.AOP;

import com.example.authserver.annotations.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogAop {
    // logger
    private static final Logger LOGGER = LoggerFactory.getLogger(LogAop.class);
    @Pointcut("@annotation(com.example.authserver.annotations.Log)")
    public void pointcut(){System.err.println("pointcut function ?");}
    @Before("pointcut() && @annotation(log)")
    public void action(Log log){
        System.err.println("run before the annotation");
        LOGGER.info(log.value());
    }
    @Around("pointcut()")
    public Object aro(ProceedingJoinPoint point)throws Throwable{
        Object ret = point.proceed();
        LOGGER.info(ret.toString());
        return ret;
    }
    /*
    * Both of them are ok
    * */
}
