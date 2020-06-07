package com.songyang.service;


import com.github.pagehelper.PageInfo;
import com.songyang.pojo.User;



public interface UserService {
    int regist(User user ,String type);
    int update(User user);
    boolean verifyUsername(String username);
    PageInfo getUserList(int pageSize , int pageNum);
    int deleteUser(Integer id);
}
