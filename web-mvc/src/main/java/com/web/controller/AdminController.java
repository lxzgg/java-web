package com.web.controller;

import com.web.entity.User;
import com.web.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {

    private static final Logger log = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    private AdminService adminService;

    @GetMapping("home")
    public ModelAndView getAdmin() {
        Map<String, String> map = new HashMap<>();
        map.put("a", "a");
        map.put("b", "b");
        return new ModelAndView("home", map);
    }

    @PostMapping("file")
    @ResponseBody
    public Map getFile(@RequestParam("file") MultipartFile[] files) throws IOException {
        for (MultipartFile file : files) {
            if (file.isEmpty()) continue;
            file.transferTo(new File("C:/temp/" + Math.random()));
        }
        return new HashMap<>();
    }

    @GetMapping("/go")
    @ResponseBody
    public List<User> getUser() {
        log.info("info...");
        log.debug("debug...");
        log.warn("warn...");
        log.error("哈哈...");
        return adminService.getAdmin();
    }


    @GetMapping("/insert")
    @ResponseBody
    public User insert() {
        return adminService.insert();
    }

    @GetMapping("/404")
    public String notFound() {
        return "404";
    }
}
