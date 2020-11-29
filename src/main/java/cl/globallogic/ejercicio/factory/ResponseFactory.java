package cl.globallogic.ejercicio.factory;

import org.springframework.stereotype.Component;

import cl.globallogic.ejercicio.model.ResponseErrorBody;

@Component
public class ResponseFactory {
    public static final String RECURSO_YA_EXISTE = "El recurso ya existe";

    public ResponseErrorBody buildConflictErrorMessage(){
        return ResponseErrorBody.builder().mensaje(RECURSO_YA_EXISTE).build();
    }
}
