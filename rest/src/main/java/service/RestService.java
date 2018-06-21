package service;

import entity.*;
import proj.RemoteCatalogue;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.ws.rs.core.GenericEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@ManagedBean
public class RestService {

    @EJB(mappedName = "java:global/server/Catalogue!proj.RemoteCatalogue")
    private RemoteCatalogue remoteCatalogue;

    public List<Forest> filterCategories(Integer categoryId) {
        List<Forest> categories = remoteCatalogue.getForests();

        return categories.stream()
                .filter(category -> category.getForestId().equals(categoryId))
                .collect(Collectors.toList());
    }

    public GenericEntity<List<CategoryDto>> generateGenericCategories(List<CategoryDto> categoryDtos) {
        return new GenericEntity<List<CategoryDto>>(categoryDtos) {
        };
    }

    public List<CategoryDto> generateDtos(List<Forest> categories){
        List<CategoryDto> categoryDtos = new ArrayList<>();
        categories.forEach(category -> categoryDtos.add(CategoryDto.builder(category)));
        return categoryDtos;
    }

    public Optional<Forest> findCategory(Integer categoryId){
        return remoteCatalogue.getForests().stream()
                .filter(actualCategory -> actualCategory.getForestId().equals(categoryId))
                .findFirst();
    }

    public Optional<ElementType> findElementType(ElementDto elementDto){
        return remoteCatalogue.getElementTypes().stream()
                .filter(actualElemType -> actualElemType.getElementTypeId().equals(elementDto.getElementTypeId()))
                .findFirst();
    }
}
