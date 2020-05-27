package com.songyang.common;

public  enum ResponseCode {

    SUCCESS(200,"SUCCESS"),
    ERROR(401,"ERROR"),
    NEED_LOGIN(300,"NEED_LOGIN"),
    NEED_AUTHORITIES(300,"NEED_AUTHORITIES"),
    ILLEGAL_ARGUMENT(2,"ILLEGAL_ARGUMENT");


    private final int code;
    private final String desc;


    ResponseCode(int code,String desc){
        this.code = code;
        this.desc = desc;
    }

    public int getCode(){
        return code;
    }
    public String getDesc(){
        return desc;
    }

}
