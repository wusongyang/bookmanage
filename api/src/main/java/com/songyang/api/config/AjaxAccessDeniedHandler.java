package com.songyang.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.songyang.common.ResponseCode;
import com.songyang.common.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import java.io.PrintWriter;
@Component
public class AjaxAccessDeniedHandler  implements AccessDeniedHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {

        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.setCharacterEncoding("utf-8");
        PrintWriter out =httpServletResponse.getWriter();
        out.write(objectMapper.writeValueAsString(StandardResponse.ErrorResponseByCodeMessage(ResponseCode.NEED_AUTHORITIES.getCode(),"需要权限")));
        out.flush();
        out.close();

    }
}
