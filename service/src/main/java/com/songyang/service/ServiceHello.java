package com.songyang.service;

import com.songyang.dao.AdminMapper;
import com.songyang.pojo.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceHello {
        @Autowired
        private AdminMapper adminMapper;
    public Admin getHello(){
       Admin admin = adminMapper.selectByPrimaryKey(1);
        return admin;
    }
}
