package com.ysjt.exam.common.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yuxiaofei
 * <pre>
 *
 * </pre>
 * @date 2019/9/16 11:18
 */
@Data
public class Response implements Serializable {

    private int code;

    private String msg;

    private String detailMsg;

    public Response() {}

    protected Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
        this.detailMsg = msg;
    }

    public static ResponseX ok(Object data) {
        return ResponseX.ok(data);
    }

    public static Response ok() {
        return new Response(200, "success");
    }

    public static Response fail(String msg) {
        return new Response(500, msg);
    }

    public static Response fail(int code, String msg) {
        return new Response(code, msg);
    }

    public static Response fail() {
        return new Response(500, "fail");
    }
}
