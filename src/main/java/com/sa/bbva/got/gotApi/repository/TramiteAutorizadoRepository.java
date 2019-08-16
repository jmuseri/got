package com.sa.bbva.got.gotApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import com.sa.bbva.got.gotApi.model.TramiteAutorizado;
import com.sa.bbva.got.gotApi.model.TramiteAutorizadoKey;

@RepositoryRestResource
public interface TramiteAutorizadoRepository extends CrudRepository<TramiteAutorizado, Integer> {
    TramiteAutorizado findById(TramiteAutorizadoKey id);

    @Transactional
    void deleteByIdAutorizadoId(Integer id);
}
