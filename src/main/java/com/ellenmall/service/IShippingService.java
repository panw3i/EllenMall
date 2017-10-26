package com.ellenmall.service;

import com.ellenmall.common.ServerReponse;
import com.ellenmall.pojo.Shipping;

/**
 * Created by zhengying on 2017/10/12.
 */
public interface IShippingService {

    ServerReponse add(Shipping shipping);
    ServerReponse list(int userId);
    ServerReponse update(Shipping shipping);
    ServerReponse select(int userId,int shippingId);
    ServerReponse del(int userId,int shippingId);

}
