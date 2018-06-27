package com.als.authorize.sso.service.impl;


import com.als.authorize.sso.common.utils.IpUtils;
import com.als.authorize.sso.controller.model.UserModel;
import com.als.authorize.sso.dao.UserDao;
import com.als.authorize.sso.dao.entity.UserEntity;
import com.als.authorize.sso.dao.spec.BasicSpec;
import com.als.authorize.sso.service.SSOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class SSOServiceImpl extends BasicServiceImpl implements SSOService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    @Transactional
    public UserModel login(String username, String password) {
        UserEntity userEntity = userDao.findOne(new BasicSpec<UserEntity>() {
            @Override
            public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                predicateList.add(cb.equal(root.get("username"), username));
                predicateList.add(cb.equal(root.get("password"), DigestUtils.md5DigestAsHex(password.getBytes())));
                predicateList.add(cb.equal(root.get("enabled"), true));
                return super.toPredicate(root, query, cb);
            }
        });
        UserModel userModel = new UserModel();
        if (userEntity != null) {
            String token = DigestUtils.md5DigestAsHex(String.valueOf(new Random().nextInt(999999)).getBytes());
            userEntity.setLastIP(IpUtils.getRemoteAddr());
            userEntity.setLastLogin(new Date());
            userEntity.setToken(token);
            userDao.save(userEntity);
            redisTemplate.opsForSet().add(userEntity.getId().toString(), token);
            redisTemplate.expire(userEntity.getId().toString(), 3, TimeUnit.DAYS);
            userModel.setUserId(userEntity.getId());
            userModel.setToken(token);
        }
        return userModel;
    }

    @Override
    public boolean logout(long userId) {
        if (redisTemplate.hasKey(String.valueOf(userId))) {
            redisTemplate.delete(String.valueOf(userId));
            logoutDB(userId);
            return true;
        }
        return false;
    }

    @Override
    public boolean logout(long userId, String token) {
        long result = redisTemplate.opsForSet().remove(String.valueOf(userId), token);
        if (result > 0) {
            logoutDB(userId);
            return true;
        }
        return false;
    }

    @Override
    public boolean checkLogin(long userId, String token) {
        return redisTemplate.opsForSet().isMember(String.valueOf(userId), token);
    }

    @Transactional
    void logoutDB(long userId) {
        UserEntity userEntity = userDao.findOne(userId);
        userEntity.setLastIP(IpUtils.getRemoteAddr());
        userEntity.setLastLogout(new Date());
        userDao.save(userEntity);
    }
}
