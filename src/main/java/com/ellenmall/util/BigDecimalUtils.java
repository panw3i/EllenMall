package com.ellenmall.util;

import java.math.BigDecimal;

/**
 * Created by zhengying on 2017/10/13.
 */
public class BigDecimalUtils {

    /**
     * 多个bigDecimals求和
     * @param doubles
     * @return
     */
    public static BigDecimal sum(Double ...doubles){
        BigDecimal sum = new BigDecimal("0");
        for (Double doub:doubles){
            BigDecimal bigDecimal = new BigDecimal(doub.toString());
            sum  = sum.add(bigDecimal);
        }
        return sum;
    }

    public static BigDecimal mul(Double a,Double b){
        BigDecimal a1 = new BigDecimal(a.toString());
        BigDecimal b1 = new BigDecimal(b.toString());
        return a1.multiply(b1);
    }
}
