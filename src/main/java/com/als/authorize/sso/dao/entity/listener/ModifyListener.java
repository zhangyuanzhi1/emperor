package com.als.authorize.sso.dao.entity.listener;

import com.alibaba.fastjson.JSON;
import com.als.authorize.sso.common.utils.BeanUtils;
import com.als.authorize.sso.dao.ModifyLogDao;
import com.als.authorize.sso.dao.entity.ModifyLogEntity;
import com.als.authorize.sso.dao.entity.mapped.BasicMapped;

import javax.persistence.PostPersist;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PreUpdate;
import java.util.Date;

public class ModifyListener {
    @PreUpdate
    public void onPreUpdate(BasicMapped object) {
        object.setUpdateTime(new Date());
    }

    @PostUpdate
    public void onPostUpdate(BasicMapped object) {
        onPostModify(object, ModifyLogEntity.ACTION_UPDATE);
    }

    @PostPersist
    public void onPostPersist(BasicMapped object) {
        onPostModify(object, ModifyLogEntity.ACTION_INSERT);
    }

    @PostRemove
    public void onPostRemove(BasicMapped object) {
        onPostModify(object, ModifyLogEntity.ACTION_DELETE);
    }

    private void onPostModify(BasicMapped object, String action) {
        ModifyLogDao modifyLogDao = BeanUtils.getBean(ModifyLogDao.class);
        ModifyLogEntity modifyLogEntity = new ModifyLogEntity();
        modifyLogEntity.setAction(action);
        modifyLogEntity.setEntityName(object.getClass().getSimpleName());
        modifyLogEntity.setEntityValue(JSON.toJSONString(object));
//        modifyLogEntity.setModifiedBy(null);
        modifyLogEntity.setKeyValue(object.getId().toString());
        modifyLogDao.save(modifyLogEntity);
    }
}
