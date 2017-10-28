package com.ellenmall.controller.backend;

import com.ellenmall.common.ServerResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by zhengying on 2017/10/12.
 */
@Controller
@RequestMapping("/manage/order")
public class OrderManageController {

    public ServerResponse detail(HttpSession session, Integer orderNum){
        return null;
    }

    public ServerResponse list(HttpSession session, Integer pageNum, Integer pageSize){
        return null;
    }

    public ServerResponse search(HttpSession session, Integer orderNum){
        return null;
    }

    public ServerResponse sendGoods(HttpSession session, Integer orderNum){
        return null;
    }
}
