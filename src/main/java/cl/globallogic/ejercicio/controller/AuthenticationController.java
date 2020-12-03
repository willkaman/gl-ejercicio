package cl.globallogic.ejercicio.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import cl.globallogic.ejercicio.exception.UnauthException;
import cl.globallogic.ejercicio.model.dto.LoginRequestBody;
import cl.globallogic.ejercicio.security.JWTAuthorizationUtils;
import cl.globallogic.ejercicio.service.AuthUserDetailServiceImpl;
import cl.globallogic.ejercicio.service.EjercicioService;

@RestController
public class AuthenticationController {

    @Autowired
    EjercicioService ejercicioService;

    @Autowired
    AuthUserDetailServiceImpl authUserDetailServiceImpl;

    @Autowired
    JWTAuthorizationUtils JWTAuthorizationUtils;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/authme")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequestBody loginRequestBody) throws Exception {
        UserDetails userDetails = authUserDetailServiceImpl.loadUserByUsername(loginRequestBody.getUsername());
        Boolean isMatch = passwordEncoder.matches(loginRequestBody.getPassword(), userDetails.getPassword());

        if( isMatch) {
            return ResponseEntity.ok(JWTAuthorizationUtils.getJWTToken(userDetails));
        }
        else {
            throw new UnauthException();
        }
        
    }

}