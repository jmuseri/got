package com.sa.bbva.got.repository;

import com.sa.bbva.got.model.TramiteAutorizado;
import com.sa.bbva.got.model.TramiteAutorizadoKey;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

@RepositoryRestResource
public interface TramiteAutorizadoRepository extends CrudRepository<TramiteAutorizado, Integer> {
    TramiteAutorizado findById(TramiteAutorizadoKey id);

    @Transactional
    void deleteByIdAutorizadoId(Integer id);
}
