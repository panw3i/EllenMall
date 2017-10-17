package com.ellenmall.vo;

import java.util.List;

/**
 * Created by zhengying on 2017/10/12.
 */
public class ShippingVo {

    private List<ShippingListVo> shippingListVoList;
    private int totalCount;

    public List<ShippingListVo> getShippingListVoList() {
        return shippingListVoList;
    }

    public void setShippingListVoList(List<ShippingListVo> shippingListVoList) {
        this.shippingListVoList = shippingListVoList;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
