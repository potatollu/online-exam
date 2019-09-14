package com.ysjt.exam.exception;

import com.ysjt.exam.common.response.Response;
import com.ysjt.exam.exception.base.BusinessException;
import org.omg.CORBA.SystemException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

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
