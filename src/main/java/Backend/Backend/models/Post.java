package Backend.Backend.models;

import java.util.UUID;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// GENERAR CONSTRUCTOR 

@AllArgsConstructor

// DEJAR EÑ CONSTRUCTOR OPCIONAL 

@NoArgsConstructor

// GENERA GETTERS Y SETTERS
@Data

public class Post {

   private UUID id;
   private String title;
   private String description;
   private String imgURL;


}

