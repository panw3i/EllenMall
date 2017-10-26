package com.ellenmall.service.impl;

import com.ellenmall.common.Constants;
import com.ellenmall.common.ServerReponse;
import com.ellenmall.dao.OrderMapper;
import com.ellenmall.pojo.Order;
import com.ellenmall.util.DateTimeUtils;
import com.ellenmall.vo.OrderListVo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhengying on 2017/10/12.
 */
@Service("iOrderService")
public class IOrderServiceImpl {

    @Autowired
    OrderMapper orderMapper;

    public ServerReponse createOrder(int userId,Integer shippingId){
        Order order = new Order();
        order.setStatus(Constants.Order.STATUS_WAIT_PAY);
        order.setShippingId(shippingId);
        order.setPaymentType(Constants.Order.PAY_ALI);

        return null;
    }

    public ServerReponse orderList(int userId,int pageNum,int pageSize){
        return ServerReponse.createBySuccess(list(userId));
    }

    public ServerReponse getOrderCartProduct(int userId,int orderId){
        return null;
    }

    public ServerReponse cancelOrder(int userId,int orderId) {
        return null;
    }

    public ServerReponse deleteOrder(int userId, int orderId){
        return null;
    }


    private List<OrderListVo> list(int userId){
        List<OrderListVo> orderListVoList = Lists.newArrayList();
        List<Order> orderList = orderMapper.selectOrderListByUserId(userId);
        for(int i = 0 ;i<orderList.size(); i++){
            OrderListVo orderListVo = new OrderListVo();
            Order order = orderList.get(i);
            orderListVo.setId(order.getId());
            orderListVo.setUserId(order.getUserId());
            orderListVo.setOrderNo(order.getOrderNo());
            orderListVo.setCloseTime(DateTimeUtils.dateToStr(order.getCloseTime()));
            orderListVo.setEndTime(DateTimeUtils.dateToStr(order.getEndTime()));
            orderListVo.setPayment(order.getPayment().floatValue());
            orderListVo.setPaymentType(order.getPaymentType());
            orderListVo.setSendTime(DateTimeUtils.dateToStr(order.getSendTime()));
            orderListVo.setShippingId(order.getShippingId());
            orderListVo.setStatus(order.getStatus());
            orderListVoList.add(orderListVo);
        }
        return orderListVoList;
    }
}
