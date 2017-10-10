package com.ellenmall.service;

import com.ellenmall.common.ServerReponse;
import com.ellenmall.pojo.Category;

import java.util.List;

/**
 * Created by zhengying on 2017/9/29.
 */
public interface ICategoryService {
    ServerReponse addCategory(String cateName, Integer parentId);
    ServerReponse updateCateName(Integer cateId,String cateName);
    ServerReponse<List<Category>> getChildrenParallelCate(Integer cateId);
    ServerReponse selectCateAndChildrenById(Integer cateId);
}
