package com.ellenmall.service.impl;

import com.ellenmall.common.ServerReponse;
import com.ellenmall.dao.UserMapper;
import com.ellenmall.pojo.User;
import com.ellenmall.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhengying on 2017/9/29.
 */
@Service("iUserService")
public class IUserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public ServerReponse<User> login(String username, String password) {
        int resultCount = userMapper.checkUsername(username);
        if(resultCount ==0){
            return ServerReponse.createByErrorMessage("用户名不存在");

        //// TODO: 2017/9/29 密码登录MD5
        User user = userMapper.selectLogin(username,password);
        if(user==null){
            return ServerReponse.createByErrorMessage("密码错误");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerReponse.createBySuccess("登录成功",user);

    }
}
