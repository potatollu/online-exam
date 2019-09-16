package com.ysjt.exam.common.enums;

import com.ysjt.exam.common.response.ResponseCode;

/**
 * @author yuxiaofei
 * <pre>
 *
 * </pre>
 * @date 2019/9/16 11:18
 */
public enum ExceptionMsg {

    USER_EXIT("用户已存在"),

    USER_NOT_FOUND("用户不存在"),

    USERNAME_OR_PASSWORD_ERROR("用户名或密码不正确");

    private int code;

    private String msg;

    public String getMsg() {
        return msg;
    }

    public int getCode() {
        return code;
    }

    ExceptionMsg(String msg) {
        this.msg = msg;
        this.code = ResponseCode.EXCEPTION.getCode();
    }

    ExceptionMsg(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
