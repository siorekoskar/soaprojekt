package com.model;

import entity.Catalog;
import entity.Elf;
import entity.Forest;
import proj.RemoteCatalogue;

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
}
