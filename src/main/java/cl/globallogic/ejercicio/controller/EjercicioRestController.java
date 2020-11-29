package cl.globallogic.ejercicio.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
            return ResponseFactory.buildConflictErrorResponse();
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable("id") Long id){
        Optional<UsuarioEntity> usuario = ejercicioService.getUsuario(id);

        if(usuario.isPresent()){
            return ResponseFactory.buildOkReponseWithBody(ejercicioService.getUsuario(id).get());
        }
        else{
            return ResponseFactory.buildNotfoundErrorResponse();
        }
    }
}
