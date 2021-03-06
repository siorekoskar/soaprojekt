
package com.gamewsdl;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
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
@WebService(name = "GameSoapServiceImpl", targetNamespace = "http://place.answer.game/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface GameSoapServiceImpl {


    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "getNewPowerLevels", targetNamespace = "http://place.answer.game/", className = "com.gamewsdl.GetNewPowerLevels")
    @ResponseWrapper(localName = "getNewPowerLevelsResponse", targetNamespace = "http://place.answer.game/", className = "com.gamewsdl.GetNewPowerLevelsResponse")
    public void getNewPowerLevels(
        @WebParam(name = "arg0", targetNamespace = "")
        List<PowerDto> arg0);

}
