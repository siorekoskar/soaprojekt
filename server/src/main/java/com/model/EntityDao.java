package com.model;

import entity.Catalog;
import entity.Elf;
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

    public Catalog getCatalog(Forest forest) {
        List<Elf> elves = entityManager.createQuery(
                "SELECT e FROM Forest f " +
                        "JOIN Elf e ON f.forestId = e.forestId " +
                        "WHERE f.forestId = :forestId", Elf.class)
                .setParameter("forestId", forest.getForestId())
                .getResultList();

        return new Catalog(forest, elves);
    }
}
