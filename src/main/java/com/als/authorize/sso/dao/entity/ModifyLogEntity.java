package com.als.authorize.sso.dao.entity;


import com.als.authorize.sso.dao.entity.mapped.AuditMapped;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "msg_modify_log")
public class ModifyLogEntity extends AuditMapped {
    private String action;
    private String entityName;
    private String keyValue;
    private String entityValue;
    private String modifiedBy;

    public static final String ACTION_INSERT = "insert";
    public static final String ACTION_UPDATE = "update";
    public static final String ACTION_DELETE = "delete";

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getEntityValue() {
        return entityValue;
    }

    public void setEntityValue(String entityValue) {
        this.entityValue = entityValue;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
}
