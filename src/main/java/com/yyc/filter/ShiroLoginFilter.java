package com.yyc.filter;
import com.alibaba.fastjson.JSONObject;

import com.yyc.shiro.RespShiroResult;
import com.yyc.shiro.ShiroResultEnum;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.core.annotation.Order;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @ClassName:  ShiroLoginFilter   
 * @Description:TODO(登录认证过滤器，未登录则拦截并向前端发送json数据提示未登录
 * 不可以在shiro配置中设置shiroFilterFactoryBean.setLoginUrl("/nologin");
 * 因为这种setLoginUrl方式只能做后端跳转。
 * 在前后端分离情况下，前端会报302/301强制重定向警告。
 * )   
 * @author: yinyicao
 * @date:   2019年6月12日 下午4:12:16   
 *     
 * @Copyright: 2019 www.yinyicao.work. All rights reserved. 
 *
 */

public class ShiroLoginFilter extends FormAuthenticationFilter {


	/**
            * 在访问controller（url）前判断是否登录，返回json。
     * @param request
     * @param response
     * @return true-继续往下执行，false-该filter过滤器已经处理，不继续执行其他过滤器
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws IOException {
    	System.err.println("-----------------ShiroLoginFilter---onAccessDenied-------------------");
    	HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json");
        RespShiroResult resultData = new RespShiroResult(ShiroResultEnum.UNLOGIN);
        httpServletResponse.getWriter().write(JSONObject.toJSON(resultData).toString());
        return false;
    }

}