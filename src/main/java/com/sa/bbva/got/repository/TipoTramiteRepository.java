package com.sa.bbva.got.repository;

import com.sa.bbva.got.model.TipoTramite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TipoTramiteRepository extends CrudRepository<TipoTramite, Integer> {
}
