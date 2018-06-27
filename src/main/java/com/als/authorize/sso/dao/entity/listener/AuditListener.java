package com.als.authorize.sso.dao.entity.listener;

import com.als.authorize.sso.common.bean.UserClient;
import com.als.authorize.sso.common.utils.BeanUtils;
import com.als.authorize.sso.common.utils.IpUtils;
import com.als.authorize.sso.dao.entity.mapped.AuditMapped;

import javax.persistence.PrePersist;

public class AuditListener {
    @PrePersist
    public void onPrePersist(AuditMapped object) {
        UserClient userClient = BeanUtils.getBean(UserClient.class);
        try {
            if (userClient != null) {
                object.setDeviceId(userClient.getDeviceId());
                object.setUserAgent(userClient.getUserAgent());
                object.setOs(userClient.getOs());
                object.setPlatform(userClient.getPlatform());
                object.setUserIp(userClient.getIp());
            }
        } catch (Exception ignored) { }
        object.setServerIp(IpUtils.getLocalAddr());
    }
}
