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

    public interface Order{
        //订单状态
        int STATUS_WAIT_PAY = 1;
        int STATUS_PAYED = 2;
        int STATUS_SENDED = 3;
        int STATUS_CANCELED = 4;
        int STATUS_DELETED = 5;
        int PAY_TYPE_ONLINE = 6;

        //支付方式
        int PAY_ALI = 1;
        int PAY_WX = 2;
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

    public enum PaymentTypeEnum{
        ONLINE_PAY(1,"在线支付");

        PaymentTypeEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }


        public static PaymentTypeEnum codeOf(int code){
            for(PaymentTypeEnum paymentTypeEnum : values()){
                if(paymentTypeEnum.getCode() == code){
                    return paymentTypeEnum;
                }
            }
            throw new RuntimeException("么有找到对应的枚举");
        }

    }

    public interface  AlipayCallback{
        String TRADE_STATUS_WAIT_BUYER_PAY = "WAIT_BUYER_PAY";
        String TRADE_STATUS_TRADE_SUCCESS = "TRADE_SUCCESS";

        String RESPONSE_SUCCESS = "success";
        String RESPONSE_FAILED = "failed";
    }

    public enum PayPlatformEnum{
        ALIPAY(1,"支付宝");

        PayPlatformEnum(int code,String value){
            this.code = code;
            this.value = value;
        }
        private String value;
        private int code;

        public String getValue() {
            return value;
        }

        public int getCode() {
            return code;
        }
    }


}
