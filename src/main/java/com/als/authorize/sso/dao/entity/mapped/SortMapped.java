package com.als.authorize.sso.dao.entity.mapped;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class SortMapped extends BasicMapped {
    private Integer sort;

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
}
