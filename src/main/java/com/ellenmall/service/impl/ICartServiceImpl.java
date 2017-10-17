package com.ellenmall.service.impl;

import com.ellenmall.common.Constants;
import com.ellenmall.common.ServerReponse;
import com.ellenmall.dao.CartMapper;
import com.ellenmall.dao.ProductMapper;
import com.ellenmall.pojo.Cart;
import com.ellenmall.pojo.Product;
import com.ellenmall.service.ICartService;
import com.ellenmall.util.BigDecimalUtils;
import com.ellenmall.util.PropertiesUtil;
import com.ellenmall.vo.CartProductListVo;
import com.ellenmall.vo.CartVo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhengying on 2017/10/12.
 */
@Service("iCartService")
public class ICartServiceImpl implements ICartService {

    @Autowired
    CartMapper cartMapper;
    @Autowired
    ProductMapper productMapper;

    /**
     * 加入购物车
     * @param productId
     * @param count
     * @return
     */
    public ServerReponse<CartVo> add(Integer userId, Integer productId, Integer count){
        Cart cart = cartMapper.selectCartByUserIdProductId(userId,productId);
        if(cart == null){//购物车中还没有这个商品 加入购物车
            cart = new Cart();
            cart.setProductId(productId);
            cart.setUserId(userId);
            cart.setChecked(Constants.Cart.CART_CHECK);
            cart.setQuantity(count);
            int result = cartMapper.insert(cart);
            if(result>0){
                return ServerReponse.createBySuccessMessage("加入购物车成功");
            }else{
                return ServerReponse.createByErrorMessage(Constants.Cart.ADD_TO_CART_FAIL);
            }
        }else {//购物车中已经有这个商品了 更新这个购物车产品
            cart.setQuantity(cart.getQuantity().intValue()+count);
            int result = cartMapper.updateByPrimaryKeySelective(cart);
            if(result>0){
                return ServerReponse.createBySuccessMessage("加入购物车成功");
            }else{
                return ServerReponse.createByErrorMessage(Constants.Cart.ADD_TO_CART_FAIL);
            }
        }
    }

    /**
     * 购物车列表
     * @return
     */
    public ServerReponse list(Integer userId,Integer pageNum,Integer pageSize){
//        PageHelper.startPage(pageNum,pageSize);
        List<Cart> cartList = cartMapper.selectCartByUserId(userId);
        List<CartProductListVo> cartProductListVos = getCartListVoList(cartList);
//        PageInfo pageInfo = new PageInfo(cartProductListVos);
//        pageInfo.setList(cartProductListVos);

        return ServerReponse.createBySuccess(getCartVo(cartProductListVos,userId));
    }

    /**
     * 选择商品
     * @param id
     * @return
     */
    public ServerReponse select(Integer userId,Integer id){
        Cart cart = cartMapper.selectByPrimaryKey(id);
        cart.setChecked(Constants.Cart.CART_CHECK);
        int result = cartMapper.updateByPrimaryKey(cart);
        if(result > 0){
            return ServerReponse.createBySuccess();
        }else{
            return ServerReponse.createByErrorMessage("操作失败");
        }
    }

    /**
     * 全选商品
     * @return
     */
    public ServerReponse selectAll(Integer userId){
        List<Cart> cartList = cartMapper.selectCartByUserId(userId);
        for(Cart cart:cartList){
            cart.setChecked(Constants.Cart.CART_CHECK);
            int result = cartMapper.updateByPrimaryKey(cart);
            if(result == 0){
                return ServerReponse.createByErrorMessage("操作失败");
            }
        }
        return ServerReponse.createBySuccess();
    }

    /**
     * 取消选中
     * @param id
     * @return
     */
    public ServerReponse unselect(Integer userId,Integer id){
        Cart cart = cartMapper.selectByPrimaryKey(id);
        cart.setChecked(Constants.Cart.CART_UNCHECK);
        int result = cartMapper.updateByPrimaryKey(cart);
        if(result > 0){
            return ServerReponse.createBySuccess();
        }else{
            return ServerReponse.createByErrorMessage("操作失败");
        }
    }

    /**
     * 全部取消选中
     * @return
     */
    public ServerReponse unselectAll(Integer userId){
        List<Cart> cartList = cartMapper.selectCartByUserId(userId);
        for(Cart cart:cartList){
            cart.setChecked(Constants.Cart.CART_UNCHECK);
            int result = cartMapper.updateByPrimaryKey(cart);
            if(result == 0){
                return ServerReponse.createByErrorMessage("操作失败");
            }
        }
        return ServerReponse.createBySuccess();
    }


    /**
     * 获取购物车商品数量
     * @param userId
     * @return
     */
    public ServerReponse getCartProductCount(Integer userId){
        int count = cartMapper.selectCountByUserId(userId);
        return ServerReponse.createBySuccessMessage(String.valueOf(count));
    }

    /**
     * 删除商品
     * @param cartProductIds
     * @return
     */
    public ServerReponse delete_product(Integer userId, String cartProductIds){
        int[] cartIds = com.ellenmall.util.StringUtils.strSplitToIntArr(cartProductIds,",");
        for(Integer cartId:cartIds){
            if(cartId!=0){
                int result = cartMapper.deleteByPrimaryKey(cartId);
                if(result==0){
                    return ServerReponse.createByErrorMessage("删除失败");
                }
            }
        }
        return ServerReponse.createBySuccess();
    }

    private boolean isAllChecked(Integer userId){
        int selectCount = cartMapper.selectCartProductCheckStatusByUserId(userId);
        int allCount = cartMapper.selectCountByUserId(userId);
        if(selectCount==allCount){
            return true;
        }
        return false;
    }


    private CartVo getCartVo(List<CartProductListVo> cartList,Integer userId){
        CartVo cartVo = new CartVo();
        cartVo.setCartProductListVo(cartList);
        cartVo.setAllChecked(isAllChecked(userId));
        cartVo.setCartTotalPrice(cartVo.getCartTotalPrice());
        cartVo.setImgHost(PropertiesUtil.getProperty("ftp.server.http.prefix"));
        return cartVo;
    }

    private List<CartProductListVo> getCartListVoList(List<Cart> cartList){
        List<CartProductListVo> cartProductListVos = Lists.newArrayList();
        CartProductListVo cartProductListVo;
        for(Cart cart:cartList){
            cartProductListVo = new CartProductListVo();
            Product product = productMapper.selectByPrimaryKey(cart.getProductId());
            if(product!=null){
                cartProductListVo.setId(cart.getId());
                if(product.getStock().intValue()>cart.getQuantity().intValue()){
                    cartProductListVo.setQuantity(cart.getQuantity());
                    cartProductListVo.setLimitQuantity(Constants.Cart.LIMIT_QUANTITY_TRUE);
                }else{
                    cartProductListVo.setQuantity(product.getStock());
                    cartProductListVo.setLimitQuantity(Constants.Cart.LIMIT_QUANTITY_FALSE);
                }
                cartProductListVo.setProductChecked(cart.getChecked());
                cartProductListVo.setProductMainImage(product.getMainImage());
                cartProductListVo.setProductPrice(product.getPrice());
                cartProductListVo.setProductStock(product.getStock());
                cartProductListVo.setProductSubtitle(product.getSubtitle());
                cartProductListVo.setProductName(product.getName());
                cartProductListVo.setProductTotalPrice(BigDecimalUtils.mul(product.getPrice().doubleValue(),cart.getQuantity().doubleValue()));
                cartProductListVos.add(cartProductListVo);
            }
        }
        return cartProductListVos;
    }


}
