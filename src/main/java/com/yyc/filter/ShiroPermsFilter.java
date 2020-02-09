//package com.yyc.filter;
//
//import java.io.IOException;
//
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletResponse;
//
//import com.yyc.shiro.RespShiroResult;
//import com.yyc.shiro.ShiroResultEnum;
//import org.apache.shiro.subject.Subject;
//import org.apache.shiro.web.filter.authz.AuthorizationFilter;
//import org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter;
//
//import com.alibaba.fastjson.JSONObject;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
///**
// *重要：不知道为啥自定义的权限过滤器无法回调，此方法作废
// * @ClassName:  ShiroPermsFilter
// * @Description:TODO(和ShiroLoginFilter一样的作用，只是ShiroLoginFilter是登录认证，而ShiroPermsFilter是权限认证)
// * @author: yinyicao
// * @date:   2019年6月12日 下午4:21:14
// *
// * @Copyright: 2019 www.yinyicao.work. All rights reserved.
// *
// */
//
//public class ShiroPermsFilter extends AuthorizationFilter{
//    @Override
//    public boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws IOException {
//        Subject subject = getSubject(request, response);
//        String[] perms = (String[]) mappedValue;
//        boolean isPermitted = true;
//        if (perms != null && perms.length > 0) {
//            if (perms.length == 1) {
//                if (!subject.isPermitted(perms[0])) {
//                    isPermitted = false;
//                }
//            } else {
//                if (!subject.isPermittedAll(perms)) {
//                    isPermitted = false;
//                }
//            }
//        }
//        return isPermitted;
//    }
//
//    /**
//     * shiro认证perms资源失败后回调方法
//     * @param servletRequest
//     * @param servletResponse
//     * @return
//     * @throws IOException
//     */
//    @Override
//    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws IOException {
//    	System.err.println("-----------------ShiroPermsFilter---onAccessDenied-------------------");
//        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
//        httpServletResponse.setCharacterEncoding("UTF-8");
//        httpServletResponse.setContentType("application/json");
//        RespShiroResult resultData = new RespShiroResult(ShiroResultEnum.UNAUTHORIZED);
//        httpServletResponse.getWriter().write(JSONObject.toJSON(resultData).toString());
//        return false;
//    }
//
//
//}