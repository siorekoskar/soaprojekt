package game.answer.place;

import proj.PowerDto;

import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.List;

@WebService
public class GameSoapService implements GameSoapServiceImpl{

    @Override
    @WebMethod
    public void getNewPowerLevels(List<PowerDto> powerDtos){
        System.out.println();
    }
}
