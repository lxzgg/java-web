package com.web.dao;

import com.web.entity.User;

import java.sql.*;
import java.util.ArrayList;

class UserDao {

    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/nest?useSSL=false&serverTimezone=Asia/Shanghai", "root", "sa");
    }

    void getUser() throws SQLException {
        var list = new ArrayList<User>();

        var sql = "SELECT * FROM auth_user";
        var statement = getConnection().createStatement();
        var rs = statement.executeQuery(sql);
        while (rs.next()) {
            var user = new User();
            user.setId(rs.getInt("id"));
            user.setName(rs.getString("name"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setMobile(rs.getString("mobile"));
            user.setEmail(rs.getString("email"));
            user.setStatus(rs.getBoolean("status"));
            user.setCreatedAt(rs.getTimestamp("createdAt"));
            user.setUpdatedAt(rs.getTimestamp("updatedAt"));
            list.add(user);
        }
        System.out.println(list);
    }

    void addUser() throws SQLException {
        var user = new User();
        user.setName(String.valueOf(Math.random()));
        user.setUsername(String.valueOf(Math.random()));
        user.setPassword(String.valueOf(Math.random()));
        user.setMobile(String.valueOf(Math.random()));
        user.setEmail(String.valueOf(Math.random()));
        user.setStatus(true);
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));

        var sql = "INSERT INTO auth_user VALUE(null,?,?,?,?,?,?,?,?)";
        var statement = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, user.getName());
        statement.setString(2, user.getUsername());
        statement.setString(3, user.getPassword());
        statement.setString(4, user.getMobile());
        statement.setString(5, user.getEmail());
        statement.setBoolean(6, user.getStatus());
        statement.setTimestamp(7, user.getCreatedAt());
        statement.setTimestamp(8, user.getUpdatedAt());
        statement.executeUpdate();

        var rs = statement.getGeneratedKeys();
        var keyValue = -1;
        if (rs.next()) keyValue = rs.getInt(1);
        // 返回主键ID
        System.out.println(keyValue);
    }

}
