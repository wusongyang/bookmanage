package com.songyang.vo;

import java.util.List;

public class UserVo {

    private  int id;
    private  String username;
    private  String phone;
    private String email;
    private List<PicVo>  picVoList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PicVo> getPicVoList() {
        return picVoList;
    }

    public void setPicVoList(List<PicVo> picVoList) {
        this.picVoList = picVoList;
    }
}
