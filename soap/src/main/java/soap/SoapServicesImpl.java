package soap;

import proj.CategoryTypeDto;
import proj.ElementTypeDto;
import proj.RemoteCatalogue;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebService;

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
}
