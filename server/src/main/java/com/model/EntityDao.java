package com.model;

import entity.CategoryType;
import entity.Elf;
import entity.Forest;
import entity.User;

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
    public void addForest(Integer height) {
        Forest f = new Forest();
        f.setHeight(height);
        entityManager.persist(f);
    }

    @Transactional
    public void addForest(Forest category) {
        entityManager.persist(category);
    }

    @Transactional
    public void addElf(Elf elf) {
        entityManager.persist(elf);
    }

    @Transactional
    public void removeElf(Elf elf) {
        entityManager.createNamedQuery("removeElf")
                .setParameter("elfId", elf.getElfId())
                .executeUpdate();
    }

    @Transactional
    public void removeForest(Forest forest) {
        entityManager.remove(entityManager.contains(forest) ? forest : entityManager.merge(forest));
    }

    @Transactional
    public List<User> getUsers() {
        return entityManager.createQuery("SELECT u FROM User u", User.class)
                .getResultList();
    }

    @Transactional
    public User findUser(String userName) {
        return entityManager.createQuery("SELECT u FROM User u WHERE u.login = :userName", User.class)
                .setParameter("userName", userName)
                .getSingleResult();
    }

    @Transactional
    public void changePasswordForUser(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public List<CategoryType> getAllCategories() {
        return entityManager.createQuery("SELECT ct FROM CategoryType ct", CategoryType.class)
                .getResultList();
    }
}
