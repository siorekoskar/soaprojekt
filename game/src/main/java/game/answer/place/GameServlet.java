package game.answer.place;


import game.answer.place.wsdl.SoapServices;
import game.answer.place.wsdl.SoapServicesImplService;
import game.answer.place.wsdl.UpdateElementDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

@WebServlet("/gameservlet")
public class GameServlet extends HttpServlet {

    private static final long serialVersionUID = -1870241428044715022L;

    private Logger logger = Logger.getLogger(GameServlet.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int elementUpdate = Integer.parseInt(req.getParameter("elementUpdate"));
        int elementId = Integer.parseInt(req.getParameter("elementId"));

        SoapServicesImplService soapServicesImplService = new SoapServicesImplService();
        SoapServices soapServicesImplPort = soapServicesImplService.getSoapServicesImplPort();
        UpdateElementDto updateElementDto = new UpdateElementDto();
        updateElementDto.setElementId(elementId);
        updateElementDto.setNewArrowCount(elementUpdate);
        Integer integer = soapServicesImplPort.modifyParameterForElement(updateElementDto);
    }
}
