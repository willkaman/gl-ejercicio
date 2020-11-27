package cl.globallogic.ejercicio.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.globallogic.ejercicio.model.UsuarioRequestBody;

@RestController
public class EjercicioRestController {
    
    @PostMapping("/usuario")
    public ResponseEntity<?> createUsuario(@RequestBody UsuarioRequestBody usuarioRB){
        
    if(usuarioRB != null)
        return new ResponseEntity<>(null, HttpStatus.OK);
    else
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
