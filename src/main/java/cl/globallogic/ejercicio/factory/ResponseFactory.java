package cl.globallogic.ejercicio.factory;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import cl.globallogic.ejercicio.model.ResponseErrorBody;

@Component
public class ResponseFactory {
    public static final String RECURSO_YA_EXISTE = "El recurso ya existe";

    public static ResponseEntity<?> buildNotfoundErrorResponse(){
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }
    
    public static ResponseEntity<?> buildConflictErrorResponse(){
        return new ResponseEntity<>(ResponseErrorBody.builder().mensaje(RECURSO_YA_EXISTE).build(), HttpStatus.CONFLICT);
    }

    public static ResponseEntity<?> buildOkReponseWithBody(Object body){
        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    public static ResponseEntity<?> buildCreatedReponseWithBody(Object body){
        return new ResponseEntity<>(body, HttpStatus.CREATED);
    }

}
