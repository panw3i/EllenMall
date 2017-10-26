package com.ellenmall.dao;

import com.ellenmall.pojo.Shipping;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ShippingMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(Shipping record);

    int insertSelective(Shipping record);

    Shipping selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Shipping record);

    int updateByPrimaryKey(Shipping record);

    List<Shipping> selectShippingByUserId(Integer user_id);

    Shipping selectShippingByUserIdShippingId(@Param("user_id")Integer user_id ,@Param("shipping_id")Integer shipping_id);

}