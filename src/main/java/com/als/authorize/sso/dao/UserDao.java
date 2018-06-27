package com.als.authorize.sso.dao;

import com.als.authorize.sso.dao.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<UserEntity, Long>, JpaSpecificationExecutor<UserEntity> {
}
