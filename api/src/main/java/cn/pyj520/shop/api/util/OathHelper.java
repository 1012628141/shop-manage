package cn.pyj520.shop.api.util;

import cn.pyj520.shop.api.interceptor.LoginInterceptor;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-27 18:23
 */
public class OathHelper {


    public static Integer getUserId() {
        return LoginInterceptor.threadLocal.get().getId();
    }

}