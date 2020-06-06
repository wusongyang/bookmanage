package com.songyang.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.songyang.common.JwtUtils;
import com.songyang.common.StandardResponse;
import com.songyang.pojo.UserDetils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    private JwtUtils jwtUtils =new JwtUtils();
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ObjectMapper objectMapper =new ObjectMapper();
        UserDetils userDetils = (UserDetils) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String token=jwtUtils.generateToken(userDetils);
        StandardResponse standardResponse = StandardResponse.SuccessResponse("success",token);
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setContentType("application/json;charset=UTF-8");
        httpServletResponse.getWriter().write(objectMapper.writeValueAsString(standardResponse));
    }
}
