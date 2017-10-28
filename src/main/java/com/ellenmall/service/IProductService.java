package com.ellenmall.service;

import com.ellenmall.common.ServerResponse;
import com.ellenmall.pojo.Product;
import com.github.pagehelper.PageInfo;

/**
 * Created by zhengying on 2017/10/10.
 */
public interface IProductService {

    ServerResponse saveOrUpdateProduct(Product product);
    ServerResponse setSaleStatus(Integer productId, Integer status);
    ServerResponse manageProductDetail(Integer productId);
    ServerResponse<PageInfo> manageProductList(Integer cateId, int pageNum, int pageSize);
    ServerResponse<PageInfo> manageSearchList(Integer product_id, String product_name, Integer pageNum, Integer pageSize);
    ServerResponse productDetail(Integer productId);
}
