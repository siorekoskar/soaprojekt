package rest;

import entity.*;
import proj.RemoteCatalogue;
import service.RestService;
import translate.Translator;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@Path("/catalog")
public class RestController {

    @EJB(mappedName = "java:global/server/Catalogue!proj.RemoteCatalogue")
    private RemoteCatalogue remoteCatalogue;

    @Inject
    private Translator translator;

    @Inject
    private RestService restService;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCategories(@QueryParam("categoryId") Integer categoryId) {
        List<Forest> categories = restService.filterCategories(categoryId);

        List<CategoryDto> categoryDtos = restService.generateDtos(categories);

        GenericEntity<List<CategoryDto>> genericEntity = restService.generateGenericCategories(categoryDtos);

        return Response.ok(genericEntity).build();
    }

    @GET
    @Path("/{categoryId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSpecificCategories(@PathParam("categoryId") Integer categoryId, @Context HttpServletRequest request) {
        List<Forest> categories = restService.filterCategories(categoryId);

        if (categories.size() == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<CategoryDto> categoryDtos = restService.generateDtos(categories);

        translator.translate(request.getLocale(), categoryDtos);

        GenericEntity<List<CategoryDto>> genericEntity = restService.generateGenericCategories(categoryDtos);

        return Response.ok(genericEntity).build();
    }

    @POST
    @Path("/{categoryId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postElementToCategory(@PathParam("categoryId") Integer categoryId, ElementDto elementDto) throws URISyntaxException {
        Elf element = ElementDto.builder(elementDto);

        Optional<Forest> category = restService.findCategory(categoryId);
        Optional<ElementType> categoryType = restService.findElementType(elementDto);

        if (category.isPresent() && categoryType.isPresent()) {
            element.setForestsByForestId(category.get());
            element.setElementTypeByElementTypeId(categoryType.get());
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        int index = remoteCatalogue.addElf(element);
        return Response.created(new URI("/category/" + categoryId + "/element/" + index)).build();
    }

}
