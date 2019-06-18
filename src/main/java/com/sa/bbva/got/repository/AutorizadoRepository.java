package com.sa.bbva.got.repository;

import com.sa.bbva.got.model.Autorizado;
import com.sa.bbva.got.model.AutorizadoKey;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AutorizadoRepository extends CrudRepository<Autorizado, AutorizadoKey> {
    Iterable<Autorizado> findAllByIdClienteId(Integer idCliente);
}
