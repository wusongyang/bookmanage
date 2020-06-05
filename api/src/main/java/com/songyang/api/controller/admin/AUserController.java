package com.songyang.api.controller.admin;

import com.github.pagehelper.PageInfo;
import com.songyang.common.StandardResponse;
import com.songyang.service.UserService;
import com.songyang.vo.UserVo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/admin")
public class AUserController {
    @Autowired
    private UserService userService;
    @GetMapping("/users")
    public StandardResponse getUserList(@RequestParam(defaultValue = "10") int pagesize , int pageNum){
        PageInfo pageInfo = userService.getUserList(pagesize,pageNum);
        return StandardResponse.SuccessResponse("查询成功",pageInfo);
    }
    @GetMapping("/delete/{id}")
    public StandardResponse deleteUser(@PathVariable(value = "id") int id){
       if (userService.deleteUser(id)>0){
           return StandardResponse.SuccessResponseMessage("删除成功");
       }else {
           return StandardResponse.ErrorResponseMessage("删除失败");
       }
    }


}
