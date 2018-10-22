package com.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AdminController {

    @GetMapping("index")
    public ModelAndView getAdmin() {
        ModelAndView view = new ModelAndView("redirect:index.jsp");
        view.addObject("a", "a");
        return view;
    }

    @GetMapping("go")
    @ResponseBody
    public Map<String, Object> getUser() {
        Map<String, Object> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", "2");
        return map;
    }
}
