package game.answer.place;

import proj.PowerDto;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public interface GameSoapServiceImpl {
    @WebMethod
    void getNewPowerLevels(List<PowerDto> powerDtos);
}
