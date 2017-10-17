package com.ellenmall.service;

import com.ellenmall.common.ServerReponse;
import com.ellenmall.vo.CartVo;

/**
 * Created by zhengying on 2017/10/12.
 */
public interface ICartService {

    ServerReponse<CartVo> add(Integer userId,Integer productId, Integer count);
    ServerReponse delete_product(Integer userId,String productIds);
    ServerReponse select(Integer userId,Integer id);
    ServerReponse selectAll(Integer userId);
    ServerReponse unselect(Integer userId,Integer id);
    ServerReponse unselectAll(Integer userId);
    ServerReponse getCartProductCount(Integer userId);
    ServerReponse list(Integer userId,Integer pageNum,Integer pageSize);

}
