package com.songyang.service.imp;

import com.songyang.common.StandardResponse;
import com.songyang.dao.UserMapper;
import com.songyang.pojo.User;
import com.songyang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public StandardResponse regist(User user) {
        return null;
    }
}
