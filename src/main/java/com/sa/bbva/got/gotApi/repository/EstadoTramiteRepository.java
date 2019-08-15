package com.sa.bbva.got.gotApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sa.bbva.got.gotApi.model.EstadoTramite;

@RepositoryRestResource
public interface EstadoTramiteRepository extends CrudRepository<EstadoTramite, Integer> {
}
