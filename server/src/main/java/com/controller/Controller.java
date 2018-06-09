package com.controller;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/contr")
public class Controller {

    @Inject
    private EntityManager em;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response insert() {
//        Forest entity = new Forest(17);
//        em.persist(new Elf("erni", 1));
//        return Response.ok(entity).build();
        return null;
    }
}
