package com.web;

import com.web.config.RootConfig;
import com.web.config.WebConfig;
import com.web.service.AdminService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class, WebConfig.class})
class WebAppInitializerTest {

    @Autowired
    private AdminService adminService;

    @Test
    void runTest() {
        adminService.getAdmin();
    }
}