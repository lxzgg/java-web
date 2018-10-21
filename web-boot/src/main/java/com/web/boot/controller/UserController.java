package com.web.boot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class UserController {

    @GetMapping("go")
    public HashMap<String, String> getUser() {
        HashMap<String, String> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "b");
        return map;
    }

}
