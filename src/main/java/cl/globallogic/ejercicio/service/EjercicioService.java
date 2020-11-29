package cl.globallogic.ejercicio.service;

import java.util.List;
import java.util.Optional;

import cl.globallogic.ejercicio.entity.UsuarioEntity;

public interface EjercicioService {
    public List<UsuarioEntity> findAllUsuario();

    public Optional<UsuarioEntity> getUsuario(Long id);

    public Optional<UsuarioEntity> postUsuario(UsuarioEntity usuario);

    public Optional<UsuarioEntity> getUsuarioByEmail(String email);
}
