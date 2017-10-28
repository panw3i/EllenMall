package com.ellenmall.controller.portal;

import com.ellenmall.common.ServerResponse;
import com.ellenmall.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by zhengying on 2017/10/11.
 */
@Controller
@RequestMapping("/product/")
public class ProductController  {

    @Autowired
    private IProductService iProductService;

    @RequestMapping("detail.do")
    @ResponseBody
    public ServerResponse detail(HttpSession session, Integer productId){
        return iProductService.productDetail(productId);
    }

    @RequestMapping("list.do")
    @ResponseBody
    public ServerResponse list(HttpSession session, @RequestParam(value = "keyword",required = false) String keyword,
                               @RequestParam(value = "cateId" ,required = false) Integer cateId,
                               @RequestParam( value = "pageNum",defaultValue = "1") Integer pageNum,
                               Integer pageSize){
        return iProductService.manageProductList(cateId,pageNum,pageSize);
    }




}
