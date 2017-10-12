package com.ellenmall.controller.portal;

import com.ellenmall.common.Constants;
import com.ellenmall.common.ServerReponse;
import com.ellenmall.pojo.User;
import com.ellenmall.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by zhengying on 2017/10/12.
 */
@Controller
@RequestMapping("/cart/")
public class CartController {

    @Autowired
    ICartService iCartService;

    @RequestMapping("list.do")
    @ResponseBody
    public ServerReponse list(HttpSession session){
        return iCartService.list();
    }

    @RequestMapping("add.do")
    @ResponseBody
    public ServerReponse add(HttpSession session,Integer productId,Integer count){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if(user == null){
            return ServerReponse.createByErrorMessage("未登录");
        }
        return iCartService.add(productId,count);
    }

    @RequestMapping("update.do")
    @ResponseBody
    public ServerReponse update(HttpSession session,Integer productId,Integer count){
        return iCartService.unpdate(productId);
    }

    @RequestMapping("delete_product.do")
    @ResponseBody
    public ServerReponse delete_product(HttpSession session,String productIds){
        return iCartService.delete_product(productIds);
    }

    @RequestMapping("select.do")
    @ResponseBody
    public ServerReponse select(HttpSession session,Integer productId){
        return iCartService.select(productId);
    }

    @RequestMapping("un_select.do")
    @ResponseBody
    public ServerReponse un_select(HttpSession session,Integer productId){
        return iCartService.unselect(productId);
    }

    @RequestMapping("get_cart_product_count.do")
    @ResponseBody
    public ServerReponse get_cart_product_count(HttpSession session){
        return iCartService.getCartProductCount();
    }

    @RequestMapping("select_all.do")
    @ResponseBody
    public ServerReponse select_all(HttpSession session){
        return iCartService.selectAll();
    }

    @RequestMapping("unselect_all.do")
    @ResponseBody
    public ServerReponse unselect_all(HttpSession session){
        return iCartService.unselectAll();
    }

}
