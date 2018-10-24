package com.web.mapper2;

import com.web.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper2 {
    List<User> findAll();

    Boolean insert(User user);
}
