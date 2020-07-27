package cn.pyj520.shop.api.exception;

import java.io.IOException;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-03-19 09:27
 */
public class BaseException extends IOException {

    public BaseException() {
    }

    public BaseException(String msg) {
        super(msg);
    }
}