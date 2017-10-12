package com.ellenmall.service;

import com.ellenmall.common.ServerReponse;
import com.ellenmall.pojo.Product;
import com.github.pagehelper.PageInfo;

/**
 * Created by zhengying on 2017/10/10.
 */
public interface IProductService {

    ServerReponse saveOrUpdateProduct(Product product);
    ServerReponse setSaleStatus(Integer productId,Integer status);
    ServerReponse manageProductDetail(Integer productId);
    ServerReponse<PageInfo> manageProductList(Integer cateId,int pageNum, int pageSize);
    ServerReponse<PageInfo> manageSearchList(Integer product_id,String product_name,Integer pageNum,Integer pageSize);
    ServerReponse productDetail(Integer productId);
}
