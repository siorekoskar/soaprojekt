package rest;

import proj.RemoteCatalogue;

import javax.ejb.EJB;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/contr")
public class RestController {

    @EJB(mappedName = "java:global/server/Catalogue!proj.RemoteCatalogue")
    private RemoteCatalogue remoteCatalogue;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Response insert() {
        return Response.ok(remoteCatalogue.getForests()).build();
    }
}
