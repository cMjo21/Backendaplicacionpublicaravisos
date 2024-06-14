package Backend.Backend.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// la T signigica que el dato es generico porque en el controlador el mismo dato tienen diferentes tipos de dato, si se tienen más datos genericos se pueden usar mas letras.

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Response<T>{

    boolean ok;
    T data;  
    String message; 
}
