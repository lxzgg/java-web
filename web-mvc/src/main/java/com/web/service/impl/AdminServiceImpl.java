package com.web.service.impl;

import com.web.common.ErrorException;
import com.web.entity.User;
import com.web.mapper.UserMapper;
import com.web.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserMapper userMapper;

    public List<User> getAdmin() {
        return userMapper.getAdmin();
    }

    public User insert() {
        userMapper.getAdmin();
        userMapper.getAdmin();
        userMapper.getAdmin();
        userMapper.getAdmin();
        var user = new User();
        user.setName(String.valueOf(Math.random()));
        user.setUsername(String.valueOf(Math.random()));
        user.setPassword(String.valueOf(Math.random()));
        user.setMobile(String.valueOf(Math.random()));
        user.setEmail(String.valueOf(Math.random()));
        user.setStatus(true);
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        Boolean insert = userMapper.insert(user);
        if (true) {
            throw new ErrorException(10001, "手动异常");
        }
        log.debug(String.valueOf(user));
        return user;
    }

}
