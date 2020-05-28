package com.songyang.api.controller.user;

import com.songyang.common.StandardResponse;
import com.songyang.service.FileService;
import com.songyang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;
    @GetMapping("/user/login")
    public StandardResponse login(){
        return  StandardResponse.SuccessResponseMessage("成功");
    }

    @PostMapping("/register")
    public  StandardResponse register(String username, String password , String phone, String email,
                                      HttpServletRequest httpServletRequest, MultipartFile multipartFile){
        String path=httpServletRequest.getSession().getServletContext().getRealPath("upload");
        String a=fileService.upload(multipartFile,path);
        return StandardResponse.SuccessResponseData(a);
    }
}
