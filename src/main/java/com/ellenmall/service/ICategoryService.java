package com.ellenmall.service;

import com.ellenmall.common.ServerResponse;
import com.ellenmall.pojo.Category;

import java.util.List;

/**
 * Created by zhengying on 2017/9/29.
 */
public interface ICategoryService {
    ServerResponse addCategory(String cateName, Integer parentId);
    ServerResponse updateCateName(Integer cateId, String cateName);
    ServerResponse<List<Category>> getChildrenParallelCate(Integer cateId);
    ServerResponse selectCateAndChildrenById(Integer cateId);
}
