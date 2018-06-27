package com.als.authorize.sso.service.impl;

import com.als.authorize.sso.controller.model.UserModel;
import com.als.authorize.sso.dao.UserDao;
import com.als.authorize.sso.dao.entity.UserEntity;
import com.als.authorize.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class UserServiceImpl extends BasicServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public UserModel add(String username, String password) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(username);
        userEntity.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
        userEntity = userDao.save(userEntity);
        UserModel userModel = new UserModel();
        userModel.setUserId(userEntity.getId());
        return userModel;
    }

    @Override
    public boolean enable(long userId, boolean enabled) {
        UserEntity userEntity = userDao.findOne(userId);
        if (userEntity != null) {
            userEntity.setEnabled(enabled);
            userDao.save(userEntity);
            return true;
        }
        return false;
    }

    @Override
    public void delete(long userId) {
        userDao.delete(userId);
    }

    @Override
    public boolean resetPassword(long userId, String password) {
        UserEntity userEntity = userDao.findOne(userId);
        if (userEntity != null) {
            userEntity.setPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
            userDao.save(userEntity);
            return true;
        }
        return false;
    }
}
