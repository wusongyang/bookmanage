package com.songyang.api.config;

import com.songyang.dao.RoleMapper;
import com.songyang.dao.UserMapper;
import com.songyang.dao.UserRoleMapper;
import com.songyang.pojo.Role;
import com.songyang.pojo.User;
import com.songyang.pojo.UserDetils;
import com.songyang.pojo.UserRole;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ServiceUserdetil implements UserDetailsService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    UserRoleMapper userRoleMapper;
    @Autowired
    RoleMapper roleMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(s);
        User user = userMapper.selectByUsername(s);
        if (user==null){
            throw new UsernameNotFoundException("没有用户名");
        }
        List<UserRole> roles = userRoleMapper.selectRoleIdsByUserId(user.getId());
        List<Role> roleList =new ArrayList<>();
        List<SimpleGrantedAuthority> authorityList =new ArrayList<>();
        for (UserRole r :roles) {
            Role role= roleMapper.selectByPrimaryKey(r.getRoleId());
            authorityList.add(new SimpleGrantedAuthority("ROLE_"+role.getName()));
        }
        UserDetils userDetils =new UserDetils();
        userDetils.setAuthorities(authorityList);
        userDetils.setId(user.getId());
        userDetils.setUsername(user.getName());
        userDetils.setPassword(user.getPassword());
        userDetils.setEmail(user.getEmail());
        return userDetils;
    }
}
