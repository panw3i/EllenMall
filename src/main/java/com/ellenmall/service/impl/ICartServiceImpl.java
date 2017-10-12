package com.ellenmall.service.impl;

import com.ellenmall.common.Constants;
import com.ellenmall.common.ServerReponse;
import com.ellenmall.dao.CartMapper;
import com.ellenmall.dao.ProductMapper;
import com.ellenmall.pojo.Cart;
import com.ellenmall.pojo.Product;
import com.ellenmall.service.ICartService;
import com.ellenmall.util.PropertiesUtil;
import com.ellenmall.vo.CartProductListVo;
import com.ellenmall.vo.CartVo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
    public ServerReponse<CartVo> add(Integer productId, Integer count){
        Cart cart = cartMapper.selectByProductId(productId);
        if(cart == null){//购物车中还没有这个商品
            cart.setChecked(Constants.Cart.CART_CHECK);
            cart.setQuantity(count);
            cartMapper.insert(cart);
        }else {//购物车中已经有这个商品了
            cart.setQuantity(cart.getQuantity()+count);
        }
        List<Cart> list = Lists.newArrayList();
        list.add(cart);
        return ServerReponse.createBySuccess(getCartVo(list));
    }

    /**
     * 购物车列表
     * @return
     */
    public ServerReponse list(){
        List<Cart> cartList = cartMapper.selectCart();
        return ServerReponse.createBySuccess(getCartVo(cartList));
    }

    /**
     * 删除商品
     * @param productIds
     * @return
     */
    public ServerReponse delete_product(String productIds){
        return null;
    }

    /**
     * 选择商品
     * @param id
     * @return
     */
    public ServerReponse select(Integer id){
        return null;
    }

    /**
     * 全选商品
     * @return
     */
    public ServerReponse selectAll(){
        return null;
    }

    /**
     * 取消选中
     * @param id
     * @return
     */
    public ServerReponse unselect(Integer id){
        return null;
    }

    /**
     * 全部取消选中
     * @return
     */
    public ServerReponse unselectAll(){
        return null;
    }

    /**
     * 更新
     * @param id
     * @return
     */
    public ServerReponse unpdate(Integer id){
        return null;
    }

    public ServerReponse getCartProductCount(){
        return null;
    }

    private CartVo getCartVo(List<Cart> cartList){
        CartVo cartVo = new CartVo();
        cartVo.setCartProductListVo(getCartProductVoList(cartList));
        cartVo.setAllChecked(false);
        cartVo.setCartTotalPrice(cartVo.getCartTotalPrice());
        cartVo.setImgHost(PropertiesUtil.getProperty("ftp.server.http.prefix"));
        return cartVo;
    }

    private List<CartProductListVo> getCartProductVoList(List<Cart> cartList){
        List<CartProductListVo> cartProductListVos = Lists.newArrayList();
        for(Cart cart:cartList){
            CartProductListVo cartProductListVo = new CartProductListVo();
            Product product = productMapper.selectByPrimaryKey(cart.getProductId());
            cartProductListVo.setId(cart.getId());
            cartProductListVo.setProductChecked(cart.getChecked());
            cartProductListVo.setProductMainImage(product.getMainImage());
            cartProductListVo.setProductPrice(product.getPrice());
            cartProductListVo.setProductStock(product.getStock());
            cartProductListVo.setProductSubtitle(product.getSubtitle());
            cartProductListVo.setProductName(product.getName());
            cartProductListVo.setProductTotalPrice(new BigDecimal(1));
            cartProductListVos.add(cartProductListVo);
        }
        return cartProductListVos;
    }

}
