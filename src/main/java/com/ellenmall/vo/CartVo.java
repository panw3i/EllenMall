package com.ellenmall.vo;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by zhengying on 2017/10/12.
 */
public class CartVo {

    private List<CartProductListVo> cartProductListVo;
    private boolean allChecked;
    private BigDecimal cartTotalPrice;
    private String imgHost;



    public List<CartProductListVo> getCartProductListVo() {
        return cartProductListVo;
    }

    public void setCartProductListVo(List<CartProductListVo> cartProductListVo) {
        this.cartProductListVo = cartProductListVo;
    }

    public boolean isAllChecked() {
        return allChecked;
    }

    public void setAllChecked(boolean allChecked) {
        this.allChecked = allChecked;
    }

    public BigDecimal getCartTotalPrice() {
        return cartTotalPrice;
    }

    public void setCartTotalPrice(BigDecimal cartTotalPrice) {
        this.cartTotalPrice = cartTotalPrice;
    }

    public String getImgHost() {
        return imgHost;
    }

    public void setImgHost(String imgHost) {
        this.imgHost = imgHost;
    }
}
