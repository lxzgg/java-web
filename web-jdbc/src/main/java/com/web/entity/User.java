package com.web.entity;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Getter
@Setter
public class User {
    private int id;
    @NotBlank(message = "name空的")
    private String name;
    private String username;
    private String password;
    private String mobile;
    private String email;
    private Boolean status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}