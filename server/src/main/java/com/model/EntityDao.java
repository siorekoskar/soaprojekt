package com.model;

import entity.Elf;
import entity.Forest;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;

@ManagedBean
public class EntityDao {

    @Inject
    private EntityManager entityManager;

    @Transactional
    public List<Forest> getForests() {
        return entityManager.createNamedQuery("getAllForests", Forest.class)
                .getResultList();
    }

    @Transactional
    public List<Elf> getElves() {
        return entityManager.createNamedQuery("getAllElves", Elf.class)
                .getResultList();
    }

    @Transactional
    public void addForest(Integer height){
        Forest f = new Forest();
        f.setHeight(height);
        entityManager.persist(f);
    }

    @Transactional
    public void addElf(Elf elf){
        entityManager.persist(elf);
    }
}
