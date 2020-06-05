package com.songyang.service;

import com.songyang.pojo.UserPic;


public interface UserPicService {
    int saveImage(UserPic userPic);
    UserPic selectImageByUserId(int id);
}
