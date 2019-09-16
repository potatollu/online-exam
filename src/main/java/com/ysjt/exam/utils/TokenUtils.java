package com.ysjt.exam.utils;

import java.util.UUID;

/**
 * @author yuxiaofei
 * <pre>
 *
 * </pre>
 * @date 2019/9/16 11:18
 */
public class TokenUtils {

    public static String getToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
