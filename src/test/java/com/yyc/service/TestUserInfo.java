package com.yyc.service;

import com.yyc.vo.RespMsg;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @program: SSM
 * @description:
 * @author: yyc
 * @create: 2020-03-11 15:48
 **/

@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestUserInfo {

    private static Logger logger = LoggerFactory.getLogger(TestUserInfo.class);

    @Resource
    private UserService userService;

    @Test
    public void test1(){
        RespMsg allUser = this.userService.getALLUser();
        logger.info(allUser.getMsg()+"--->"+allUser.getData());
    }
}
