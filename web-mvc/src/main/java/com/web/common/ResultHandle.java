package com.web.common;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 返回值处理
 */
@ControllerAdvice("com.web.controller")
public class ResultHandle implements ResponseBodyAdvice {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        System.out.println(returnType);
        System.out.println(converterType);
        System.out.println("处理返回值");
        return true;
    }

    @Override
    public Map beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        HttpServletResponse resp = ((ServletServerHttpResponse) response).getServletResponse();
        HttpServletRequest req = ((ServletServerHttpRequest) request).getServletRequest();
        long time = (long) req.getAttribute("X-Response-Time");
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("message", "success");
        map.put("data", body);
        resp.setHeader("X-Response-Time", String.valueOf(System.currentTimeMillis() - time + "ms"));
        return map;
    }
}
