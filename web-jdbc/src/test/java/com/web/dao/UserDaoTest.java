package com.web.dao;

import org.junit.jupiter.api.*;

import java.sql.SQLException;

class UserDaoTest {
    @BeforeAll
    static void beforeAll() {
        System.out.println("每个类运行前调用，并且只调用一次,必须静态");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("每个方法测试前调用");
    }

    @AfterEach
    void afterEach() {
        System.out.println("每个方法测试后调用");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("每个类运行后调用，并且只调用一次,必须静态");
    }

    @Test
    void getUser() throws SQLException {
        var star = System.currentTimeMillis();
        var userDao = new UserDao();
        userDao.getUser();
        System.out.println(System.currentTimeMillis() - star);
    }

    @Test
    void addUser() throws SQLException {
        var star = System.currentTimeMillis();
        var userDao = new UserDao();
        userDao.addUser();
        System.out.println(System.currentTimeMillis() - star);
    }

}