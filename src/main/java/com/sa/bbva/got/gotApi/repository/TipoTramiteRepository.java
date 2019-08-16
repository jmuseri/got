package com.sa.bbva.got.gotApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sa.bbva.got.gotApi.model.TipoTramite;

@RepositoryRestResource
public interface TipoTramiteRepository extends CrudRepository<TipoTramite, Integer> {
    Iterable<TipoTramite> findAllByActivoIsTrue();
}
