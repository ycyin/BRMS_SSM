package com.yyc.service;

import com.yyc.utils.MD5Encryption;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @program: SSM
 * @description: 测试MD5加密
 * @author: yyc
 * @create: 2020-02-18 15:32
 **/

public class TestMD5Encryption {
    @Test
    public void Test(){
        String userName = "user1";
        String pass = "a123456";
        /*
         实际盐值：盐值=用户名+后缀。用户名作为盐值前缀，直接对用户名加密作为盐值的后缀
         存储盐值：数据库直接存储盐值后缀，读取出来再加前缀
         */
        String saveSalt = MD5Encryption.Encryption(null,userName);
        String realSalt = userName + saveSalt;
        /*
         密码：进一步将盐值和实际密码进行MD5加密
         */
        String password = MD5Encryption.Encryption(realSalt,pass);
        System.out.println("用户名："+userName+" 密码："+pass+"\n"+"存储盐值："+saveSalt+"\n实际盐值:"+realSalt+"\n"+"密码："+password);
    }
}
