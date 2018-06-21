package soap;

import entity.Elf;
import proj.CategoryTypeDto;
import proj.ElementTypeDto;
import proj.RemoteCatalogue;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.Optional;

@WebService
public class SoapServicesImpl implements SoapServices {

    @EJB(mappedName = "java:global/server/Catalogue!proj.RemoteCatalogue")
    private RemoteCatalogue remoteCatalogue;

    @Override
    @WebMethod
    public void addNewCategories(CategoryTypeDto categoryTypeDto) {
        remoteCatalogue.sendCategoryType(categoryTypeDto);
    }

    @Override
    @WebMethod
    public void addNewCharacters(ElementTypeDto elementTypeDto) {
        remoteCatalogue.sendElementType(elementTypeDto);
    }

    @Override
    @WebMethod
    public Integer modifyParameterForElement(UpdateElementDto updateElementDto) {
        Optional<Elf> maybeElement = remoteCatalogue.getElves().stream()
                .filter(element -> element.getElfId().equals(updateElementDto.getElementId()))
                .findFirst();
        if (maybeElement.isPresent()) {
            Elf actualElement = maybeElement.get();
            actualElement.setArrowsCount(updateElementDto.getNewArrowCount());
            return remoteCatalogue.updateElement(actualElement);
        } else {
            return null;
        }
    }


}
