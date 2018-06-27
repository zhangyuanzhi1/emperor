package com.als.authorize.sso.common.utils.cookie;

import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieSet {
    private HttpServletRequest request;
    private HttpServletResponse response;

    public CookieSet(HttpServletRequest request) {
        this.request = request;
    }

    public CookieSet(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public Cookie getCookie(String name) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return null;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals(name)) return cookie;
        }
        return null;
    }

    public String get(String name) {
        Cookie cookie = getCookie(name);
        return cookie == null ? null : cookie.getValue();
    }

    public boolean contains(String name) {
        return getCookie(name) != null;
    }

    public boolean empty(String name) {
        return !StringUtils.isEmpty(get(name));
    }

    public void set(String name, String value) {
        set(name, value, null);
    }

    public void set(String name, String value, Integer maxAge) {
        if (response == null) return;
        Cookie cookie = getCookie(name);
        if (cookie == null) cookie = new Cookie(name, value);
        if (maxAge != null) cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }

    public void delete(String name) {
        if (response == null) return;
        Cookie cookie = getCookie(name);
        if (cookie != null) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
    }
}
