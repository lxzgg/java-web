package com.web;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

@ComponentScan(basePackages = "com.web", excludeFilters = {@ComponentScan.Filter(Controller.class)})
@Configuration
public class RootConfig {
}
