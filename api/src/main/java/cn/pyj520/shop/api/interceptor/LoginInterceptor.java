package cn.pyj520.shop.api.interceptor;


import cn.pyj520.shop.api.constants.HeaderConstants;
import cn.pyj520.shop.api.exception.AuthorizationException;
import cn.pyj520.shop.api.model.RequestParam;
import cn.pyj520.shop.api.util.JWTUtil;
import cn.pyj520.shop.api.util.NullUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 登录认证的拦截器
 */

@Component
public class LoginInterceptor implements HandlerInterceptor {

    //要将springboot默认的全局错误页面的地址放出来
    public final static String error = ".*/error";


    public static ThreadLocal<RequestParam> threadLocal = new ThreadLocal();


    /*允许通过的地址*/
    private final static String[] notFilter = new String[]{
            error,
            ".*/test/.*",
            ".*/login.*"
            /*测试方法，实际要注释*/
    };

    /**
     * Handler执行之前调用这个方法
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        String url = request.getRequestURI();
        for (String s : notFilter) {
            if (url.matches(s)) {
                return true;
            }
        }
        String accessToken = request.getHeader(HeaderConstants.ACCESS_TOKEN);
        if (NullUtil.isNullObject(accessToken)) {
            throw new AuthorizationException("缺少AcessToken");
        }

        Integer uid = JWTUtil.verifyJwtByType(accessToken, HeaderConstants.ACCESS_TOKEN);
        if (NullUtil.isNullObject(uid)) {
            throw new AuthorizationException("AccessToken失效");
        }
       // request.setAttribute(USER_ID, uid);//将解密的用户id放进request域，在head中取
        //设置参数
        RequestParam requestParam = new RequestParam();
        requestParam.setId(uid);
        threadLocal.set(requestParam);
        return true;
    }



}