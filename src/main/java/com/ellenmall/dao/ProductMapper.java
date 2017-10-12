package com.ellenmall.dao;

import com.ellenmall.pojo.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> selectProductListByCateId(Integer category_id);

    List<Product> selectByNameAndProductId(@Param("product_id")Integer product_id,@Param("product_name")String product_name);
}