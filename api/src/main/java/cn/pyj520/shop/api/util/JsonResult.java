package cn.pyj520.shop.api.util;


import cn.pyj520.shop.api.constants.NetworkCode;
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回接口定义
 *
 * @author
 */
public class JsonResult {

    private Integer code;
    private String msg;
    private Object data;  // 返回对象

    public JsonResult() {
    }

    public JsonResult(Integer code, Object data) {
        this.code = code;
        this.msg = "";
        this.data = data;
    }

    public JsonResult(Integer code, String message, Object data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }

    public static String toString(NetworkCode networkCode, Object object, String msg) {
        return JSON.toJSONString(new JsonResult(networkCode.getCode(), msg, object));
    }

    public static String toString(NetworkCode networkCode, Object object) {
        return JSON.toJSONString(new JsonResult(networkCode.getCode(), networkCode.getValue(), object));
    }

    public static String toString(NetworkCode networkCode) {
        return JSON.toJSONString(new JsonResult(networkCode.getCode(), networkCode.getValue(), null));
    }

    public static String toString(NetworkCode networkCode, String msg) {
        return JSON.toJSONString(new JsonResult(networkCode.getCode(), msg, null));
    }


    public static ResponseEntity toEntity(NetworkCode networkCode) {
        return toEntity(networkCode, networkCode.getValue());
    }

    public static ResponseEntity toEntity(NetworkCode code, String message) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", String.valueOf(code));
        map.put("msg", message == null ? "" : message);
        //错误信息全部发邮件
        return new ResponseEntity<>(map, HttpStatus.OK);
    }


    public Integer getCode() {
        return code;
    }


    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String message) {
        this.msg = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}