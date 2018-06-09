package com.model;

import entity.Catalog;
import entity.Forest;
import proj.RemoteCatalogue;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
public class Catalogue implements RemoteCatalogue {

    @Inject
    EntityDao entityDao;

    public List<Catalog> getCatalogs() {
        return entityDao.getForests().stream()
                .map(forest -> entityDao.getCatalog(forest))
                .collect(Collectors.toList());
    }

    public List<Forest> getForests() {
        return entityDao.getForests();
    }
}
