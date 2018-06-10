package com.model;

import entity.*;

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
    public List<Forest> getCategories() {
        return entityManager.createNamedQuery("getAllForests", Forest.class)
                .getResultList();
    }

    @Transactional
    public List<Elf> getElements() {
        return entityManager.createNamedQuery("getAllElves", Elf.class)
                .getResultList();
    }

    @Transactional
    public void addCategory(Forest category) {
        entityManager.persist(category);
    }

    @Transactional
    public void addElement(Elf element) {
        if(element.getElfId() != null) {
            entityManager.createNativeQuery("UPDATE elves e SET " +
                    "e.element_type_id = ?, " +
                    "e.arrow_type = ?, " +
                    "e.name = ?, " +
                    "e.power = ?, " +
                    "e.arrows_count = ?, " +
                    "e.forest_id = ? WHERE e.elf_id = ?")
                    .setParameter(1, element.getElementTypeByElementTypeId().getElementTypeId())
                    .setParameter(2, element.getArrowType())
                    .setParameter(3, element.getName())
                    .setParameter(4, element.getPower())
                    .setParameter(5, element.getArrowsCount())
                    .setParameter(6, element.getForestsByForestId().getForestId())
                    .setParameter(7, element.getElfId())
                    .executeUpdate();
        } else {
            entityManager.persist(element);
        }
    }

    @Transactional
    public void removeElement(Elf element) {
        entityManager.createNamedQuery("removeElf")
                .setParameter("elfId", element.getElfId())
                .executeUpdate();
    }

    @Transactional
    public void removeCategory(Forest category) {
        entityManager.remove(entityManager.contains(category) ? category : entityManager.merge(category));
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

    @Transactional
    public List<ElementType> getElementTypes() {
        return entityManager.createQuery("SELECT et FROM ElementType et", ElementType.class)
                .getResultList();
    }
}
