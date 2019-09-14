package com.ysjt.exam.exception.base;

import com.ysjt.exam.common.enums.ExceptionMsg;
import com.ysjt.exam.common.response.ResponseCode;

public class BusinessException extends RuntimeException {

    private int code;

    public int getCode() {
        return code;
    }

    private BusinessException() {
        super();
    }

    public BusinessException(String msg) {
        super(msg);
        this.code = ResponseCode.EXCEPTION.getCode();
    }

    public BusinessException(ExceptionMsg msg) {
        super(msg.getMsg());
        this.code = msg.getCode();
    }

    public BusinessException(ResponseCode code) {
        super(code.getMsg());
        this.code = code.getCode();
    }

}
