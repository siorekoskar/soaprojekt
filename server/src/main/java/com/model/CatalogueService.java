package com.model;

import entity.Catalog;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@ManagedBean
public class CatalogueService {

    @Inject
    private EntityDao entityDao;

    public List<Catalog> getCatalogs() {
        return entityDao.getForests().stream()
                .map(forest -> new Catalog(forest, forest.getElvesByForestId()))
                .collect(Collectors.toList());
    }
}
