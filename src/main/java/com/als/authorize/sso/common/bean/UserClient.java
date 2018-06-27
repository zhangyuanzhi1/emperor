package com.als.authorize.sso.common.bean;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.http.Cookie;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class UserClient {
    private Long userId;
    private String deviceId;
    private String os;
    private String platform;
    private String userAgent;
    private String ip;
    private Cookie[] cookies;

    public final static String OS_IOS = "ios";
    public final static String OS_ANDROID = "android";
    public final static String OS_OTHERS = "others";

    public final static String PLATFORM_WECHAT = "wechat";
    public final static String PLATFORM_ALIPAY = "alipay";
    public final static String PLATFORM_OTHERS = "others";

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Cookie[] getCookies() {
        return cookies;
    }

    public void setCookies(Cookie[] cookies) {
        this.cookies = cookies;
    }
}
