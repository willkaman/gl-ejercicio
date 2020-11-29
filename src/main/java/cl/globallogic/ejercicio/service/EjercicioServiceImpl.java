package cl.globallogic.ejercicio.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.globallogic.ejercicio.dao.EjercicioDAO;
import cl.globallogic.ejercicio.entity.UsuarioEntity;

@Service
public class EjercicioServiceImpl implements EjercicioService{
    
    @Autowired
    EjercicioDAO ejercicioDAO;
    
    public List<UsuarioEntity> findAllUsuario(){
        return ejercicioDAO.findAllusuario();
    }

    public Optional<UsuarioEntity> getUsuarioByEmail(String email){
        return ejercicioDAO.getUsuarioByEmail(email);
    }

    public Optional<UsuarioEntity> getUsuario(Long id){
        return ejercicioDAO.getUsuario(id);
    }

    public Optional<UsuarioEntity> postUsuario(UsuarioEntity usuario){
        LocalDateTime now =LocalDateTime.now();
        usuario.setCreated(now);
        usuario.setModified(now);
        usuario.setLastLogin(now);
        usuario.setIsActive(Boolean.TRUE);
        usuario.setToken("");
        if(ejercicioDAO.getUsuarioByEmail(usuario.getEmail()).isPresent()){
            return Optional.empty();
        }
        else{
            return Optional.of(ejercicioDAO.saveUsuario(usuario));
        }
    }
}
