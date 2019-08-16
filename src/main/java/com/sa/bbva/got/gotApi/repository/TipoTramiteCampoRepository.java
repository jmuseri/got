package com.sa.bbva.got.gotApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sa.bbva.got.gotApi.model.TipoTramiteCampo;
import com.sa.bbva.got.gotApi.model.TipoTramiteCampoKey;

@RepositoryRestResource
public interface TipoTramiteCampoRepository extends CrudRepository<TipoTramiteCampo, Integer> {
    Iterable<TipoTramiteCampo> findAllByActivoIsTrue();

    TipoTramiteCampo findById(TipoTramiteCampoKey id);

}
