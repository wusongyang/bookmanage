package com.songyang.service.imp;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.songyang.dao.UserMapper;
import com.songyang.dao.UserRoleMapper;
import com.songyang.pojo.User;
import com.songyang.pojo.UserPic;
import com.songyang.pojo.UserRole;
import com.songyang.service.UserPicService;
import com.songyang.service.UserService;
import com.songyang.vo.PicVo;
import com.songyang.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UserRoleMapper userRoleMapper;
    @Autowired
    private UserPicService userPicService;
    @Override
    public int regist(User user) {
        userMapper.insertSelective(user);
        UserRole userRole =new UserRole();
        userRole.setRoleId(2);
        System.out.println(user.getId());
        userRole.setUserId(user.getId());
        userRoleMapper.insertSelective(userRole);
        return  1;
    }

    @Override
    public int update(User user) {
        return userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public boolean verifyUsername(String username) {
        User user=userMapper.selectByUsername(username);
        if (user==null){
            return  true;
        }else {
            return false;
        }
    }

    @Override
    public PageInfo getUserList(int pageSize, int pageNum) {
        PageHelper.startPage(pageNum,pageSize);
        List<User> list = userMapper.selectUserList();
        PageInfo pageInfo =new PageInfo(list);
        List<UserVo> userVoList =Lists.newArrayList();
        for (User u:
             list) {
            List<PicVo> picVoList = Lists.newArrayList();
            UserPic userPic=userPicService.selectImageByUserId(u.getId());
            PicVo picVo =new PicVo();
            if(userPic!=null){
                picVo.setPicId(userPic.getId());
                picVo.setPicName(userPic.getPicName());
                picVo.setPicHost(userPic.getPicHost());
                picVoList.add(picVo);
            }

            UserVo userVo=this.assembleUser2UserVo(u,picVoList);
            userVoList.add(userVo);
        }
        pageInfo.setList(userVoList);
        return pageInfo;
    }

    @Override
    public int deleteUser(Integer id) {
       return userMapper.deleteByPrimaryKey(id);
    }

    //转换
    private UserVo assembleUser2UserVo(User user, List<PicVo> picVoList){
        UserVo userVo = new UserVo();
        userVo.setId(user.getId());
        userVo.setEmail(user.getEmail());
        userVo.setPhone(user.getPhone());
        userVo.setUsername(user.getName());
        userVo.setPicVoList(picVoList);
        return  userVo;
    }


}
