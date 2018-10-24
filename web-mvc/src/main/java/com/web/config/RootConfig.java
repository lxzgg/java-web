package com.web.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "com.web", excludeFilters = {@ComponentScan.Filter(EnableWebMvc.class), @ComponentScan.Filter(Controller.class)})
public class RootConfig {
}
