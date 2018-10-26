package com.web.service;

import com.web.entity.User;

import java.util.List;

public interface AdminService {
    List<User> getAdmin();

    User insert();
}
