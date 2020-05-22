package com.yyc.service;

import com.yyc.vo.RespMsg;
import com.yyc.vo.request.UserVo;
import org.junit.Test;
import org.junit.runner.RunWith;
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


    @Resource
    private UserService userService;

    @Test
    public void test1(){
        RespMsg allUser = this.userService.getAllUser();
    }

    @Test
    public void test2(){
        UserVo u = new UserVo();
        u.setUsername("test");
        u.setNickname("测试");
        u.setPassword("ewqrewqrqwereqwq");
        u.setRole(1);
        u.setState(1);
        userService.insertUser(u);
    }
}
