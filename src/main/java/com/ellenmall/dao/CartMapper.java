package com.ellenmall.dao;

import com.ellenmall.pojo.Cart;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CartMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Cart record);

    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);

    List<Cart> selectCartByUserId(Integer user_id);

    Cart selectCartByUserIdProductId(@Param("user_id")Integer user_id, @Param("product_id")Integer product_id);

    int selectCartProductCheckStatusByUserId(Integer userId);

    int selectCountByUserId(Integer userId);

    List<Cart> selectCheckedCartByUserId(Integer userId);

}