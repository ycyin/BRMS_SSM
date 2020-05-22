package com.yyc.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Date;


/**************************************
 * @author 尹以操 E-mail:34782655@qq.com
 * @version 创建/修改时间：
 * 类说明: JWT工具类
 ***************************************
 */
public class JwtUtils {
    private final static Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    /**
     * 创建JWT
     */
    public static String createJWT(long expiresTime,String userId) {

        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            token = JWT.create()
                    // 设置用户
                    .withIssuer(userId)
                    // 设置过期时间
                    .withExpiresAt(new Date(System.currentTimeMillis()+ (expiresTime * 1000)))
                    .sign(algorithm)
                    ;
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
            logger.error("创建JWT出错：{}",exception.getMessage());
        }

        return token;
    }

    /**
     * 验证JWT
     */
    public static DecodedJWT verifyJWT(String token,String userInfo) {
        DecodedJWT jwt = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(userInfo)
                    //接受过期2秒的token
                    .acceptExpiresAt(2)
                    .build(); //Reusable verifier instance
             jwt = verifier.verify(token);
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
            logger.warn("jwt验证异常，当前异常信息：{},Token信息：{},用户信息：{},",exception.getMessage(),token,userInfo);
        }
        return jwt;
    }

    /**
     * JWT解码
     */
    public static DecodedJWT decodedJWT(String token){
        DecodedJWT jwt = null;
        try {
            jwt = JWT.decode(token);
        } catch (JWTDecodeException exception){
            //Invalid token
            logger.error("解码JWT出错：{}",exception.getMessage());
        }
        return jwt;
    }


}