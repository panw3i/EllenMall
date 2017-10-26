package com.ellenmall.service.impl;

import com.ellenmall.common.ServerReponse;
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
    public ServerReponse add( Shipping shipping){
        int result = shippingMapper.insert(shipping);
        if(result > 0){
            return ServerReponse.createBySuccess("新增地址成功");
        }else{
            return ServerReponse.createByErrorMessage("插入数据库失败");
        }
    }

    /**
     * 地址列表
     * @param userId
     * @return
     */
    public ServerReponse list(int userId){
        List<Shipping> shippingList = shippingMapper.selectShippingByUserId(userId);
        return ServerReponse.createBySuccess(shippingList);
    }

    /**
     * 更新地址
     * @param shipping
     * @return
     */
    public ServerReponse update(Shipping shipping){
        int result = shippingMapper.updateByPrimaryKeySelective(shipping);
        if(result>0){
            return ServerReponse.createBySuccessMessage("更新成功");
        }else{
            return ServerReponse.createByErrorMessage("更新失败");
        }
    }

    /**
     * 选择地址
     * @param userId
     * @param shippingId
     * @return
     */
    public ServerReponse select(int userId,int shippingId){
        Shipping shipping = shippingMapper.selectShippingByUserIdShippingId(userId,shippingId);
        if(shipping == null){
            return ServerReponse.createByErrorMessage("地址不存在");
        }
        return ServerReponse.createBySuccess(shipping);
    }

    /**
     * 删除地址
     * @param userId
     * @param shippingId
     * @return
     */
    public ServerReponse del(int userId,int shippingId){
        int result = shippingMapper.deleteByPrimaryKey(shippingId);
        if(result>0){
            return ServerReponse.createBySuccessMessage("删除成功");
        }else{
            return ServerReponse.createByErrorMessage("删除失败");
        }
    }

}
