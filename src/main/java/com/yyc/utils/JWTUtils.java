package com.yyc.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import java.util.Date;

public class JWTUtils {
    public static String createJWT(long expiresTime,String userId) {

        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            token = JWT.create()
                    .withIssuer(userId) //设置用户
                    .withExpiresAt(new Date(System.currentTimeMillis()+ (expiresTime * 1000))) //设置过期时间
                    .sign(algorithm)
                    ;
        } catch (JWTCreationException exception){
            //Invalid Signing configuration / Couldn't convert Claims.
        }

        return token;
    }

    public static DecodedJWT verifyJWT(String token,String userId) {
        DecodedJWT jwt = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256("secret");
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(userId)
                    .acceptExpiresAt(2) //接受过期2秒的token
                    .build(); //Reusable verifier instance
             jwt = verifier.verify(token);
        } catch (JWTVerificationException exception){
            //Invalid signature/claims
        }
        return jwt;
    }

}
