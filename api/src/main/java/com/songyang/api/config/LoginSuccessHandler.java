package com.songyang.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.songyang.common.StandardResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ObjectMapper objectMapper =new ObjectMapper();
        StandardResponse standardResponse =StandardResponse.SuccessResponse("登陆成功",authentication.getPrincipal());
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(standardResponse));
    }
}
