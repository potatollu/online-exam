package com.ysjt.exam.filter;

import com.ysjt.exam.constant.RedisKeys;
import com.ysjt.exam.utils.RedisUtils;
import com.ysjt.exam.utils.SpringContextUtils;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.IOException;
import java.util.*;

/**
 * @author yuxiaofei
 * <pre>
 *
 * </pre>
 * @date 2019/9/16 11:28
 */

@Component
@WebFilter
public class UserInfoFilter implements Filter {

    private static final String USER_ID = "userId";

    private static final String USERNAME = "username";

    private static final String REAL_NAME = "realName";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HeaderMapRequestWrapper requestWrapper = new HeaderMapRequestWrapper(req);
        RedisUtils redisUtils = SpringContextUtils.getBean(RedisUtils.class);
        Map<Object,Object> userMap = redisUtils.hmget(RedisKeys.EXAM_TOKEN_INFO + ((HttpServletRequest) request).getHeader("token"));
        if (userMap != null && userMap.size() > 0) {
            requestWrapper.addHeader(USER_ID, userMap.get(USER_ID).toString());
            requestWrapper.addHeader(USERNAME, userMap.get(USERNAME).toString());
            requestWrapper.addHeader(REAL_NAME, userMap.get(REAL_NAME).toString());
        }
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {
    }

    public class HeaderMapRequestWrapper extends HttpServletRequestWrapper {

        public HeaderMapRequestWrapper(HttpServletRequest request) {
            super(request);
        }

        private Map<String, String> headerMap = new HashMap<>();

        public void addHeader(String name, String value) {
            headerMap.put(name, value);
        }

        @Override
        public String getHeader(String name) {
            String headerValue = super.getHeader(name);
            if (headerMap.containsKey(name)) {
                headerValue = headerMap.get(name);
            }
            return headerValue;
        }

        @Override
        public Enumeration<String> getHeaderNames() {
            List<String> names = Collections.list(super.getHeaderNames());
            names.addAll(headerMap.keySet());
            return Collections.enumeration(names);
        }

        @Override
        public Enumeration<String> getHeaders(String name) {
            List<String> values = Collections.list(super.getHeaders(name));
            if (headerMap.containsKey(name)) {
                values.add(headerMap.get(name));
            }
            return Collections.enumeration(values);
        }
    }
}
