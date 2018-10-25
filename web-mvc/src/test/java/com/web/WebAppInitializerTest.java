package com.web;

import com.web.config.RootConfig;
import com.web.config.WebConfig;
import com.web.service.impl.AdminServiceImpl;
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
    private AdminServiceImpl adminServiceImpl;

    @Test
    void runTest() {
        adminServiceImpl.getAdmin();
    }
}