package com.web.controller;

import com.web.common.ErrorException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminController {

    @GetMapping("index")
    public ModelAndView getAdmin() {
        ModelAndView view = new ModelAndView("home");
        view.addObject("a", "666");
        return view;
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
