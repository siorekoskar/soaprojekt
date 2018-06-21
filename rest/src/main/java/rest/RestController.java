package rest;

import entity.*;
import proj.RemoteCatalogue;
import translate.Translator;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/catalog")
public class RestController {

    @EJB(mappedName = "java:global/server/Catalogue!proj.RemoteCatalogue")
    private RemoteCatalogue remoteCatalogue;

    @Inject
    private Translator translator;

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getCategories(@QueryParam("categoryId") Integer categoryId) {
        List<Forest> categories = remoteCatalogue.getForests();

        if (categoryId != null) {
            categories = categories.stream()
                    .filter(category -> category.getForestId().equals(categoryId))
                    .collect(Collectors.toList());
        }

        List<CategoryDto> categoryDtos = new ArrayList<>();
        categories.forEach(category -> categoryDtos.add(CategoryDto.builder(category)));


        GenericEntity<List<CategoryDto>> genericEntity = new GenericEntity<List<CategoryDto>>(categoryDtos) {
        };

        return Response.ok(genericEntity).build();
    }

    @GET
    @Path("/{categoryId}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getSpecificCategories(@PathParam("categoryId") Integer categoryId, @Context HttpServletRequest request) {
        List<Forest> categories = remoteCatalogue.getForests();

        categories = categories.stream()
                .filter(category -> category.getForestId().equals(categoryId))
                .collect(Collectors.toList());

        if (categories.size() == 0) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        List<CategoryDto> categoryDtos = new ArrayList<>();
        categories.forEach(category -> categoryDtos.add(CategoryDto.builder(category)));

        GenericEntity<List<CategoryDto>> genericEntity = new GenericEntity<List<CategoryDto>>(categoryDtos) {
        };

        Locale locale = request.getLocale();

        if(locale.equals(Locale.US)){
            categoryDtos.forEach(translator::translate);
        }

        return Response.ok(genericEntity).build();
    }

    @POST
    @Path("/{categoryId}")
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postElementToCategory(@PathParam("categoryId") Integer categoryId, ElementDto elementDto) throws URISyntaxException {
        Elf element = ElementDto.builder(elementDto);
        Optional<Forest> category = remoteCatalogue.getForests().stream()
                .filter(actualCategory -> actualCategory.getForestId().equals(categoryId))
                .findFirst();

        Optional<ElementType> categoryType = remoteCatalogue.getElementTypes().stream()
                .filter(actualElemType -> actualElemType.getElementTypeId().equals(elementDto.getElementTypeId()))
                .findFirst();

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
