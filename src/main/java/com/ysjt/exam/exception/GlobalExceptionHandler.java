package com.ysjt.exam.exception;

import com.ysjt.exam.common.response.Response;
import com.ysjt.exam.common.response.ResponseCode;
import com.ysjt.exam.exception.base.BusinessException;
import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;

/**
 * @author yuxiaofei
 * <pre>
 *
 * </pre>
 * @date 2019/9/16 11:18
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);


    /**
     * 拦截全局异常
     */
    @ExceptionHandler(Exception.class)
    public Response exception(Exception e) {
        return Response.fail(e.getMessage());
    }

    /**
     * 拦截@RequestHeader获取用户信息时，token不合法返回401
     * @param e
     * @return
     */
    @ExceptionHandler(ServletRequestBindingException.class)
    public Response exception(ServletRequestBindingException e) {
        if (e.getMessage().startsWith("Missing")) {
            return Response.fail(ResponseCode.AUTH_FAIL.getCode(), ResponseCode.AUTH_FAIL.getMsg());
        }
        return Response.fail(e.getMessage());
    }

    /**
     * 拦截系统异常
     */
    @ExceptionHandler(SystemException.class)
    public Response systemException(SystemException e) {
        return Response.fail(e.getMessage());
    }

    /**
     * 拦截业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Response businessException(BusinessException e) {
        return Response.fail(e.getCode(), e.getMessage());
    }
}
