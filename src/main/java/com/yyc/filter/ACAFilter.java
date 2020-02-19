package com.yyc.filter;

import org.springframework.core.annotation.Order;
 
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 
/**
 * 跨域的设置问题
 * 参考：https://blog.csdn.net/qq_39313596/article/details/82592809
 * @author tonywu
 * @version v1.0.0
 */
//
////@Component   //在启动类加了@ServletComponentScan无需加这个了(springboot)
//@Order(5)//设置优先级加载
////@ServletComponentScan  //加载启动类上了(springboot)
//@WebFilter(urlPatterns = "/*",filterName = "ACAFilter")
public class ACAFilter implements Filter {
 
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
        String origin = req.getHeader("Origin");
        if(origin == null) {
            origin = req.getHeader("Referer");
        }
        resp.setHeader("Access-Control-Allow-Origin", origin);//这里不能写*，*代表接受所有域名访问，如写*则下面一行代码无效。谨记
        resp.setHeader("Access-Control-Allow-Credentials", "true");// 携带Cookie
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Max-Age", "3600");
        //允许前端发送的请求头类型，比如 axios.defaults.headers.common['Token'] =  getToken(); 这里就写上Token
        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type, Accept, Origin,Token");

        if (req.getMethod().equals("OPTIONS")) {
            resp.setStatus(200);
            return;
        }

        chain.doFilter(req, resp);

        System.out.println("to access control allow origin");
    }
 
    /**
     * 销毁
     */
    @Override
    public void destroy() {
    }
}