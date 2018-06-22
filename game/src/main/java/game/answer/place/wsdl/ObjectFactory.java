
package game.answer.place.wsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the game.answer.place.wsdl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _ModifyLabel_QNAME = new QName("http://soap/", "modifyLabel");
    private final static QName _AddNewCategories_QNAME = new QName("http://soap/", "addNewCategories");
    private final static QName _ModifyLabelResponse_QNAME = new QName("http://soap/", "modifyLabelResponse");
    private final static QName _ModifyParameterForElementResponse_QNAME = new QName("http://soap/", "modifyParameterForElementResponse");
    private final static QName _AddNewCategoriesResponse_QNAME = new QName("http://soap/", "addNewCategoriesResponse");
    private final static QName _AddNewCharactersResponse_QNAME = new QName("http://soap/", "addNewCharactersResponse");
    private final static QName _ModifyParameterForElement_QNAME = new QName("http://soap/", "modifyParameterForElement");
    private final static QName _AddNewCharacters_QNAME = new QName("http://soap/", "addNewCharacters");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: game.answer.place.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ModifyParameterForElement }
     * 
     */
    public ModifyParameterForElement createModifyParameterForElement() {
        return new ModifyParameterForElement();
    }

    /**
     * Create an instance of {@link AddNewCategoriesResponse }
     * 
     */
    public AddNewCategoriesResponse createAddNewCategoriesResponse() {
        return new AddNewCategoriesResponse();
    }

    /**
     * Create an instance of {@link AddNewCharactersResponse }
     * 
     */
    public AddNewCharactersResponse createAddNewCharactersResponse() {
        return new AddNewCharactersResponse();
    }

    /**
     * Create an instance of {@link AddNewCharacters }
     * 
     */
    public AddNewCharacters createAddNewCharacters() {
        return new AddNewCharacters();
    }

    /**
     * Create an instance of {@link ModifyLabel }
     * 
     */
    public ModifyLabel createModifyLabel() {
        return new ModifyLabel();
    }

    /**
     * Create an instance of {@link AddNewCategories }
     * 
     */
    public AddNewCategories createAddNewCategories() {
        return new AddNewCategories();
    }

    /**
     * Create an instance of {@link ModifyLabelResponse }
     * 
     */
    public ModifyLabelResponse createModifyLabelResponse() {
        return new ModifyLabelResponse();
    }

    /**
     * Create an instance of {@link ModifyParameterForElementResponse }
     * 
     */
    public ModifyParameterForElementResponse createModifyParameterForElementResponse() {
        return new ModifyParameterForElementResponse();
    }

    /**
     * Create an instance of {@link UpdateElementDto }
     * 
     */
    public UpdateElementDto createUpdateElementDto() {
        return new UpdateElementDto();
    }

    /**
     * Create an instance of {@link CategoryTypeDto }
     * 
     */
    public CategoryTypeDto createCategoryTypeDto() {
        return new CategoryTypeDto();
    }

    /**
     * Create an instance of {@link ElementTypeDto }
     * 
     */
    public ElementTypeDto createElementTypeDto() {
        return new ElementTypeDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyLabel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "modifyLabel")
    public JAXBElement<ModifyLabel> createModifyLabel(ModifyLabel value) {
        return new JAXBElement<ModifyLabel>(_ModifyLabel_QNAME, ModifyLabel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewCategories }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "addNewCategories")
    public JAXBElement<AddNewCategories> createAddNewCategories(AddNewCategories value) {
        return new JAXBElement<AddNewCategories>(_AddNewCategories_QNAME, AddNewCategories.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyLabelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "modifyLabelResponse")
    public JAXBElement<ModifyLabelResponse> createModifyLabelResponse(ModifyLabelResponse value) {
        return new JAXBElement<ModifyLabelResponse>(_ModifyLabelResponse_QNAME, ModifyLabelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyParameterForElementResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "modifyParameterForElementResponse")
    public JAXBElement<ModifyParameterForElementResponse> createModifyParameterForElementResponse(ModifyParameterForElementResponse value) {
        return new JAXBElement<ModifyParameterForElementResponse>(_ModifyParameterForElementResponse_QNAME, ModifyParameterForElementResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewCategoriesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "addNewCategoriesResponse")
    public JAXBElement<AddNewCategoriesResponse> createAddNewCategoriesResponse(AddNewCategoriesResponse value) {
        return new JAXBElement<AddNewCategoriesResponse>(_AddNewCategoriesResponse_QNAME, AddNewCategoriesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewCharactersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "addNewCharactersResponse")
    public JAXBElement<AddNewCharactersResponse> createAddNewCharactersResponse(AddNewCharactersResponse value) {
        return new JAXBElement<AddNewCharactersResponse>(_AddNewCharactersResponse_QNAME, AddNewCharactersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ModifyParameterForElement }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "modifyParameterForElement")
    public JAXBElement<ModifyParameterForElement> createModifyParameterForElement(ModifyParameterForElement value) {
        return new JAXBElement<ModifyParameterForElement>(_ModifyParameterForElement_QNAME, ModifyParameterForElement.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddNewCharacters }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://soap/", name = "addNewCharacters")
    public JAXBElement<AddNewCharacters> createAddNewCharacters(AddNewCharacters value) {
        return new JAXBElement<AddNewCharacters>(_AddNewCharacters_QNAME, AddNewCharacters.class, null, value);
    }

}
