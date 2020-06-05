package com.songyang.api.controller.user;


import com.songyang.api.ImgPropoties.ImgType;
import com.songyang.common.StandardResponse;
import com.songyang.pojo.User;
import com.songyang.pojo.UserPic;
import com.songyang.service.FileService;
import com.songyang.service.UserPicService;
import com.songyang.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/user")
public class UserController {
    private static  final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private FileService fileService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserPicService userPicService;

    @PostMapping("/login")
    public StandardResponse login(){
        return  StandardResponse.SuccessResponseMessage("成功");
    }

    @PostMapping("/register")
    public  StandardResponse register(String username, String password , String phone, String email,
                                      HttpServletRequest httpServletRequest, MultipartFile multipartFile){
        String path=httpServletRequest.getSession().getServletContext().getRealPath("upload");
        if (userService.verifyUsername(username)){
            return StandardResponse.ErrorResponseMessage("用户名已经存在");
        }
        User user =new User();
        user.setName(username);
        user.setEmail(email);
        password= bCryptPasswordEncoder.encode(password);
        user.setPassword(password);
        user.setPhone(phone);
        Integer userId =userService.regist(user);
        logger.info(userId.toString());
        String picName=fileService.upload(multipartFile,path,ImgType.userImagesPath);
        UserPic userPic =new UserPic();
        userPic.setPicHost("http://r318x60291.zicp.vip/"+ImgType.userImagesPath);
        userPic.setPicName(picName);
        userPic.setUserId(user.getId());
        Integer picId=userPicService.saveImage(userPic);
        logger.info(picId.toString());
        user.setPicId(picId);
        user.setPicId(userPic.getId());
        int conut=userService.update(user);
        user.setPassword("");
        if (conut>0){
            return StandardResponse.SuccessResponse("注册成功",user);
        }
        return StandardResponse.ErrorResponseMessage("注册失败");
    }
    @PostMapping("/updateuser/{id}")
    public StandardResponse updateUser(String username, String phone, String email,
                                       HttpServletRequest httpServletRequest, MultipartFile multipartFile, @PathVariable("id") int id){
       if (userService.verifyUsername(username)){
           return StandardResponse.ErrorResponseMessage("用户名已经存在");
       }
        User user =new User();
        user.setId(id);
        user.setName(username);
        user.setPhone(phone);
        user.setEmail(email);
       int conut =userService.update(user);
       if (conut>0){
           return StandardResponse.SuccessResponseMessage("更新成功");
       }
       return  StandardResponse.ErrorResponseMessage("更新失败");
    }

}
