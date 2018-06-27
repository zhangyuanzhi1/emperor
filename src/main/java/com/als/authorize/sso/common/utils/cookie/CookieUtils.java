package com.als.authorize.sso.common.utils.cookie;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
    public static String get(HttpServletRequest request, String name) {
        return new CookieSet(request).get(name);
    }

    public static void set(HttpServletRequest request, HttpServletResponse response, String name, String value) {
        set(request, response, name, value, null);
    }

    public static void set(HttpServletRequest request, HttpServletResponse response, String name, String value, Integer maxAge) {
        new CookieSet(request, response).set(name, value, maxAge);
    }

    public static void delete(HttpServletRequest request, HttpServletResponse response, String name) {
        new CookieSet(request, response).delete(name);
    }
}
