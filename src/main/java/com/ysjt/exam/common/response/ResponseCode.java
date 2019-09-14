package com.ysjt.exam.common.response;

public enum ResponseCode {

    OK(200, "success"),

    AUTH_FAIL(401, "auth fail"),

    EXCEPTION(500, "exception");

    private int code;

    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
