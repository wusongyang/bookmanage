package com.songyang.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.songyang.common.ResponseCode;
import com.songyang.common.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class AjaxAuthenticationEntryPoint implements AuthenticationEntryPoint {
   @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        if (httpServletResponse.getHeader("token")!=null){
            StandardResponse standardResponse = StandardResponse.ErrorResponseByCodeMessage(401,"token失效请重新登录");
            httpServletResponse.getWriter().write(objectMapper.writeValueAsString(standardResponse));
       }else {

        StandardResponse standardResponse = StandardResponse.ErrorResponseByCodeMessage(ResponseCode.NEED_AUTHORITIES.getCode(),"没有权限");

        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(standardResponse));
        }
    }
}
