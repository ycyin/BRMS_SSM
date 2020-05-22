package com.yyc.filter;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yyc.shiro.MyAuthenticationToken;
import com.yyc.utils.JwtUtils;
import com.yyc.vo.RespMsg;
import com.yyc.vo.ResultEnum;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;


/**
 * @Description: 登录认证和鉴权过滤器.使用JWT+Shiro
 * 登录认证：未登录/登录过期则拦截并向前端发送json数据提示，注意：不可以在shiro配置中设置shiroFilterFactoryBean.setLoginUrl("/nologin")，因为这种setLoginUrl方式只能做后端跳转，在前后端分离情况下，前端会报302/301强制重定向警告。
 * 鉴权：完成用户登录认证后就要进行鉴权，需要调用getSubject(request, response).login(token)才会执行鉴权操作
 * @author: yyc
 */
public class ShiroAuthcFilter extends FormAuthenticationFilter {
    private final static Logger logger = LoggerFactory.getLogger(ShiroAuthcFilter.class);

    /**
     * 在访问controller（url）前判断是否登录、是否有权限；
     * 没有登录返回json，鉴权则交给shiro处理，没有权限会抛出UnauthorizedException或AuthorizationException
     * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        logger.info("登录认证授权处理：正在执行ShiroAuthcFilter-onAccessDenied方法进行认证鉴权操作-------------------");
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        // 获取请求header中的Token
        String token = httpServletRequest.getHeader("Token");

        if (null != token) {
            // 解码Token信息
            DecodedJWT jwt = JwtUtils.decodedJWT(token);
            // 解码成功
            if (null != jwt) {
                // 从解码后的Token中获取用户信息
                String userInfoJson = Optional.ofNullable(jwt.getIssuer()).orElse("FAILED");
                // 验证jwt的有效性
                DecodedJWT verifyJwt = JwtUtils.verifyJWT(token, userInfoJson);
                // 验证jwtToken有效
                if (null != verifyJwt) {
                    logger.info("jwt认证成功，当前Token信息：{},用户信息：{}", token, userInfoJson);
                    // 鉴权去
                    return executeLogin(request, response);
                }else{
                    logger.info("jwt认证失败，当前Token信息：{},用户信息：{}", token, userInfoJson);
                    httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
                    httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
                    RespMsg resultData = new RespMsg(ResultEnum.LOGIN_TOKEN_EXPIRES);
                    httpServletResponse.getWriter().write(JSONObject.toJSON(resultData).toString());
                    return false;
                }
            }
        }
        logger.info("jwt认证失败，当前Token信息：{}", token);
        httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        RespMsg resultData = new RespMsg(ResultEnum.UNLOGIN);
        httpServletResponse.getWriter().write(JSONObject.toJSON(resultData).toString());
        return false;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        // 获取请求header中的Token
        String jwtToken = httpServletRequest.getHeader("Token");
        MyAuthenticationToken token = new MyAuthenticationToken(jwtToken);
        try {
            getSubject(request, response).login(token);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}