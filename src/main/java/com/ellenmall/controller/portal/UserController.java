package com.ellenmall.controller.portal;

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
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    @RequestMapping(value="login.do",method= RequestMethod.GET)
    @ResponseBody
    public ServerReponse<User> login(String username, String password, HttpSession session){
        //serviece -->mybaits -> dao
        ServerReponse<User> response = iUserService.login(username,password);
        if(response.isSuccess()){
            session.setAttribute(Constants.CURRENT_USER,response.getData());
        }
        return response;
    }

    @RequestMapping(value="logout.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerReponse<User> logout(HttpSession session){
        session.removeAttribute(Constants.CURRENT_USER);
        return ServerReponse.createBySuccess();
    }

    @RequestMapping(value = "register.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerReponse<String> register(User user){
        return iUserService.register(user);
    }

    @RequestMapping(value = "check_valid.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerReponse<String> checkValid(String str,String type){
        return iUserService.checkValid(str,type);
    }

    @RequestMapping(value = "get_user_info.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerReponse<User> getUserInfo(HttpSession session){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if(user != null){
            return ServerReponse.createBySuccess(user);
        }
        return ServerReponse.createByErrorMessage("用户未登录,无法获取当前用户信息");
    }

    @RequestMapping(value = "forget_get_question.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerReponse<String> forgetGetQuestion(String username){
        return iUserService.selectQuestion(username);
    }

    @RequestMapping(value = "forget_check_question.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerReponse<String> forgetCheckAnswer(String username,String question,String answer){
        return iUserService.checkAnswer(username, question, answer);
    }

    @RequestMapping(value = "forget_reset_pwd.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerReponse<String> forgetResetPassword(String username,String pwdnew,String forgetToken){
        return iUserService.forgetResetPwd(username, pwdnew, forgetToken);
    }

    @RequestMapping(value = "reset_pwd.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerReponse<String> resetPwd(HttpSession session,String pwdOld,String pwdNew){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if(user == null){
            return ServerReponse.createByErrorMessage("用户未登录");
        }
        return iUserService.resetPwd(pwdOld,pwdNew,user);
    }

    @RequestMapping(value = "update_info.do",method = RequestMethod.GET)
    @ResponseBody
    public ServerReponse<User> updateInfomation(HttpSession session,User user){
        User currentUser = (User) session.getAttribute(Constants.CURRENT_USER);
        if(currentUser == null){
            return ServerReponse.createByErrorMessage("用户未登录");
        }
        user.setId(currentUser.getId());
        user.setUsername(currentUser.getUsername());
        ServerReponse<User> resp = iUserService.updateInformation(user);
        if(resp.isSuccess()){
            resp.getData().setUsername(currentUser.getUsername());
            session.setAttribute(Constants.CURRENT_USER,resp.getData());
        }
        return resp;
    }

}
