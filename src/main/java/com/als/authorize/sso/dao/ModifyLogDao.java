package com.als.authorize.sso.dao;

import com.als.authorize.sso.dao.entity.ModifyLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ModifyLogDao extends JpaRepository<ModifyLogEntity, Long>, JpaSpecificationExecutor<ModifyLogEntity> {
}
