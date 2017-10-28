package com.ellenmall.controller.portal;

import com.ellenmall.common.ServerResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by zhengying on 2017/10/22.
 */

@Controller
@RequestMapping("orderPay")
public class OrderPayController  {

    /**
     * 支付丁当
     * @param session
     * @param orderNum
     * @return
     */
    public ServerResponse pay(HttpSession session, Integer orderNum){
        return null;
    }

    /**
     * 查询订单状态
     * @param session
     * @param orderNum
     * @return
     */
    public ServerResponse queryOrderPayStatus(HttpSession session, Integer orderNum){
        return null;
    }

    /**
     * 支付宝回调
     * @param session
     * @return
     */
    public ServerResponse alipayCallback(HttpSession session){
        return null;
    }
}
