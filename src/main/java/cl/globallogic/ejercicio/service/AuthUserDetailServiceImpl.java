package cl.globallogic.ejercicio.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.globallogic.ejercicio.model.dto.LoginRequestBody;
import cl.globallogic.ejercicio.model.entity.UsuarioEntity;
import cl.globallogic.ejercicio.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username){
        UsuarioEntity usuario = this.usuarioRepository.findByEmail(username)
                                    .orElseThrow(()-> new UsernameNotFoundException("Usuario inválido"));
        return new User(
            usuario.getEmail()
            ,usuario.getPassword()
            ,new ArrayList<>()
        );
    }

    public UserDetails loadUserByCredentials(LoginRequestBody login){
        
        UsuarioEntity usuario = usuarioRepository.findByEmailAndPassword(login.getUsername(), passwordEncoder.encode(login.getPassword()))
                                    .orElseThrow(()-> new UsernameNotFoundException("Usuario inválido"));

        return new User(
            usuario.getEmail()
            ,usuario.getPassword()
            ,new ArrayList<>()
        );
    }
}