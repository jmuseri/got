package com.sa.bbva.got.repository;

import com.sa.bbva.got.model.TramiteAutorizado;
import com.sa.bbva.got.model.TramiteAutorizadoKey;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TramiteAutorizadoRepository extends CrudRepository<TramiteAutorizado, Integer> {
    TramiteAutorizado findById(TramiteAutorizadoKey id);

}
