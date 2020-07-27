package cn.pyj520.shop.api.config;


import cn.pyj520.shop.api.constants.NetworkCode;
import cn.pyj520.shop.api.exception.AuthorizationException;
import cn.pyj520.shop.api.exception.BaseException;
import cn.pyj520.shop.api.util.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/*
 * @Author: ZJY on 2019/11/8 15:45
 * @Description: 应客户端要求，
 */
@Slf4j
@Controller
@ControllerAdvice
public class ExceptionHandlerConfig implements ErrorController {


    public static final String ERROR_PATH = "/error";


    /**
     * @Author: ZJY on 2019/9/12 15:17
     * @Description: 因为父类定义MethodArgumentNotValidException异常
     * 重写相关方法，处理@Valid抛出的异常信息
     */

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> onException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        String defaultMessage = bindingResult.getAllErrors().get(0).getDefaultMessage();
        log.info("[参数校验异常" + defaultMessage);
        return JsonResult.toEntity(NetworkCode.PARM_ERROR, defaultMessage);
    }

    @ExceptionHandler(AuthorizationException.class)
    public ResponseEntity<Object> onException(AuthorizationException ex) {
        String message = ex.getMessage();
        log.info(message);
        return JsonResult.toEntity(NetworkCode.ACCESS_TOKEN_EXPIRED, message);
    }

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Object> onException(BaseException ex) {
        String message = ex.getMessage();
        log.info(message);
        return JsonResult.toEntity(NetworkCode.CODE_SYS_FAIL, message);
    }

    /**
     * @Author: ZJY on 2019/9/12 15:57
     * @Description: 实现ErrorController，将所有原ResponseEntityExceptionHandler经过转发到erorr路径的异常全部在
     * * 此方法中处理，对于4类http协议状态码进行单独处理
     */
    @RequestMapping(value = ERROR_PATH)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<String> onException(Exception e, HttpServletRequest request) {

        e.printStackTrace();
        return JsonResult.toEntity(NetworkCode.CODE_SYS_FAIL);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    private int getStatus(HttpServletRequest request) {
        Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (status != null) {
            return status;
        }
        return 500;
    }
}
