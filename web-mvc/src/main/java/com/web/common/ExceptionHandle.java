package com.web.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 异常处理
 */
@RestControllerAdvice
public class ExceptionHandle {

    private static final Logger log = LoggerFactory.getLogger(ExceptionHandle.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ErrorException.class)
    public Map<String, Object> errorException(ErrorException e) {
        log.error(String.valueOf(400), e);
        Map<String, Object> map = new HashMap<>();
        map.put("code", e.getCode());
        map.put("message", e.getMessage());
        return map;
    }

    @ResponseStatus()
    @ExceptionHandler()
    public Map<String, Object> exception(Exception e) {
        log.error(String.valueOf(500), e);
        Map<String, Object> map = new HashMap<>();
        map.put("code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        map.put("message", e.getMessage());
        return map;
    }

}
