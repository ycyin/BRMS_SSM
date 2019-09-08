package com.yyc.service;


import com.auth0.jwt.interfaces.DecodedJWT;
import com.yyc.utils.JWTUtils;

import java.util.Date;

public class Test {


    @org.junit.Test
    public void test() throws InterruptedException {
        //生成token,有效时间5秒
        String token = JWTUtils.createJWT(5,"yyc");
        System.out.println(token);

        verify(token);
    }

    public void verify(String token) throws InterruptedException{
        int timer = 1;
        System.out.println("马上认证：");
        DecodedJWT decodedJWT = JWTUtils.verifyJWT(token,"yyc");
        System.out.println(decodedJWT.getToken());
        System.out.println(decodedJWT.getIssuer());
        System.out.println(decodedJWT.getIssuer() != null ? "token有效":"token无效");

        for (int i = 0; i < timer * 10; i++) {
            System.out.println("等待"+timer+"秒后重新认证：");
            Thread.sleep(timer * 1000);
//        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJ5eWMiLCJleHAiOjE1NjE1NTQ2MTF9.RE5XiC4g_6_rLnfkq0Y7niDlKqxeudHDkF50FLKekXs";
            DecodedJWT decodedJWT2 = JWTUtils.verifyJWT(token,"yyc");
            System.out.println(decodedJWT2 != null ? "token有效":"token无效");
        }

    }
}
