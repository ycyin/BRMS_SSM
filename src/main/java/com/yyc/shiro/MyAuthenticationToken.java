package com.yyc.shiro;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yyc.entity.UserInfo;
import com.yyc.utils.JwtUtils;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * 自定义AuthenticationToken,必须在自定义realm中复写supports方法，使之能识别自定义token
 * 用于JWT与Shiro集成
 * 需要保存用户的信息（这里只保存用户名和密码），
 *        而用户的信息又由用户登录时生成的jwtToken转换而来。
 * @author yyc
 */
public class MyAuthenticationToken implements AuthenticationToken {

    private UserInfo user;

    public MyAuthenticationToken(String jwtToken) {
        DecodedJWT decodedJWT = JwtUtils.decodedJWT(jwtToken);
        String issuer = decodedJWT.getIssuer();
        this.user = JSONObject.parseObject(issuer,UserInfo.class);
    }

    @Override
    public Object getPrincipal() {
        return user.getUsername();
    }

    @Override
    public Object getCredentials() {
        return user.getPassword();
    }


}

