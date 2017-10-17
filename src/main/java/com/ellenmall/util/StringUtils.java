package com.ellenmall.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zhengying on 2017/10/13.
 */
public class StringUtils {

    private static Logger logger = LoggerFactory.getLogger(StringUtils.class);

    /**
     *字符串分割成int类型数组
     * @param str
     * @return
     */
    public  static int[] strSplitToIntArr(String str,String regex){
        String[] strArr = str.split(regex);
        int[] intArr = new int[]{};
        try {
            for(int i = 0; i<strArr.length;i++){
                intArr[i] = Integer.parseInt(strArr[i]);
            }
        }catch (Exception e){
            e.printStackTrace();
            logger.error("str split Error",e.getMessage());
        }
        return intArr;
    }
}
