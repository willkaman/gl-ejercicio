package cl.globallogic.ejercicio.repository;

import org.springframework.data.repository.CrudRepository;

import cl.globallogic.ejercicio.model.entity.PhoneEntity;

public interface PhoneRepository extends CrudRepository<PhoneEntity, Long> {}
