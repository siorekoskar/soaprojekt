package com.model;

import entity.Catalog;
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
        return entityDao.getForests();
    }
}
