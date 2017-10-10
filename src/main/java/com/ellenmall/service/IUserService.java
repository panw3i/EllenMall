package com.ellenmall.service;

import com.ellenmall.common.ServerReponse;
import com.ellenmall.pojo.User;

/**
 * Created by zhengying on 2017/9/29.
 */
public interface IUserService {

    ServerReponse<User> login(String username, String password);

    ServerReponse<String> register(User user);

    ServerReponse<String> checkValid(String str, String type);

    ServerReponse selectQuestion(String username);

    ServerReponse<String> checkAnswer(String username, String question, String answer);

    ServerReponse<String> forgetResetPwd(String username, String pwdnew, String forgetToken);

    ServerReponse<String> resetPwd(String pwdOld,String pwdNew,User user);

    ServerReponse<User> updateInformation(User user);

    ServerReponse<User> getInfo(Integer userId);

    ServerReponse checkAdminRole(User user);
}