package cl.globallogic.ejercicio.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import cl.globallogic.ejercicio.entity.UsuarioEntity;

public interface UsuarioRepository extends CrudRepository<UsuarioEntity, Long> {

    Optional<UsuarioEntity> findByEmail(String email);

    Optional<UsuarioEntity> findByEmailAndPassword(String email, String password);
  }
