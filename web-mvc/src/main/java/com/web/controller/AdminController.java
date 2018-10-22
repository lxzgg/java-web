package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
}
