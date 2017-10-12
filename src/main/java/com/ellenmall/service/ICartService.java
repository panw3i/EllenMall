package com.ellenmall.service;

import com.ellenmall.common.ServerReponse;
import com.ellenmall.vo.CartVo;

/**
 * Created by zhengying on 2017/10/12.
 */
public interface ICartService {

    ServerReponse<CartVo> add(Integer productId, Integer count);
    ServerReponse list();
    ServerReponse delete_product(String productIds);
    ServerReponse select(Integer id);
    ServerReponse selectAll();
    ServerReponse unselect(Integer id);
    ServerReponse unselectAll();
    ServerReponse unpdate(Integer id);
    ServerReponse getCartProductCount();
}
