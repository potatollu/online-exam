package com.ysjt.exam.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author yuxiaofei
 * <pre>
 *  用于处理接口白名单
 * </pre>
 * @date 2019/9/16 11:18
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Public {
    boolean isEnable() default true;
}
