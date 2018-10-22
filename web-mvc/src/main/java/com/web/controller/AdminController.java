package com.web.controller;

import com.web.utils.ValidationUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class AdminController {

    @GetMapping("go")
    public Map<Object, Object> getAdmin() {
        Map<Object, Object> map = new HashMap<>();
        map.put("a", 1);
        map.put("b", "2");
        ValidationUtil.validator(map);
        return map;
    }

}
