
package com.gamewsdl;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.gamewsdl package. 
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

    private final static QName _GetNewPowerLevels_QNAME = new QName("http://place.answer.game/", "getNewPowerLevels");
    private final static QName _GetNewPowerLevelsResponse_QNAME = new QName("http://place.answer.game/", "getNewPowerLevelsResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gamewsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetNewPowerLevels }
     * 
     */
    public GetNewPowerLevels createGetNewPowerLevels() {
        return new GetNewPowerLevels();
    }

    /**
     * Create an instance of {@link GetNewPowerLevelsResponse }
     * 
     */
    public GetNewPowerLevelsResponse createGetNewPowerLevelsResponse() {
        return new GetNewPowerLevelsResponse();
    }

    /**
     * Create an instance of {@link PowerDto }
     * 
     */
    public PowerDto createPowerDto() {
        return new PowerDto();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNewPowerLevels }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://place.answer.game/", name = "getNewPowerLevels")
    public JAXBElement<GetNewPowerLevels> createGetNewPowerLevels(GetNewPowerLevels value) {
        return new JAXBElement<GetNewPowerLevels>(_GetNewPowerLevels_QNAME, GetNewPowerLevels.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNewPowerLevelsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://place.answer.game/", name = "getNewPowerLevelsResponse")
    public JAXBElement<GetNewPowerLevelsResponse> createGetNewPowerLevelsResponse(GetNewPowerLevelsResponse value) {
        return new JAXBElement<GetNewPowerLevelsResponse>(_GetNewPowerLevelsResponse_QNAME, GetNewPowerLevelsResponse.class, null, value);
    }

}
