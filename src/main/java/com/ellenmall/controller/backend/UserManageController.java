package com.ellenmall.controller.backend;

import com.ellenmall.common.Constants;
import com.ellenmall.common.ServerReponse;
import com.ellenmall.pojo.User;
import com.ellenmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
/**
 * Created by zhengying on 2017/9/29.
 */

@Controller
@RequestMapping("/manage/user")
public class UserManageController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value="login.do",method= RequestMethod.POST)
    @ResponseBody
    public ServerReponse<User> login(String username, String password, HttpSession session){
        ServerReponse<User> resp = iUserService.login(username, password);
        if(resp.isSuccess()){
            User user = resp.getData();
            if(user.getRole() == Constants.Role.ROLE_ADMIN){
                session.setAttribute(Constants.CURRENT_USER,user);
                return resp;
            }else{
                return ServerReponse.createByErrorMessage("不是管理员,无法登录");
            }
        }
        return resp;
    }
}
