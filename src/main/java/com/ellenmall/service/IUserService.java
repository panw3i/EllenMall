package com.ellenmall.service;

import com.ellenmall.common.ServerReponse;
import com.ellenmall.pojo.User;

/**
 * Created by zhengying on 2017/9/29.
 */
public interface IUserService {

    ServerReponse<User> login(String username, String password);
    ServerReponse<String> register(User user);
    ServerReponse<String> checkValid(String str,String type);

}
