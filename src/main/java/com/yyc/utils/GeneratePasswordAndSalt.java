package com.yyc.utils;

import com.yyc.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.lang.model.element.NestingKind;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: SSM
 * @description: 生成数据库存储的密码和盐值
 * @author: yyc
 * @create: 2020-03-13 14:58
 **/
public class GeneratePasswordAndSalt {
    private final static Logger logger = LoggerFactory.getLogger(GeneratePasswordAndSalt.class);

    public static Map<String,String> generate(String username,String password){
        /*
         实际盐值：盐值=用户名+后缀。用户名作为盐值前缀，直接对用户名加密作为盐值的后缀
         存储盐值：数据库直接存储盐值后缀，读取出来再加前缀
         */
        String saveSalt = MD5Encryption.Encryption(null,username);
        String realSalt = username + saveSalt;
        /*
         密码：进一步将盐值和实际密码进行MD5加密
         */
        String pass = MD5Encryption.Encryption(realSalt,password);
        logger.info("用户名："+username+" 密码："+pass+"\n"+"存储盐值："+saveSalt+"\n实际盐值:"+realSalt+"\n"+"密码："+password);

        Map<String, String> res = new HashMap();
        res.put("password",pass);
        res.put("salt",saveSalt);
        return res;
    }
}
