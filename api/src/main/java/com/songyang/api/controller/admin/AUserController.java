package com.songyang.api.controller.admin;

import com.github.pagehelper.PageInfo;
import com.songyang.api.ImgPropoties.ImgType;
import com.songyang.common.Const;
import com.songyang.common.StandardResponse;
import com.songyang.pojo.User;
import com.songyang.pojo.UserPic;
import com.songyang.service.FileService;
import com.songyang.service.UserPicService;
import com.songyang.service.UserService;
import com.songyang.vo.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/admin")
public class AUserController {
    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private FileService fileService;
    @Autowired
    private UserPicService userPicService;
    @GetMapping("/users")
    public StandardResponse getUserList(@RequestParam(defaultValue = "10") int pagesize , int pageNum){
        PageInfo pageInfo = userService.getUserList(pagesize,pageNum);
        return StandardResponse.SuccessResponse("查询成功",pageInfo);
    }
    @DeleteMapping("/{id}")
    public StandardResponse deleteUser(@PathVariable(value = "id") int id){
       if (userService.deleteUser(id)>0){
           return StandardResponse.SuccessResponseMessage("删除成功");
       }else {
           return StandardResponse.ErrorResponseMessage("删除失败");
       }
    }
    @Transactional(rollbackFor = Exception.class)
    @PostMapping("/employee")
    public StandardResponse addEmployee(User user, MultipartFile multipartFile, HttpServletRequest httpServletRequest){
        String path=httpServletRequest.getSession().getServletContext().getRealPath("upload");
        if(userService.verifyUsername(user.getName())){
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userService.regist(user, Const.EMPLOYEE);
            String picName=fileService.upload(multipartFile,path, ImgType.userImagesPath);
            UserPic userPic =new UserPic();
            userPic.setPicHost("http://r318x60291.zicp.vip/"+ImgType.userImagesPath);
            userPic.setPicName(picName);
            userPic.setUserId(user.getId());
            userPicService.saveImage(userPic);
            user.setPicId(userPic.getId());
            int conut=userService.update(user);
            user.setPassword("");
            if (conut>0){
                return StandardResponse.SuccessResponse("注册成功",user);
            }
            else return StandardResponse.ErrorResponseMessage("注册失败");


        }else {
            return StandardResponse.ErrorResponseMessage("用户名存在");
        }

    }


}
