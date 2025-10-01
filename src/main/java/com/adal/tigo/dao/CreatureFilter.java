package com.adal.tigo.dao;

import com.adal.tigo.model.Creature;
import org.springframework.data.jpa.domain.Specification;

public class CreatureFilter {


    public static Specification<Creature> nameContains(String name) {
        return (root, query, cb) ->
                name == null ? null : cb.like(cb.lower(root.get("name")), "%" + name.toLowerCase() + "%");
    }

    public static Specification<Creature> isActive(Boolean active) {
        return (root, query, cb) ->
                active == null ? null : cb.equal(root.get("active"), active);
    }

    // Example future filter
    public static Specification<Creature> hasType(String type) {
        return (root, query, cb) ->
                type == null ? null : cb.equal(root.get("type"), type);
    }

}
