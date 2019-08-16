package com.sa.bbva.got.gotApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sa.bbva.got.gotApi.model.Autorizado;

@RepositoryRestResource
public interface AutorizadoRepository extends CrudRepository<Autorizado, Integer> {
    Iterable<Autorizado> findByClienteId(Integer clienteId);
}
