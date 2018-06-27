package com.als.authorize.sso.common.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class IpUtils {
    public static String getRemoteAddr() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return getRemoteAddr(request);
    }

    public static String getRemoteAddr(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (isValid(ip)) return ip;
        ip = request.getHeader("Proxy-Client-IP");
        if (isValid(ip)) return ip;
        ip = request.getHeader("WL-Proxy-Client-IP");
        if (isValid(ip)) return ip;
        ip = request.getHeader("HTTP_CLIENT_IP");
        if (isValid(ip)) return ip;
        ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        if (isValid(ip)) return ip;
        ip = request.getRemoteAddr();
        if (isValid(ip)) return ip;
        return getLocalAddr();
    }

    public static String getLocalAddr() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static boolean isValid(String ip) {
        return StringUtils.isNotEmpty(ip) && !ip.equalsIgnoreCase("unknown") && !ip.contains("0:0:0");
    }
}
