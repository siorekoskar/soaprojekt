package soap;


import proj.CategoryTypeDto;
import proj.ElementTypeDto;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface SoapServices {

    @WebMethod
    void addNewCategories(CategoryTypeDto categoryTypeDto);

    @WebMethod
    void addNewCharacters(ElementTypeDto elementTypeDto);
}
