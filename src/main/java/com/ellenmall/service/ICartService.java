package com.ellenmall.service;

import com.ellenmall.common.ServerResponse;
import com.ellenmall.vo.CartVo;

/**
 * Created by zhengying on 2017/10/12.
 */
public interface ICartService {

    ServerResponse<CartVo> add(Integer userId, Integer productId, Integer count);
    ServerResponse delete_product(Integer userId, String productIds);
    ServerResponse select(Integer userId, Integer id);
    ServerResponse selectAll(Integer userId);
    ServerResponse unselect(Integer userId, Integer id);
    ServerResponse unselectAll(Integer userId);
    ServerResponse getCartProductCount(Integer userId);
    ServerResponse list(Integer userId, Integer pageNum, Integer pageSize);

}
