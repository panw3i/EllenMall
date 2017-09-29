package com.ellenmall.service.impl;

import com.ellenmall.common.Constants;
import com.ellenmall.common.ServerReponse;
import com.ellenmall.common.TokenCache;
import com.ellenmall.dao.UserMapper;
import com.ellenmall.pojo.User;
import com.ellenmall.service.IUserService;
import com.ellenmall.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

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
        }
        //// TODO: 2017/9/29 密码登录MD5
        String md5Pwd = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username,md5Pwd);
        if(user==null){
            return ServerReponse.createByErrorMessage("密码错误");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerReponse.createBySuccess("登录成功",user);
    }

    public ServerReponse<String> register(User user){
        ServerReponse validResp = this.checkValid(user.getUsername(),Constants.USERNAME);
        if(!validResp.isSuccess()){
            return validResp;
        }
        validResp = this.checkValid(user.getEmail(),Constants.EMAIL);
        if(!validResp.isSuccess()){
            return validResp;
        }
        user.setRole(Constants.Role.ROLE_CUSTOM);
        //MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int resultCount = userMapper.insert(user);
        if(resultCount==0){
            return ServerReponse.createByErrorMessage("注册失败");
        }
        return ServerReponse.createBySuccessMessage("注册成功!");
    }

    public ServerReponse<String> checkValid(String str,String type){
        if(org.apache.commons.lang3.StringUtils.isNotBlank(type)){
            if(Constants.EMAIL.equals(type)){
                int resultCount = userMapper.checkEmail(str);
                if(resultCount>0){
                    return ServerReponse.createByErrorMessage("邮箱已存在");
                }
            }else if(Constants.USERNAME.equals(type)){
                int resultCount = userMapper.checkUsername(str);
                if(resultCount>0){
                    return ServerReponse.createByErrorMessage("用户名已存在");
                }
            }
        }else{
            return ServerReponse.createByErrorMessage("参数错误");
        }
        return ServerReponse.createBySuccessMessage("效验成功");
    }

    public ServerReponse selectQuestion(String username){
        ServerReponse validResp = this.checkValid(username,Constants.USERNAME);
        if(validResp.isSuccess()){
            return ServerReponse.createByErrorMessage("用户不存在");
        }
        String question = userMapper.selectQuestionByUsername(username);
        if (StringUtils.isNotBlank(question)){
            return ServerReponse.createBySuccessMessage(question);
        }

        return ServerReponse.createByErrorMessage("找回密码的问题是空的");
    }

    public ServerReponse<String> checkAnswer(String username,String question,String answer){
        int resultCount = userMapper.checkAnswer(username,question,answer);
        if(resultCount>0){
            String forgetToken = UUID.randomUUID().toString();//一般不会重复的字符串 把它放到本地cache中 设置有效期
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+username,forgetToken);
            return ServerReponse.createBySuccess(forgetToken);
        }
        return ServerReponse.createByErrorMessage("问题的答案错误");
    }

    public ServerReponse<String> forgetResetPwd(String username,String pwdnew,String forgetToken){
        if(StringUtils.isBlank(forgetToken)){
            return ServerReponse.createByErrorMessage("用户不存在");
        }
        String token= TokenCache.getKey(TokenCache.TOKEN_PREFIX+username);
        if(StringUtils.isBlank(token)) {
            return ServerReponse.createByErrorMessage("token无效或者过期");
        }
        if(StringUtils.equals(forgetToken,token)){
            String md5Pwd = MD5Util.MD5EncodeUtf8(pwdnew);
            int rowCount = userMapper.updatePwdByUserName(username,md5Pwd);
            if(rowCount>0){
                ServerReponse.createBySuccessMessage("修改密码成功");
            }
        }else{
            return ServerReponse.createByErrorMessage("token错误 请重新获取重置密码的token");
        }
        return ServerReponse.createByErrorMessage("修改密码失败");
    }

}
