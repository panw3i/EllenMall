package com.ellenmall.controller.portal;

import com.ellenmall.common.Constants;
import com.ellenmall.common.ServerResponse;
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

    /**
     * 加入购物车
     * @param session
     * @param productId
     * @param count
     * @return
     */
    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse add(HttpSession session, Integer productId, Integer count){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return iCartService.add(user.getId(),productId,count);
    }


    /**
     * 购物车列表
     * @param session
     * @return
     */
    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse list(HttpSession session, int pageNum, int pageSize){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return iCartService.list(user.getId(),pageNum,pageSize);
    }

    /**
     * 选中购物车中的商品
     * @param session
     * @param cartId
     * @return
     */
    @RequestMapping("select.do")
    @ResponseBody
    public ServerResponse select(HttpSession session, Integer cartId){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return iCartService.select(user.getId(),cartId);
    }

    /**
     * 选中所有产品
     * @param session
     * @return
     */
    @RequestMapping("select_all.do")
    @ResponseBody
    public ServerResponse select_all(HttpSession session){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return iCartService.selectAll(user.getId());
    }

    /**
     * 取消选中购物车中的产品
     * @param session
     * @param cartId
     * @return
     */
    @RequestMapping("un_select.do")
    @ResponseBody
    public ServerResponse un_select(HttpSession session, Integer cartId){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return iCartService.unselect(user.getId(),cartId);
    }

    /**
     * 取消选中所有产品
     * @param session
     * @return
     */
    @RequestMapping("unselect_all.do")
    @ResponseBody
    public ServerResponse unselect_all(HttpSession session){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return iCartService.unselectAll(user.getId());
    }

    /**
     * 获取购物车中产品数量
     * @param session
     * @return
     */
    @RequestMapping("get_cart_product_count.do")
    @ResponseBody
    public ServerResponse get_cart_product_count(HttpSession session){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return iCartService.getCartProductCount(user.getId());
    }

    /**
     * 删除购物车中商品
     * @param session
     * @param cartIds
     * @return
     */
    @RequestMapping("delete_product.do")
    @ResponseBody
    public ServerResponse delete_product(HttpSession session, String cartIds){
        User user = (User) session.getAttribute(Constants.CURRENT_USER);
        if(user == null){
            return ServerResponse.createByErrorMessage("未登录");
        }
        return iCartService.delete_product(user.getId(),cartIds);
    }

}
