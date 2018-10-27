package com.web.mapper;

import com.web.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    List<User> getAdmin();

    Boolean insert(User user);
}
