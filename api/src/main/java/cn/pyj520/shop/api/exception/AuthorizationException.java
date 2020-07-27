package cn.pyj520.shop.api.exception;

/**
 * @Description:
 * @author: ZJY
 * @date: 2019/5/25 17:37
 */
public class AuthorizationException extends BaseException {

    public AuthorizationException() {
        super();
    }

    public AuthorizationException(String errorMessage) {
        super(errorMessage);
    }

}
