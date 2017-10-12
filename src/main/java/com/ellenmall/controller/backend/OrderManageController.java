package com.ellenmall.controller.backend;

import com.ellenmall.common.ServerReponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by zhengying on 2017/10/12.
 */
@Controller
@RequestMapping("/manage/order")
public class OrderManageController {

    public ServerReponse detail(HttpSession session,Integer orderNum){
        return null;
    }

    public ServerReponse list(HttpSession session,Integer pageNum,Integer pageSize){
        return null;
    }

    public ServerReponse search(HttpSession session,Integer orderNum){
        return null;
    }

    public ServerReponse sendGoods(HttpSession session,Integer orderNum){
        return null;
    }
}
