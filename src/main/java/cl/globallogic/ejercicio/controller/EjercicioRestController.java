package cl.globallogic.ejercicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
        return ResponseFactory.buildCreatedReponseWithBody(ejercicioService.postUsuario(
            UsuarioEntity.builder()
                .email(usuarioRB.getEmail())
                .name(usuarioRB.getName())
                .password(usuarioRB.getPassword())
                .build()
        ));
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable("id") Long id){
        return ResponseFactory.buildOkReponseWithBody(ejercicioService.getUsuario(id));
    }
}
