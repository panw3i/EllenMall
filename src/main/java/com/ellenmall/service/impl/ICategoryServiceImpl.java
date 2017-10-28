package com.ellenmall.service.impl;

import com.ellenmall.common.ServerResponse;
import com.ellenmall.dao.CategoryMapper;
import com.ellenmall.pojo.Category;
import com.ellenmall.service.ICategoryService;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Set;

/**
 * Created by zhengying on 2017/9/29.
 * 类别管理服务
 */

@Service("iCategoryService")
public class ICategoryServiceImpl implements ICategoryService {

    private Logger logger = LoggerFactory.getLogger(ICategoryServiceImpl.class);
    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 添加分类
     * @param cateName
     * @param parentId
     * @return
     */
    public ServerResponse addCategory(String cateName, Integer parentId){

        //分类名称判空
        if(parentId == null || StringUtils.isBlank(cateName)){
            return ServerResponse.createByErrorMessage("商品品类参数错误");
        }
        Category category = new Category();
        category.setName(cateName);
        category.setParentId(parentId);
        category.setStatus(true);
        int rowCount = categoryMapper.insert(category);
        if(rowCount > 0){
            return ServerResponse.createBySuccess("添加品类成功");
        }else{
            return ServerResponse.createByErrorMessage("添加品类失败");
        }
    }

    /**
     * 更改分类名称
     * @param cateId
     * @param cateName
     * @return
     */
    public ServerResponse updateCateName(Integer cateId, String cateName){
        //分类名称判空
        if(cateId == null || StringUtils.isBlank(cateName)){
            return ServerResponse.createByErrorMessage("商品品类参数错误");
        }
        Category cate = new Category();
        cate.setId(cateId);
        cate.setName(cateName);
        int rowCount = categoryMapper.updateByPrimaryKeySelective(cate);
        if(rowCount>0){
            return ServerResponse.createBySuccessMessage("更新品类名成功");
        }
        return ServerResponse.createByErrorMessage("更新品类名失败");
    }

    /**
     *获取第一级子分类
     * @param cateId
     * @return
     */
    public ServerResponse<List<Category>> getChildrenParallelCate(Integer cateId){
        List<Category> categories = categoryMapper.selectCateChildrenByParentId(cateId);
        if(CollectionUtils.isEmpty(categories)){
            logger.info("未找到当前分类的子分类");
        }
        return ServerResponse.createBySuccess(categories);
    }

    /**
     * 递归所有子分类ID
     * @param cateId
     * @return
     */
    public ServerResponse selectCateAndChildrenById(Integer cateId) {
        Set<Category> categorySet = Sets.newHashSet();
        findChildCate(categorySet,cateId);
        List<Integer> cateIdList = Lists.newArrayList();
        if(cateId != null){
            for(Category categoryItem:categorySet){
                cateIdList.add(categoryItem.getId());
            }
        }
        return ServerResponse.createBySuccess(cateIdList);
    }

    //递归算法算出子节点
    private Set<Category> findChildCate(Set<Category> categories,Integer cateId){
        //
        Category category = categoryMapper.selectByPrimaryKey(cateId);
        if(category != null){
            categories.add(category);
        }
        //查找子节点 递归算法一定要有一个退出条件; 如果categoryList为空 就不会进入for循环 就退出了
        List<Category> categoryList = categoryMapper.selectCateChildrenByParentId(cateId);
        for(Category categoryItem:categoryList){
            findChildCate(categories,categoryItem.getId());
        }
        return categories;
    }


}
