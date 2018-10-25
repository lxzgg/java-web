package com.web.service.impl;

import com.web.common.ErrorException;
import com.web.entity.User;
import com.web.mapper2.UserMapper2;
import com.web.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
@Transactional("tx2")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserMapper2 userMapper;

    public List<User> getAdmin() {
        return userMapper.findAll();
    }

    public User insert() {
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
            throw new ErrorException(155, "手动");
        }

        return user;
    }

}
