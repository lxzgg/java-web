package com.web.entity;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class User {
    private int id;
    private String name;
    private String username;
    private String password;
    private String mobile;
    private String email;
    private Boolean status;
    private Timestamp createdAt;
    private Timestamp updatedAt;
}