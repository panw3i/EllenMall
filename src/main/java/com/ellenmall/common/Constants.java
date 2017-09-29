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
}
