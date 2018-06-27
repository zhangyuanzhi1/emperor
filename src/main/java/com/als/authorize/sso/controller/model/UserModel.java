package com.als.authorize.sso.controller.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户")
public class UserModel extends BasicModel {
    @ApiModelProperty("用户ID")
    private long userId;
    @ApiModelProperty("令牌")
    private String token;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
