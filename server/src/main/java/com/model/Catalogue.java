package com.model;

import entity.Catalog;
import entity.Forest;
import proj.RemoteCatalogue;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Remote(RemoteCatalogue.class)
@Stateless
public class Catalogue implements RemoteCatalogue {

    @Inject
    private EntityDao entityDao;

    @Override
    public List<Catalog> getCatalogs() {
        return entityDao.getForests().stream()
                .map(forest -> entityDao.getCatalog(forest))
                .collect(Collectors.toList());
    }

    @Override
    public List<Forest> getForests() {
        return entityDao.getForests();
    }
}
