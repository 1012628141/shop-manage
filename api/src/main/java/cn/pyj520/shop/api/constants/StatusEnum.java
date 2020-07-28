package cn.pyj520.shop.api.constants;

/**
 * @Description:
 * @Author: zjy
 * @Date: 2020-07-28 16:10
 */
public enum StatusEnum {

    ACTIVE(0),
    DELETE(1),
    FORBIDDEN(2);

    private int code;

    StatusEnum(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
