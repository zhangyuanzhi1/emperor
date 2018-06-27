package com.als.authorize.sso.service;

import com.als.authorize.sso.controller.model.UserModel;

public interface UserService {
    UserModel add(String username, String password);
    boolean enable(long userId, boolean enabled);
    void delete(long userId);
    boolean resetPassword(long userId, String password);
}
