package com.songyang.service.imp;

import com.songyang.dao.UserPicMapper;
import com.songyang.dao.UserRoleMapper;
import com.songyang.pojo.UserPic;
import com.songyang.pojo.UserRole;
import com.songyang.service.UserPicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserPicServiceImp implements UserPicService {
    @Autowired
    private UserPicMapper userPicMapper;

    @Override
    public int saveImage(UserPic userPic) {
    return    userPicMapper.insertUserPicReturnId(userPic);

    }

    @Override
    public UserPic selectImageByUserId(int id) {
       return userPicMapper.selectImageByUserId(id);
    }
}
