package com.songyang.api.controller;

import com.songyang.pojo.Admin;
import com.songyang.service.ServiceHello;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Autowired
    private ServiceHello serviceHello;
    @RequestMapping("/hello")
    @ResponseBody
    public Admin hello(){
        return serviceHello.getHello();
    }
}
