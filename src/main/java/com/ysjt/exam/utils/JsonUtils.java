package com.ysjt.exam.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.ysjt.exam.utils.gson.MapDeserializerDoubleAsIntFix;

import java.lang.reflect.Type;
import java.util.*;

public class JsonUtils {


    private JsonUtils() {
        throw new IllegalStateException("Utility class");
    }


    private static Gson gson = new GsonBuilder().registerTypeAdapter(new TypeToken<Map<String, Object>>() {
            }.getType(),
            new MapDeserializerDoubleAsIntFix())
            .serializeNulls() //当字段值为空或null时，依然对该字段进行转换
            .setDateFormat("yyyy-MM-dd HH:mm:ss:SSS") //时间转化为特定格式
            .setPrettyPrinting() //对结果进行格式化，增加换行
            .disableHtmlEscaping()//防止特殊字符出现乱码
            .create();

    public static String toJson(Object o, Type type) {
        return gson.toJson(o, type);
    }

    public static <T> T fromJson(String str, Type type) {
        return gson.fromJson(str, type);
    }
}
