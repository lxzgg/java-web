package com.web.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.junit.jupiter.api.*;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.IOException;
import java.io.StringWriter;
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
    void obj2xml() throws XMLStreamException, IOException {
        XmlMapper mapper = new XmlMapper();

        var time1 = System.currentTimeMillis();

        StringWriter stringWriter = new StringWriter();
        XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
        XMLStreamWriter sw = xmlOutputFactory.createXMLStreamWriter(stringWriter);

        sw.writeStartDocument();
        sw.writeStartElement("root");

        mapper.writeValue(sw, "a");
        sw.writeEndElement();
        sw.writeEndDocument();

        stringWriter.toString();

        var time2 = System.currentTimeMillis();

        System.out.println(time2 - time1);
    }

    @Test
    void dom() {
        Document document = DocumentHelper.createDocument();
        Element root = document.addElement("root");

        var time1 = System.currentTimeMillis();

        root.addElement("author").addCDATA("James Strachan");
        root.addElement("author").addCDATA("Bob McWhirter");
        root.addElement("author1").addCDATA("Bob McWhirter");
        root.addElement("author2").addCDATA("Bob McWhirter");
        root.addElement("author3").addCDATA("Bob McWhirter");
        root.addElement("author4").addCDATA("Bob McWhirter");
        root.addElement("author5").addCDATA("Bob McWhirter");
        root.addElement("author6").addCDATA("Bob McWhirter");
        document.getRootElement().asXML();

        var time2 = System.currentTimeMillis();

        System.out.println(time2 - time1);
    }
}