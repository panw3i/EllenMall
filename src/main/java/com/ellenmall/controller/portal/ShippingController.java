package com.ellenmall.controller.portal;

import com.ellenmall.common.Constants;
import com.ellenmall.common.ServerReponse;
import com.ellenmall.pojo.Shipping;
import com.ellenmall.pojo.User;
import com.ellenmall.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by zhengying on 2017/10/12.
 */
@Controller
@RequestMapping("/shipping/")
public class ShippingController {

    @Autowired
    IShippingService iShippingService; //

    public ServerReponse add(HttpSession session, Shipping shipping){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if(user == null){
            return ServerReponse.createByErrorMessage("未登录");
        }
        return ServerReponse.createBySuccess();
    }

    public ServerReponse del(HttpSession session, Integer shippingId){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        return null;
    }

    public ServerReponse list(HttpSession session){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        return null;
    }

    public ServerReponse select(HttpSession session,Integer shippingId){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        return null;
    }

    public ServerReponse update(HttpSession session,Shipping shipping){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        return null;
    }
}
