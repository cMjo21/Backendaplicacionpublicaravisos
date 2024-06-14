package Backend.Backend.helpers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import Backend.Backend.models.Response;

// con esta notación puedo configurar la funciión creada para que se comporte como servicio.
@Service

public class Helpers {

    
    public  <T> Map<String, Object> apiResponse(Response<T> data){
        Map<String, Object> response = new HashMap<>(); 
        response.put("ok", data.isOk());
        response.put("data", data.getData());
        response.put("message", data.getMessage());

        return response;
    
}

}
