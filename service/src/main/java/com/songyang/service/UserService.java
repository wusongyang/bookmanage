package com.songyang.service;

import com.songyang.common.StandardResponse;
import com.songyang.pojo.User;

public interface UserService {
    StandardResponse regist(User user);
}
