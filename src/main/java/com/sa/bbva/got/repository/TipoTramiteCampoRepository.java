package com.sa.bbva.got.repository;

import com.sa.bbva.got.model.TipoTramiteCampo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TipoTramiteCampoRepository extends CrudRepository<TipoTramiteCampo, Integer> {
    Iterable<TipoTramiteCampo> findAllByActivoIsTrue();
}
