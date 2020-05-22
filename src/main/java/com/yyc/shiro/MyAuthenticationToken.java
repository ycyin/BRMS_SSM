package com.yyc.shiro;

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

    /**
     *  用户信息
     */
    private final UserInfo user;

    /**
     *
     * @param jwtToken JWT TOKEN值
     */
    public MyAuthenticationToken(String jwtToken) {
        // 解码TOKEN
        DecodedJWT decodedjwt = JwtUtils.decodedJWT(jwtToken);
        // 取出JWT TOKEN中的json字符串
        String issuer = decodedjwt.getIssuer();
        // 将JSON字符串转换为实体对象，方便shiro的登录认证
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

