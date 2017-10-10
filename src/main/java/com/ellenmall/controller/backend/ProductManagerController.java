package com.ellenmall.controller.backend;

import com.ellenmall.common.ServerReponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by zhengying on 2017/10/9.
 */

@Controller
@RequestMapping
public class ProductManagerController {

    public ServerReponse addProduct(HttpSession session,String cateName,String desc){
        return null;
    }

    public ServerReponse updateProduct(HttpSession session,String cName,String ev){
        return null;
    }

}
