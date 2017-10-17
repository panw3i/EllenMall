package com.ellenmall.service.impl;

import com.ellenmall.common.ServerReponse;
import org.springframework.stereotype.Service;

/**
 * Created by zhengying on 2017/10/12.
 */
@Service("iOrderService")
public class IOrderServiceImpl {

    public ServerReponse createOrder(int userId,int productId){
        return null;
    }

    public ServerReponse deleteOrder(int userId, int orderId){
        return null;
    }

    public ServerReponse orderList(int userId,int pageNum,int pageSize){
        return null;
    }

    public ServerReponse cancelOrder(int userId,int orderId) {
        return null;
    }

    public ServerReponse changeOrderStatus(int userId,int orderId){
        return null;
    }
}
