package com.yyc.exception;

import com.alibaba.fastjson.JSONObject;
import com.yyc.vo.RespMsg;
import com.yyc.vo.ResultEnum;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 自定义异常捕获，主要捕获没有权限异常
 */
public class NoPermissionException extends SimpleMappingExceptionResolver {

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        if (ex instanceof  AuthorizationException || ex instanceof UnauthorizedException){
            ModelAndView mv = new ModelAndView();//必须返回一个MV,否则setStatus无效!!【未知原因】
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            RespMsg resultData = new RespMsg(ResultEnum.UNAUTHORIZED);
            try {
                response.getWriter().write(JSONObject.toJSON(resultData).toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return mv;
        }else { // 其他异常打印
            ex.printStackTrace();
        }
        return null;
    }
}