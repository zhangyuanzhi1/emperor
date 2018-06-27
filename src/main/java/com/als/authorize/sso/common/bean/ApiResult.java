package com.als.authorize.sso.common.bean;

public class ApiResult<T> {
    private int code = SUCCESS;
    private String msg;
    private T data;

    public static final int SUCCESS = 0;

    public ApiResult() {
    }

    public ApiResult(int code) {
        this.code = code;
    }

    public ApiResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ApiResult(T data) {
        this.data = data;
    }

    public ApiResult(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
