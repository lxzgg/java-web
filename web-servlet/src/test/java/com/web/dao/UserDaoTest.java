package com.web.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.web.entity.User;
import org.dom4j.DocumentHelper;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.SortedMap;
import java.util.StringJoiner;
import java.util.TreeMap;

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

    @Test
    void lombok() {
        var user = new User();
        user.setName(String.valueOf(Math.random()));
        user.setUsername(String.valueOf(Math.random()));
        user.setPassword(String.valueOf(Math.random()));
        user.setMobile(String.valueOf(Math.random()));
        user.setEmail(String.valueOf(Math.random()));
        user.setStatus(true);
        user.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        user.setUpdatedAt(new Timestamp(System.currentTimeMillis()));
        System.out.println(user);
    }

    @Test
    void stringbuilder() {
        long beginTime1 = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100000; i++) {
            sb.append(i);
        }
        System.out.println(System.currentTimeMillis() - beginTime1);
    }

    @Test
    void stringJoin() {
        long beginTime = System.currentTimeMillis();
        StringJoiner sj = new StringJoiner("");
        for (int i = 0; i < 100000; i++) {
            sj.add(String.valueOf(i));
        }
        System.out.println(System.currentTimeMillis() - beginTime);
    }

    @Test
    void join() {
        StringJoiner s = new StringJoiner("&");
        s.add("a=1").add("b=2").add("c=3");
        System.out.println(s);
    }

    @Test
    void map() throws JsonProcessingException {
        var objectMapper = new ObjectMapper();
        objectMapper.writeValueAsString("");

        long beginTime = System.currentTimeMillis();
        SortedMap<String, String> map = new TreeMap<>();
        map.put("c", "1");
        map.put("2", "1");
        map.put("a", "1");
        map.put("bb", "1");
        map.put("b", "1");
        map.put("b1", "2");

        String s = objectMapper.writeValueAsString(map);
        System.out.println(System.currentTimeMillis() - beginTime);
        System.out.println(s);

        map.forEach((s1, s2) -> {
            System.out.println(s1);
            System.out.println(s2);
        });

    }
}