package com.web.common;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Aspect
@Component
public class ResultAop {

    @Around("execution(public * com.web.controller.*.*(..))")
    public Object resultAround(ProceedingJoinPoint point) throws Throwable {
        Object proceed = point.proceed();
        HashMap<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("message", "success");
        map.put("data", proceed);
        return map;
    }

}
