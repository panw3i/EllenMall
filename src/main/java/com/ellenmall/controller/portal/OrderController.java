package com.ellenmall.controller.portal;

import com.ellenmall.common.Constants;
import com.ellenmall.common.ServerReponse;
import com.ellenmall.pojo.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by zhengying on 2017/10/12.
 */
@Controller
@RequestMapping("/order/")
public class OrderController {



    /**
     * 创建订单
     * @param session
     * @return
     */
    public ServerReponse create(HttpSession session,String shippingIds){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if(user == null){
            return ServerReponse.createByErrorMessage("用户未登录");
        }
        
        return null;
    }

    /**
     * 订单列表
     * @param session
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerReponse list(HttpSession session,Integer pageNum,Integer pageSize){
        return null;
    }

    /**
     * 获取购物车中订单商品
     * @param session
     * @return
     */
    public ServerReponse get_order_cart_product(HttpSession session){
        return null;
    }

    /**
     * 订单详情
     * @param session
     * @param orderNum
     * @return
     */
    public ServerReponse detail(HttpSession session,Integer orderNum){
        return null;
    }

    /**
     * 取消订单
     * @param session
     * @param orderNum
     * @return
     */
    public ServerReponse cancel(HttpSession session,Integer orderNum){
        return null;
    }

    /**
     * 删除订单
     * @param session
     * @param orderNum
     * @return
     */
    public ServerReponse del(HttpSession session,Integer orderNum){
        return null;
    }




}
