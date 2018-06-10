package com.model;

import entity.Catalog;
import entity.Elf;
import entity.Forest;
import entity.User;
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
    @Inject
    private CatalogueService catalogueService;

    @Override
    public List<Catalog> getCatalogs() {
        return catalogueService.getCatalogs();
    }

    @Override
    public List<Forest> getForests() {
        return catalogueService.getForests();
    }

    @Override
    public List<Elf> getElves() {
        return entityDao.getElves();
    }

    @Override
    public void addForest(Integer height) {
        entityDao.addForest(height);
    }

    @Override
    public void addElf(Elf elf) {
        entityDao.addElf(elf);
    }

    @Override
    public void removeElf(Elf elf) {
        entityDao.removeElf(elf);
    }

    @Override
    public void removeForest(Forest forest) {
        entityDao.removeForest(forest);
    }

    @Override
    public List<User> getUsers() {
        return entityDao.getUsers();
    }

    @Override
    public boolean changePassword(UserDetails userDetails) {
        User user = entityDao.findUser(userDetails.getUserName());
        if (user != null && userDetails.getOldPassword().equals(user.getPassword())) {
            user.setPassword(userDetails.getNewPassword());
            entityDao.changePasswordForUser(user);
            return true;
        } else {
            return false;
        }
    }
}
