package com.ellenmall.service;

import com.ellenmall.common.ServerResponse;
import com.ellenmall.pojo.Shipping;

/**
 * Created by zhengying on 2017/10/12.
 */
public interface IShippingService {

    ServerResponse add(Shipping shipping);
    ServerResponse list(int userId);
    ServerResponse update(Shipping shipping);
    ServerResponse select(int userId, int shippingId);
    ServerResponse del(int userId, int shippingId);

}
