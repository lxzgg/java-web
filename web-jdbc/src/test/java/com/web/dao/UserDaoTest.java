package com.web.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.dom4j.DocumentHelper;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

class UserDaoTest {

    @BeforeAll
    static void beforeAll() {
        // 每个类运行前调用，并且只调用一次,必须静态
    }

    @BeforeEach
    void beforeEach() {
        // 每个方法测试前调用
    }

    @AfterEach
    void afterEach() {
        // 每个方法测试后调用
    }

    @AfterAll
    static void afterAll() {
        // 每个类运行后调用，并且只调用一次,必须静态
    }

    @Test
    void getUser() throws SQLException, JsonProcessingException {
        var userDao = new UserDao();
        List list = userDao.getUser();
        var mapper = new XmlMapper();
        String s1 = mapper.writeValueAsString(list);
        System.out.println(s1);
    }

    @Test
    void addUser() throws SQLException {
        var star = System.currentTimeMillis();
        var userDao = new UserDao();
        userDao.addUser();
        System.out.println(System.currentTimeMillis() - star);
    }

    @Test
    void domObj() throws JsonProcessingException {
        var time1 = System.currentTimeMillis();
        var mapper = new XmlMapper();
        var node = mapper.createObjectNode();
        node.put("a", 1);
        node.put("b", "");
        var time2 = System.currentTimeMillis();
        System.out.println(node);
        System.out.println(mapper.writeValueAsString(node));
        System.out.println(time2 - time1);
    }

    @Test
    void domXml() {
        var time1 = System.currentTimeMillis();
        var document = DocumentHelper.createDocument();
        var root = document.addElement("root");
        root.addElement("author").addCDATA(String.valueOf(Math.random()));
        root.addElement("author").addCDATA(String.valueOf(Math.random()));
        root.addElement("author1").addCDATA(String.valueOf(Math.random()));
        root.addElement("author2").addCDATA(String.valueOf(Math.random()));
        root.addElement("author3").addCDATA(String.valueOf(Math.random()));
        root.addElement("author4").addCDATA(String.valueOf(Math.random()));
        root.addElement("author5").addCDATA(String.valueOf(Math.random()));
        root.addElement("author6").addCDATA(String.valueOf(Math.random()));
        var time2 = System.currentTimeMillis();
        System.out.println(document.asXML());
        System.out.println(time2 - time1);
    }

}