package com.model;

import entity.*;
import org.apache.commons.codec.digest.DigestUtils;
import proj.RemoteCatalogue;
import proj.UserDetails;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Remote(RemoteCatalogue.class)
@Stateless
public class Catalogue implements RemoteCatalogue {

    @Inject
    private EntityDao entityDao;

    @Override
    public List<Forest> getForests() {
        return entityDao.getCategories();
    }

    @Override
    public List<CategoryType> getCategoryTypes() {
        return entityDao.getAllCategories();
    }

    @Override
    public List<ElementType> getElementTypes() {
        return entityDao.getElementTypes();
    }

    @Override
    public List<Elf> getElves() {
        return entityDao.getElements();
    }

    @Override
    public void addForest(Forest category) {
        entityDao.addCategory(category);
    }

    @Override
    public void addElf(Elf elf) {
        entityDao.addElement(elf);
    }

    @Override
    public void removeElf(Elf elf) {
        entityDao.removeElement(elf);
    }

    @Override
    public void removeForest(Forest forest) {
        entityDao.removeCategory(forest);
    }

    @Override
    public List<User> getUsers() {
        return entityDao.getUsers();
    }

    @Override
    public boolean changePassword(UserDetails userDetails) {
        User user = entityDao.findUser(userDetails.getUserName());
        String newUserPasswordHash = DigestUtils.sha256Hex(userDetails.getNewPassword());
        String oldUserPasswordHash = DigestUtils.sha256Hex(userDetails.getOldPassword());

        if (user != null && oldUserPasswordHash.equals(user.getPassword())) {
            user.setPassword(newUserPasswordHash);
            entityDao.changePasswordForUser(user);
            return true;
        } else {
            return false;
        }
    }
}
