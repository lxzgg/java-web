package com.web.controller;

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
import java.util.Map;

@Controller
public class AdminController {

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
    public Map<String, Object> getUser() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", "2");
//        if (map.size() > 0) {
//            throw new ErrorException(ErrorException.ErrorEnum.parameter_error);
//        }
        return map;
    }

    @GetMapping("/404")
    public String notFound() {
        return "404";
    }
}
