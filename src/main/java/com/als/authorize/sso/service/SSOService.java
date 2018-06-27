package com.als.authorize.sso.service;

import com.als.authorize.sso.controller.model.UserModel;

public interface SSOService {
    UserModel login(String username, String password);
    boolean logout(long userId);
    boolean logout(long userId, String token);
    boolean checkLogin(long userId, String token);
}
