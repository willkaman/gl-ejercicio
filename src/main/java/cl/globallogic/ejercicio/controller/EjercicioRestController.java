package cl.globallogic.ejercicio.controller;

import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.globallogic.ejercicio.entity.UsuarioEntity;
import cl.globallogic.ejercicio.factory.ResponseFactory;
import cl.globallogic.ejercicio.model.UsuarioRequestBody;
import cl.globallogic.ejercicio.service.EjercicioService;

@RestController
public class EjercicioRestController {

    @Autowired
    EjercicioService ejercicioService;

    @Autowired
    ResponseFactory responseFactory;
    
    @PostMapping("/usuario")
    public ResponseEntity<?> createUsuario(@RequestBody UsuarioRequestBody usuarioRB){
        Optional<UsuarioEntity> usuario = ejercicioService.postUsuario(
            UsuarioEntity.builder()
                .email(usuarioRB.getEmail())
                .name(usuarioRB.getName())
                .password(usuarioRB.getPassword())
                .build()
        );

        if(usuario.isPresent()){
            return new ResponseEntity<>(usuario.get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(responseFactory.buildConflictErrorMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> getUsuario(@PathParam(value = "id") Long id){

        if(ejercicioService.getUsuario(id).isPresent()){
            return new ResponseEntity<>(ejercicioService.getUsuario(id).get(), HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(ejercicioService.findAllUsuario(), HttpStatus.NOT_FOUND);
        }

    }
}
