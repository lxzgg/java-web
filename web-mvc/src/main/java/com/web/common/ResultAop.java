package com.web.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ResultAop {

    @Around("execution(public * com.web.controller.*.*(..))")
    public Object resultAround(ProceedingJoinPoint point) throws Throwable {
        System.out.println("切面");
        return point.proceed();
    }

}
