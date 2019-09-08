package com.yyc.filter;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.yyc.utils.JWTUtils;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

/**
 * Token验证过滤器
 */
//@Component   //在启动类加了@ServletComponentScan无需加这个了(springboot)
@Order(4)//设置优先级加载
//@ServletComponentScan  //加载启动类上了(springboot)
@WebFilter(urlPatterns = "/*",filterName = "TokenCheckFilter")
public class TokenCheckFilter implements Filter {
 
    /**
     * 初始化
     * @param filterConfig FilterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }
 
    /**
     * 过滤
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpServletRequest req = (HttpServletRequest) request;
        String requestURI = req.getRequestURI();
        System.out.println(requestURI);

        String token = Optional.ofNullable(req.getHeader("token")).orElse("NOT FOUNT");
        DecodedJWT decodedJWT = JWTUtils.verifyJWT(token,"yyc");

        JSONObject jsonObject = new JSONObject();
        if (decodedJWT == null) { //token无效
            if (requestURI.contains("/login")||requestURI.endsWith("/SSM_war/")){ //登录操作不过滤
                chain.doFilter(req, resp);
            }else{
                PrintWriter writer = resp.getWriter();
                resp.setStatus(401);
                jsonObject.put("isAuthorization",false);
                writer.write(jsonObject.toString());
                return;
            }

        }else{
//            PrintWriter writer = resp.getWriter();
//            jsonObject.put("isAuthorization",true);
//            writer.write(jsonObject.toString());
            chain.doFilter(req, resp);
        }


        System.out.println("to access control check token");
    }
 
    /**
     * 销毁
     */
    @Override
    public void destroy() {
    }
}