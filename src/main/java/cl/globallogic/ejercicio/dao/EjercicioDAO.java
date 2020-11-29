package cl.globallogic.ejercicio.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.stereotype.Repository;

import cl.globallogic.ejercicio.entity.UsuarioEntity;
import cl.globallogic.ejercicio.repository.UsuarioRepository;


@Repository
public class EjercicioDAO {
    @Autowired
    UsuarioRepository usuarioRepository;

    public List<UsuarioEntity> findAllusuario(){
    List<UsuarioEntity> result = new ArrayList<UsuarioEntity>();
        usuarioRepository.findAll().forEach(result::add);
        return result;
    }

    public Optional<UsuarioEntity> getUsuario(Long id){
        return usuarioRepository.findById(id);
    }

    public Optional<UsuarioEntity> getUsuarioByEmail(String email){
        return usuarioRepository.findByEmail(email);
    }

    public UsuarioEntity saveUsuario(UsuarioEntity usuario){
        return usuarioRepository.save(usuario);
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {
        return new PersistenceExceptionTranslationPostProcessor();
    }
}


