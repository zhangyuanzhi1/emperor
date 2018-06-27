package com.als.authorize.sso.dao.spec;


import com.als.authorize.sso.dao.entity.mapped.BasicMapped;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

public class BasicSpec<T extends BasicMapped> implements Specification<T> {
    private boolean deleted;
    protected List<Predicate> predicateList = new ArrayList<>();
    protected List<Order> orderList = new ArrayList<>();

    public BasicSpec() {
    }

    public BasicSpec(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        predicateList.add(cb.equal(root.get("deleted"), deleted));
        query.where(predicateList.toArray(new Predicate[0]));
        query.orderBy(orderList.toArray(new Order[0]));
        predicateList.clear();
        orderList.clear();
        return query.getRestriction();
    }
}
