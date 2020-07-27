/**
 *
 */
package cn.pyj520.shop.api.constants;

import cn.pyj520.shop.api.util.NullUtil;

import java.util.HashMap;
import java.util.Map;


public enum NetworkCode {

    CODE_SUCCESS(0, "请求成功"),

    REFRESH_TOKEN_EXPIRED(1000, "REFRESH-TOKEN失效"),

    ACCESS_TOKEN_EXPIRED(1001, "ACCESS-TOKEN失效"),

    CODE_SYS_FAIL(1002, "系统错误"),

    PARM_ERROR(1003, "参数错误"),

    DATA_IS_EMPTY(1004, "参数个数错误"),

    CODE_REQ_FAIL(1005, "参数个数错误"),

    NO_PERMISSION(1006, "对资源访问权限不足"),

    NO_LOGIN(1007, "用户未登录"),


    ACCOUNT_FORMAT_ERROR(100000, "账号格式不正确"),

    ACCOUNT_BE_REGISTER(100001, "账号已经被注册"),

    ACCOUNT_PASSWORD_ERROR(100002, "账号或者密码错误"),

    ACCOUNT_NO_REGISTER(100003, "账号未注册"),

    ACCOUNT_NOT_ACTIVED(100005, "账号未激活"),

    ACCOUNT_IS_ACTIVED(100006, "账号已经激活状态"),

    ORIGINAL_PASSWORD_ERROR(100007, "原密码错误"),

    CODE_IS_USED(100008, "验证码已经被使用");


    private final Integer code;
    private final String value;

    NetworkCode(Integer code, String value) {
        this.code = code;
        this.value = value;

    }

    public Integer getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    private static Map<Integer, NetworkCode> maps = new HashMap<Integer, NetworkCode>();

    static {
        for (NetworkCode item : NetworkCode.values()) {
            maps.put(item.getCode(), item);
        }
    }

    public static NetworkCode getByCode(final Integer code) {
        Integer localCode = code;
        if (!NullUtil.isNullObject(localCode)) {
            NetworkCode NetworkCodeEnum = maps.get(localCode);
            return NetworkCodeEnum;
        }
        return null;
    }

}
