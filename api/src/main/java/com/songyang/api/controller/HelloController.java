package com.songyang.api.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {


    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "123";
    }
    @RequestMapping("/admin/hello")
    @ResponseBody
    public String hello1(){
        return "1234444";
    }
}
