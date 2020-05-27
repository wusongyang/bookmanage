package com.songyang.common;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
@JsonSerialize(include =  JsonSerialize.Inclusion.NON_NULL)
public class StandardResponse<T> implements Serializable {
    //响应体

    private T data;
    //响应的信息
    private String message;
    //响应状态码
    private  int status;
    private StandardResponse(T data ,String message,int status){
        this.data =data;
        this.message =message;
        this.status =status;
    }
    private StandardResponse(int status){
        this.status =status;
    }
    private  StandardResponse(int status ,String message){
        this.status=status;
        this.message=message;
    }
    private StandardResponse(T data ,int status){
        this.status=status;
        this.data=data;
    }
    private StandardResponse(T data, String message){
        this.message=message;
        this.data= data;
    }
    @JsonIgnore
    //使之不在json序列化结果当中
    public boolean isSuccess(){
        return this.status == ResponseCode.SUCCESS.getCode();
    }

    public int getStatus(){
        return status;
    }
    public T getData(){
        return data;
    }
    public String getMsg(){
        return message;
    }

    public static <T> StandardResponse<T> SuccessResponse(){
        StandardResponse standardResponse =new StandardResponse(ResponseCode.SUCCESS.getCode());
        return standardResponse;
    }
    public static<T> StandardResponse<T> SuccessResponseData(T data){
        StandardResponse standardResponse =new StandardResponse(data,ResponseCode.SUCCESS.getCode());
        return standardResponse;
    }
    public static<T> StandardResponse<T> SuccessResponseMessage(String message){
        StandardResponse standardResponse =new StandardResponse(ResponseCode.SUCCESS.getCode(),message);
        return standardResponse;
    }
    public static<T> StandardResponse<T> SuccessResponse(String message,T data){
        StandardResponse standardResponse =new StandardResponse(data,message,ResponseCode.SUCCESS.getCode());
        return standardResponse;
    }
    public static<T> StandardResponse<T> ErrorResponseMessage(String message){
        StandardResponse standardResponse =new StandardResponse(ResponseCode.ERROR.getCode(),message);
        return standardResponse;
    }
    public static<T> StandardResponse<T> ErrorResponseCodeMessage() {
        StandardResponse standardResponse = new StandardResponse(ResponseCode.ERROR.getCode(), ResponseCode.ERROR.getDesc());
        return standardResponse;
    }
    public static<T> StandardResponse<T> ErrorResponseByCodeMessage(int errorCode ,String message) {
        StandardResponse standardResponse = new StandardResponse(errorCode, message);
        return standardResponse;
    }

    public class ErrorResponse {
    }
}
