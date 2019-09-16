package com.ysjt.exam.common.response;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author yuxiaofei
 * <pre>
 *
 * </pre>
 * @date 2019/9/16 11:18
 */
@EqualsAndHashCode(callSuper = true)
@Data
public final class ResponseX extends Response implements Serializable {

    private Object data;

    private ResponseX(Object data, int code, String msg) {
        super(code, msg);
        this.data = data;
    }

    public static ResponseX ok(Object data) {
        return new ResponseX(data, 200, "success");
    }

}
