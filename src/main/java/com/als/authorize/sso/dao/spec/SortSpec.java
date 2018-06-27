package com.als.authorize.sso.dao.spec;

import com.als.authorize.sso.dao.entity.mapped.SortMapped;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SortSpec<T extends SortMapped> extends BasicSpec<T> {
    public SortSpec() {
    }

    public SortSpec(boolean deleted) {
        super(deleted);
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        orderList.add(cb.desc(root.get("sort")));
        return super.toPredicate(root, query, cb);
    }
}
