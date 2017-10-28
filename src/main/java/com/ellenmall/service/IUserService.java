package com.ellenmall.service;

import com.ellenmall.common.ServerResponse;
import com.ellenmall.pojo.User;

/**
 * Created by zhengying on 2017/9/29.
 */
public interface IUserService {

    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str, String type);

    ServerResponse selectQuestion(String username);

    ServerResponse<String> checkAnswer(String username, String question, String answer);

    ServerResponse<String> forgetResetPwd(String username, String pwdnew, String forgetToken);

    ServerResponse<String> resetPwd(String pwdOld, String pwdNew, User user);

    ServerResponse<User> updateInformation(User user);

    ServerResponse<User> getInfo(Integer userId);

    ServerResponse checkAdminRole(User user);
}