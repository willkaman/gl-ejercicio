package cl.globallogic.ejercicio.service;

import java.util.List;

import cl.globallogic.ejercicio.entity.UsuarioEntity;

public interface EjercicioService {
    public List<UsuarioEntity> findAllUsuario();

    public UsuarioEntity getUsuario(Long id);

    public UsuarioEntity postUsuario(UsuarioEntity usuario);

    public UsuarioEntity getUsuarioByEmail(String email);
}
