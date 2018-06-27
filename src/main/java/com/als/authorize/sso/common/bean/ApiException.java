package com.als.authorize.sso.common.bean;

public class ApiException extends RuntimeException {
    private int code = 500;

    public ApiException() {
        super();
    }

    public ApiException(int code) {
        super();
        this.code = code;
    }

    public ApiException(int code, String message) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
