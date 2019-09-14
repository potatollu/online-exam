package com.ysjt.exam.utils;

import java.util.UUID;

public class TokenUtils {

    public static String getToken() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
