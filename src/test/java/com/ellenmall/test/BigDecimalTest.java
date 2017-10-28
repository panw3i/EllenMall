package com.ellenmall.test;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * Created by zhengying on 2017/10/13.
 */
public class BigDecimalTest {


    @Test
    public void test1(){
        System.out.println(0.01+0.05);
        System.out.println(1.001-0.02);
        System.out.println(1.02*0.05);
        System.out.println(123.4/1000);
    }

    @Test
    public void test2(){
        BigDecimal a  = new BigDecimal(0.01);
        BigDecimal b  = new BigDecimal(0.05);
        System.out.println();
        System.out.println( a.add(b));
    }

    @Test
    public void test3(){
        BigDecimal a  = new BigDecimal("0.01");
        BigDecimal b  = new BigDecimal("0.05");
        System.out.println();

        System.out.println(a.toPlainString());

        System.out.println( a.add(b));
    }



}
