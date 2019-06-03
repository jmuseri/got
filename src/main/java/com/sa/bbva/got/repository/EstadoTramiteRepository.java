package com.sa.bbva.got.repository;

import com.sa.bbva.got.model.EstadoTramite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface EstadoTramiteRepository extends CrudRepository<EstadoTramite, Integer> {
}
