package com.ellenmall.common;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.io.Serializable;

/**
 * Created by zhengying on 2017/9/29.
 */
// T代表响应对象的类型
//保证序列化json的时候 如果是null的对象 key也会消失
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServerReponse<T> implements Serializable{

    private String msg;
    private int status;
    private T data;

    private ServerReponse(int status){
        this.status = status;
    }

    private ServerReponse(int status,T data){
        this.status = status;
        this.data = data;
    }

    private ServerReponse(int status ,T data,String msg){
        this.status = status;
        this.data = data;
        this.msg = msg;
    }

    private ServerReponse (int status,String msg){
        this.status = status;
        this.msg = msg;
    }

    //使它不在json序列化中
    @JsonIgnore
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
        return msg;
    }

    public static <T> ServerReponse<T> createBySuccess(){
        return new ServerReponse<T>(ResponseCode.SUCCESS.getCode());
    }

    public static <T> ServerReponse<T> createBySuccessMessage(String msg){
        return new ServerReponse<T>(ResponseCode.SUCCESS.getCode(),msg);
    }

    public static <T> ServerReponse<T> createBySuccess(T data){
        return new ServerReponse<T>(ResponseCode.SUCCESS.getCode(),data);
    }

    public static <T> ServerReponse<T> createBySuccess(String msg,T data){
        return new ServerReponse<T>(ResponseCode.SUCCESS.getCode(),data,msg);
    }

    public static <T> ServerReponse<T> createByError(){
        return new ServerReponse<T>(ResponseCode.ERROR.getCode(),ResponseCode.ERROR.getDesc());
    }

    public static <T> ServerReponse<T> createByErrorMessage(String errMsg){
        return new ServerReponse<T>(ResponseCode.ERROR.getCode(),errMsg);
    }

    public static <T> ServerReponse<T> createByErrCodeMsg(int errCode,String errMsg){
        return new ServerReponse<T>(errCode,errMsg);
    }
}
