package Backend.Backend.Controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



import Backend.Backend.models.Post;
import Backend.Backend.models.Response;
import Backend.Backend.helpers.Helpers;


// notación de springboot para crear un restcontroler para poder hacer las petciones 

@RequestMapping("/posts")

@RestController

// creacipon de los cours sirven para que cualquier cliente se puede conectar al backend  y pueda hacer las peticiones para aceptar peticiones de cualquier cliente se deja un * 

@CrossOrigin(origins="*")

public class PostController {

  // para importar un servivio
  @Autowired
  private Helpers helpers;

 // creamos un arreglo vacio 

    public List<Post> posts = new ArrayList<>();

// inyectar valores al arregrlo creado. 

   public PostController(){

        Post post1 = new Post(UUID.randomUUID(), "Perro perdido en el parque", "Labrador retriever macho color dorado responde al nomre de max perdido el 5 de septiembre ", "https://th.bing.com/th/id/OIG3.wR1_lLzKdgz0q6PIeV_1?pid=ImgGn");

        Post post2 = new Post(UUID.randomUUID(), "vendo moto", "Vendo harley moto como nueva  ", "https://th.bing.com/th/id/OIG1.pI7v9zBVFNKzuJloTy0Q?pid=ImgGn");
        
        Post post3 = new Post(UUID.randomUUID(), "vendo apartamento ", "Vendo lindo apartammento ubicado en las mejores zonas de la ciudad  ", "https://th.bing.com/th/id/OIG3.9Iab6neh3UVTLLh4w_FF?pid=ImgGn");


      posts.add(post1);
      posts.add(post2);
      posts.add(post3);



      }

    // metodos 


    // obtener 

      @GetMapping
      public ResponseEntity<?> getPosts(){
         Map<String, Object> response=new HashMap<>();

              response.put("ok",true);
             response.put("data",posts);
             response.put("message","lista de posts"); 
            return new ResponseEntity<>(response,HttpStatus.OK);


         }
        //obtener por id

         @GetMapping("/{id}")
      public ResponseEntity<?> getPostsById(@PathVariable("id") String id){
         Map<String, Object> response=new HashMap<>();


         try{

          UUID idConvert=UUID.fromString(id);

          Post postEncontrado = posts.stream().filter(item->item.getId().equals(idConvert)).findFirst().orElse(null);
 
            if (postEncontrado == null) {
 
             response.put("ok",false);
             response.put("data","");
             response.put("message","post no encontrado"); 

           
             return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
             
            }
 
            response.put("ok",true);
            response.put("data",postEncontrado);
            response.put("message","peticion encontrada");
            return new ResponseEntity<>(response,HttpStatus.OK) ;

 
 
         }catch(IllegalArgumentException e){



          response.put("ok",false);
          response.put("data","");
          response.put("message","id no valido "); 

          return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);


         }
         

         }


         // guardar un nuevo dato 

        @PostMapping
        public ResponseEntity<?> savePost(@RequestBody Post post){
          Map<String, Object> response=new HashMap<>();
          
          post.setId(UUID.randomUUID());
          posts.add(post);
          response.put("ok", true);
          response.put("data",post);
          response.put("message","post creado"); 
         
          return new ResponseEntity<>(response, HttpStatus.CREATED);
        
                
        }

        // actualizar un dato 


        @PutMapping("/{id}")
        public ResponseEntity<?> updatePost(@RequestBody Post post, @PathVariable("id") String id){
          Map<String, Object> response=new HashMap<>();
          try{
            UUID idConvert = UUID.fromString(id);
            Post postEncontrado = posts.stream().filter(item->item.getId().equals(idConvert)).findFirst().orElse(null);
            if (postEncontrado==null) {

              response.put("ok", false);
              response.put("data","");
              response.put("message","post no creado"); 

              return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
              
            }

            if(post.getTitle()!= null && !post.getTitle().isEmpty()){
             
                    postEncontrado.setTitle(post.getTitle());

            }

            if (post.getDescription()!=null && !post.getDescription().isEmpty()) {

              postEncontrado.setDescription(post.getDescription());
              
            }

            if (post.getImgURL()!=null && !post.getImgURL().isEmpty()) {


            postEncontrado.setImgURL(post.getImgURL());

              
            }

           

            response.put("ok", true);
            response.put("data",postEncontrado);
            response.put("message","cambo realizado ");
                    
            return new ResponseEntity<>(response, HttpStatus.OK);
          }
          catch(IllegalArgumentException e){


          response.put("ok",false);
          response.put("data","");
          response.put("message","id no valido ");        
          return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

        }


        } 

      // borrar un dato 


      @DeleteMapping("/{id}")
        public ResponseEntity<?> deletePost(@PathVariable("id") String id){
          Map<String, Object> response=new HashMap<>();

          try{

              UUID idConvert=UUID.fromString(id);

              Post postEncontrado = posts.stream().filter(item->item.getId().equals(idConvert)).findFirst().orElse(null);
          if (postEncontrado==null) {

            response.put("ok", false);
            response.put("data","");
            response.put("message","post no creado");


          response = helpers.apiResponse(new Response<>(true,posts,"petición no realizada"));          
          return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
            
          }
          
          posts.remove(postEncontrado);
          response.put("ok", true);
          response.put("data","");
          response.put("message","dato elminado"); 
                  
          return new ResponseEntity<>(response, HttpStatus.OK);

          }catch(IllegalArgumentException e){


          response.put("ok",false);
          response.put("data","");
          response.put("message","id no valido ");
        
          return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

        }

          

      }

               
    



}






    

