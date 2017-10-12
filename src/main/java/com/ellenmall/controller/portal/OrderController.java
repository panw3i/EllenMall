package com.ellenmall.controller.portal;

import com.ellenmall.common.ServerReponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by zhengying on 2017/10/12.
 */
@Controller
@RequestMapping("/order/")
public class OrderController {

    /** -------- 订单支付相关 ---------**/
    public ServerReponse pay(HttpSession session,Integer orderNum){
        return null;
    }

    public ServerReponse queryOrderPayStatus(HttpSession session,Integer orderNum){
        return null;
    }

    public ServerReponse alipayCallback(HttpSession session){
        return null;
    }

    /** ------------订单相关 ----------**/

    public ServerReponse cancel(HttpSession session,Integer orderNum){
        return null;
    }

    public ServerReponse create(HttpSession session){
        return null;
    }

    public ServerReponse del(HttpSession session,Integer orderNum){
        return null;
    }

    public ServerReponse detail(HttpSession session,Integer orderNum){
        return null;
    }

    public ServerReponse get_order_cart_product(HttpSession session){
        return null;
    }

    public ServerReponse list(HttpSession session,Integer pageNum,Integer pageSize){
        return null;
    }
}
