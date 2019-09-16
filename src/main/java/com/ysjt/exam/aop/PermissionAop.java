package com.ysjt.exam.aop;

import com.ysjt.exam.common.response.ResponseCode;
import com.ysjt.exam.constant.RedisKeys;
import com.ysjt.exam.exception.base.BusinessException;
import com.ysjt.exam.utils.RedisUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.Map;

/**
 * @author yuxiaofei
 * <pre>
 *
 * </pre>
 * @date 2019/9/16 11:18
 */
@Component
@Aspect
public class PermissionAop {

    @Autowired
    RedisUtils redisUtils;

    @Before("execution(* com.ysjt.exam.web..*(..))")
    public void before(JoinPoint joinPoint) {
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        Enumeration<String> enumeration = request.getHeaderNames();
        String token = "";
        while (enumeration.hasMoreElements()) {
            String name = enumeration.nextElement();
            if ("token".equals(name)) {
                token = request.getHeader(name);
            }
        }
        if (token.isEmpty()) {
            permitRequestFilter(joinPoint);
        }
    }

    private void permitRequestFilter(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        Class<?>[] argTypes = new Class[joinPoint.getArgs().length];
        for (int i = 0; i < args.length; i++) {
            argTypes[i] = args[i].getClass();
        }
        Method method;
        try {
            method = joinPoint.getTarget().getClass()
                    .getMethod(joinPoint.getSignature().getName(), argTypes);
            Public isPublic = method.getAnnotation(Public.class);
            if (isPublic == null || !isPublic.isEnable()) {
                throw new BusinessException(ResponseCode.AUTH_FAIL);
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
