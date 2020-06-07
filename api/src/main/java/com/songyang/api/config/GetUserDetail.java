package com.songyang.api.config;

import com.songyang.pojo.UserDetils;
import org.springframework.security.core.context.SecurityContextHolder;

public class GetUserDetail {

    public static UserDetils getUserDetailsBySecurity(){
        return (UserDetils) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
