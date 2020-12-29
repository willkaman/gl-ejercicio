package cl.globallogic.ejercicio.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import cl.globallogic.ejercicio.dao.EjercicioDAO;
import cl.globallogic.ejercicio.exception.ConflictException;
import cl.globallogic.ejercicio.exception.NoDataFoundException;
import cl.globallogic.ejercicio.model.entity.UsuarioEntity;

@Service
public class EjercicioServiceImpl implements EjercicioService{
    
    private EjercicioDAO ejercicioDAO;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public EjercicioServiceImpl(EjercicioDAO ejercicioDAO, PasswordEncoder passwordEncoder){
        this.ejercicioDAO = ejercicioDAO;
        this.passwordEncoder = passwordEncoder;
    }

    
    public List<UsuarioEntity> findAllUsuario(){
        return ejercicioDAO.findAllusuario();
    }

    public UsuarioEntity getUsuarioByEmail(String email){
        return ejercicioDAO.getUsuarioByEmail(email)
            .orElseThrow(() -> new NoDataFoundException());
    }

    public UsuarioEntity getUsuario(Long id){
        return ejercicioDAO.getUsuario(id)
            .orElseThrow(() -> new NoDataFoundException());
    }

    public UsuarioEntity postUsuario(UsuarioEntity usuario){
        LocalDateTime now =LocalDateTime.now();
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuario.setCreated(now);
        usuario.setModified(now);
        usuario.setLastLogin(now);
        usuario.setIsActive(Boolean.TRUE);
        usuario.setToken("");

        try{
            // forzar y capturar excepcion en caso de existencia previa del email
            getUsuarioByEmail(usuario.getEmail());
            throw new ConflictException();
        }
        catch (NoDataFoundException ex){
            return ejercicioDAO.saveUsuario(usuario);
        }
    
    }
}
