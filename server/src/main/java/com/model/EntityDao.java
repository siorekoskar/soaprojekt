package com.model;

import entity.Forest;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ManagedBean
public class EntityDao {

    @Inject
    private EntityManager entityManager;

    public List<Forest> getForests() {
        return entityManager.createQuery("SELECT f FROM Forest f", Forest.class)
                .getResultList();
    }
}
