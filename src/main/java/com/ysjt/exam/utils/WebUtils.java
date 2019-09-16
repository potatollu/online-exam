package com.ysjt.exam.utils;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author yuxiaofei
 * <pre>
 *
 * </pre>
 * @date 2019/9/16 15:26
 */
public class WebUtils {

    private WebUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final String UNKNOWN = "unknown";

    public static String encodeString(String source, String encoding) {
        try {
            return URLEncoder.encode(source, encoding);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static String decodeString(String source, String encoding) {
        try {
            return URLDecoder.decode(source, encoding);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    public static Cookie getCookie(HttpServletRequest request, String name) {
        return org.springframework.web.util.WebUtils.getCookie(request, name);
    }


    public static String getClientIP(HttpServletRequest request)
    {
        String ipAddress = null;
        ipAddress = request.getHeader("x-forwarded-for");
        if (ipAddress == null || ipAddress.length() == 0
                || UNKNOWN.equalsIgnoreCase(ipAddress))
            ipAddress = request.getHeader("Proxy-Client-IP");
        if (ipAddress == null || ipAddress.length() == 0
                || UNKNOWN.equalsIgnoreCase(ipAddress))
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        if (ipAddress == null || ipAddress.length() == 0
                || UNKNOWN.equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
                InetAddress inetaddress = null;
                try {
                    inetaddress = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    return "";
                }
                ipAddress = inetaddress.getHostAddress();
            }
        }

        // 对于通过多个代理的情况，第一个IP为客户端真实IP地址，多个IP按照","分割
        if (ipAddress.split(",").length > 1)
            ipAddress = ipAddress.split(",")[0];
        return ipAddress;
    }

    public static String appendUrlParam(String url, String name, String value) {
        if (value == null) {
            return url;
        } else if (url != null) {
            StringBuilder sb = new StringBuilder();
            sb.append(url);
            if (name != null) {
                if (url.contains("?")) {
                    sb.append("&");
                } else {
                    sb.append("?");
                }

                sb.append(name);
                sb.append("=");
                sb.append(encodeString(value, "utf-8"));
            }

            return sb.toString();
        } else {
            return null;
        }
    }

    public static String appendUrlParams(String url, Map<String, String> params) {
        if (params == null) {
            return url;
        } else {
            StringBuilder s = new StringBuilder(url);
            Iterator var3 = params.entrySet().iterator();

            while(var3.hasNext()) {
                Map.Entry<String, String> entry = (Map.Entry)var3.next();
                if (s.indexOf("?") != -1) {
                    s.append("&");
                } else {
                    s.append("?");
                }

                s.append((String)entry.getKey());
                s.append("=");
                s.append(encodeString((String)entry.getValue(), "utf-8"));
            }

            return s.toString();
        }
    }

    public static String getBasePath(HttpServletRequest request) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        return basePath;
    }

    public static HttpServletResponse getHttpServletResponse(){
        return getServletRequestAttributes().getResponse();
    }

    public static ServletRequestAttributes getServletRequestAttributes(){
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
    }

    public static HttpServletRequest getHttpServletRequest() {
        return getServletRequestAttributes().getRequest();
    }


    public static Map<String, String> getRequestParameters(HttpServletRequest request) {
        return buildRequestParams(request.getParameterNames(), request);
    }

    public static Map<String, String> getRequestHeaders(HttpServletRequest request) {
        return buildHeadersParams(request.getHeaderNames(), request);
    }

    public static String getRequestHeader(String name){
        return getRequestHeaders(getHttpServletRequest()).get(name);
    }



    private static Map<String, String> buildHeadersParams(Enumeration enumeration, HttpServletRequest request) {
        Map<String, String> param = new HashMap<>();
        while (enumeration.hasMoreElements()) {
            String keyName = String.valueOf(enumeration.nextElement());
            param.put(keyName, request.getHeader(keyName));
        }
        return param;
    }


    private static Map<String, String> buildRequestParams(Enumeration enumeration, HttpServletRequest request) {
        Map<String, String> param = new HashMap<>();
        while (enumeration.hasMoreElements()) {
            String keyName = String.valueOf(enumeration.nextElement());
            param.put(keyName, request.getParameter(keyName));
        }
        return param;
    }

    public static void writeJson(HttpServletResponse response,byte[] bytes) throws IOException {
        response.setHeader("Content-Type","application/json;charset=UTF-8");
        OutputStream outputStream = response.getOutputStream();
        outputStream.write(bytes,0,bytes.length);
        outputStream.flush();
        outputStream.close();
    }

    public static ResponseEntity<byte[]> getDownloadResponseEntity(HttpServletRequest request, File file) {
        return getDownloadResponseEntity(request, file.getName(), MediaType.APPLICATION_OCTET_STREAM, file, (File)null);
    }

    public static ResponseEntity<byte[]> getDownloadResponseEntity(HttpServletRequest request, File file, File defaultFile) {
        return getDownloadResponseEntity(request, file.getName(), MediaType.APPLICATION_OCTET_STREAM, file, defaultFile);
    }

    public static ResponseEntity<byte[]> getDownloadResponseEntity(HttpServletRequest request, String fileName, File file) {
        return getDownloadResponseEntity(request, fileName, MediaType.APPLICATION_OCTET_STREAM, file, (File)null);
    }

    public static ResponseEntity<byte[]> getDownloadResponseEntity(HttpServletRequest request, String fileName, File file, File defaultFile) {
        return getDownloadResponseEntity(request, fileName, MediaType.APPLICATION_OCTET_STREAM, file, defaultFile);
    }

    public static ResponseEntity<byte[]> getDownloadResponseEntity(HttpServletRequest request, String fileName, MediaType contentType, File file, File defaultFile) {
        try {
            byte[] bytes = FileUtils.readFileToByteArray(file);
            return getDownloadResponseEntity(request, fileName, contentType, bytes);
        } catch (IOException var8) {
            if (defaultFile == null) {
                throw new RuntimeException("资源不存在", var8);
            } else {
                try {
                    byte[] bytes = FileUtils.readFileToByteArray(defaultFile);
                    return getDownloadResponseEntity(request, fileName, contentType, bytes);
                } catch (IOException var7) {
                    var7.addSuppressed(var8);
                    throw new RuntimeException("资源不存在", var7);
                }
            }
        }
    }

    public static ResponseEntity<byte[]> getDownloadResponseEntity(HttpServletRequest request, String fileName, byte[] bytes) {
        return getDownloadResponseEntity(request,fileName,MediaType.APPLICATION_OCTET_STREAM,bytes);
    }

    public static ResponseEntity<InputStreamResource> getDownloadResponseEntity(HttpServletRequest request, URL url) {
        String fileName = StringUtils.substring(url.getPath(), url.getPath().lastIndexOf("/") + 1);
        return getDownloadResponseEntity(request, fileName, url);
    }

    public static ResponseEntity<InputStreamResource> getDownloadResponseEntity(HttpServletRequest request, String fileName, URL url) {
        try {
            return getDownloadResponseEntity(request, fileName, MediaType.APPLICATION_OCTET_STREAM, (new InputStreamResource(url.openStream())));
        } catch (IOException var4) {
            throw new RuntimeException("资源不存在", var4);
        }
    }

    public static <T> ResponseEntity<T> getDownloadResponseEntity(HttpServletRequest request, String fileName, MediaType contentType, T body) {
        String downloadFileName = getAttachFileName(request, fileName);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentDispositionFormData("attachment", downloadFileName);
        httpHeaders.setContentType(contentType);
        return new ResponseEntity(body, httpHeaders, HttpStatus.OK);
    }


    public static String getAttachFileName(HttpServletRequest request, String fileName) {
        String encodeFileName = fileName;

        try {
            String agent = request.getHeader("User-Agent");
            if (null != agent) {
                if (!agent.contains("MSIE") && !agent.contains("Trident")) {
                    if (agent.contains("Mozilla")) {
                        encodeFileName = StringUtils.newStringIso8859_1(StringUtils.getByteUtf8(fileName));
                    }
                } else {
                    encodeFileName = URLEncoder.encode(fileName, "UTF-8");
                }
            }
        } catch (Exception var4) {
            ;
        }
        return encodeFileName;
    }



}
