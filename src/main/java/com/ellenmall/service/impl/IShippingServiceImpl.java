package com.ellenmall.service.impl;

import com.ellenmall.common.ServerResponse;
import com.ellenmall.dao.ShippingMapper;
import com.ellenmall.pojo.Shipping;
import com.ellenmall.service.IShippingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhengying on 2017/10/12.
 */

@Service("iShippingService") //这个注解的名字暂时没发现有什么用
public class IShippingServiceImpl implements IShippingService {

    @Autowired
    ShippingMapper shippingMapper;

    /**
     * 新增地址
     * @param shipping
     * @return
     */
    public ServerResponse add(Shipping shipping){
        int result = shippingMapper.insert(shipping);
        if(result > 0){
            return ServerResponse.createBySuccess("新增地址成功");
        }else{
            return ServerResponse.createByErrorMessage("插入数据库失败");
        }
    }

    /**
     * 地址列表
     * @param userId
     * @return
     */
    public ServerResponse list(int userId){
        List<Shipping> shippingList = shippingMapper.selectShippingByUserId(userId);
        return ServerResponse.createBySuccess(shippingList);
    }

    /**
     * 更新地址
     * @param shipping
     * @return
     */
    public ServerResponse update(Shipping shipping){
        int result = shippingMapper.updateByPrimaryKeySelective(shipping);
        if(result>0){
            return ServerResponse.createBySuccessMessage("更新成功");
        }else{
            return ServerResponse.createByErrorMessage("更新失败");
        }
    }

    /**
     * 选择地址
     * @param userId
     * @param shippingId
     * @return
     */
    public ServerResponse select(int userId, int shippingId){
        Shipping shipping = shippingMapper.selectShippingByUserIdShippingId(userId,shippingId);
        if(shipping == null){
            return ServerResponse.createByErrorMessage("地址不存在");
        }
        return ServerResponse.createBySuccess(shipping);
    }

    /**
     * 删除地址
     * @param userId
     * @param shippingId
     * @return
     */
    public ServerResponse del(int userId, int shippingId){
        int result = shippingMapper.deleteByPrimaryKey(shippingId);
        if(result>0){
            return ServerResponse.createBySuccessMessage("删除成功");
        }else{
            return ServerResponse.createByErrorMessage("删除失败");
        }
    }

}
