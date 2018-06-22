
package game.answer.place.wsdl;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "SoapServices", targetNamespace = "http://soap/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface SoapServices {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.Integer
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "modifyParameterForElement", targetNamespace = "http://soap/", className = "game.answer.place.wsdl.ModifyParameterForElement")
    @ResponseWrapper(localName = "modifyParameterForElementResponse", targetNamespace = "http://soap/", className = "game.answer.place.wsdl.ModifyParameterForElementResponse")
    public Integer modifyParameterForElement(
        @WebParam(name = "arg0", targetNamespace = "")
        UpdateElementDto arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "addNewCharacters", targetNamespace = "http://soap/", className = "game.answer.place.wsdl.AddNewCharacters")
    @ResponseWrapper(localName = "addNewCharactersResponse", targetNamespace = "http://soap/", className = "game.answer.place.wsdl.AddNewCharactersResponse")
    public void addNewCharacters(
        @WebParam(name = "arg0", targetNamespace = "")
        ElementTypeDto arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "addNewCategories", targetNamespace = "http://soap/", className = "game.answer.place.wsdl.AddNewCategories")
    @ResponseWrapper(localName = "addNewCategoriesResponse", targetNamespace = "http://soap/", className = "game.answer.place.wsdl.AddNewCategoriesResponse")
    public void addNewCategories(
        @WebParam(name = "arg0", targetNamespace = "")
        CategoryTypeDto arg0);

}