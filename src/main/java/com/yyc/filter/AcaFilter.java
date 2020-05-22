package com.yyc.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
 

/**
 * 跨域的设置问题
 * 参考：https://blog.csdn.net/qq_39313596/article/details/82592809
 *
 * @ClassName:  AcaFilter
 * @Description: 跨域过滤器
 * @author: yyc
 *
 * 常用注解
 * /@Component  //在启动类加了@ServletComponentScan无需加这个了(springboot)
 * /@Order(5)  //设置优先级加载
 * /@ServletComponentScan  //加载启动类上了(springboot)
 * /@WebFilter(urlPatterns = "/*",filterName = "ACAFilter")
 */
public class AcaFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(AcaFilter.class);

    /**
     * 初始化
     * @param filterConfig FilterConfig
     */
    @Override
    public void init(FilterConfig filterConfig) {
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
        // 这里不能写*，*代表接受所有域名访问，如写*则下面一行代码Allow-Credentials无效。谨记
        resp.setHeader("Access-Control-Allow-Origin", origin);
        // 允许携带Cookie(前端withCredentials = true带Cookie时必须设置此项）
        // resp.setHeader("Access-Control-Allow-Credentials", "true")
        resp.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, OPTIONS, DELETE");
        resp.setHeader("Access-Control-Max-Age", "3600");
        // 允许前端发送的请求头类型，比如 axios.defaults.headers.common['Token'] =  getToken(); 这里就写上Token
        resp.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type, Accept, Origin,Token");
        if ("OPTIONS".equals(req.getMethod())) {
            resp.setStatus(200);
            return;
        }
        chain.doFilter(req, resp);
        logger.info("跨域请求处理：{}","To access control allow origin");
    }
 
    /**
     * 销毁
     */
    @Override
    public void destroy() {
    }
}