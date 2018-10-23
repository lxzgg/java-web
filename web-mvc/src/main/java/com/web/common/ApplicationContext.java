package com.web.common;

import org.springframework.stereotype.Component;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;

@Component
public class ApplicationContext implements ServletContextAware {
    @Override
    public void setServletContext(ServletContext servletContext) {
        servletContext.setAttribute("ctx", "666");
    }
}
