package com.ellenmall.common;

/**
 * Created by zhengying on 2017/9/29.
 */
public class Constants {

    public static final String CURRENT_USER = "current_user";

    public static final String EMAIL = "email";
    public static final String USERNAME="username";

    public interface Role {
        int ROLE_CUSTOM =0;
        int ROLE_ADMIN= 1;
    }

    public interface Cart{
        int CART_CHECK = 1;
        int CART_UNCHECK = 0;
        String ADD_TO_CART_FAIL = "加入购物车失败";
        int LIMIT_QUANTITY_TRUE = 0;
        int LIMIT_QUANTITY_FALSE = 1;
        int ALL_CHECKED= 0;

    }

    public enum ProductStatusEnum{

        ON_SALE(1,"在线");

        private String value;
        private int code;

        ProductStatusEnum(int code,String value){
            this.value = value;
            this.code = code;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }
    }


}
