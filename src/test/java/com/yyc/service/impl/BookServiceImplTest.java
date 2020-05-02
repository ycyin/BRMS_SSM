package com.yyc.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:spring-mvc.xml","classpath:spring-mybatis.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class BookServiceImplTest {

    @Autowired
    private BookServiceImpl bookService;

    @Test
    public void addBookByCamera() {
        bookService.addBookByCamera(null);
    }
}