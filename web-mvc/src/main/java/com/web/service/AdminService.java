package com.web.service;

import com.web.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AdminService {
    List<User> getAdmin();

    User insert();
}
