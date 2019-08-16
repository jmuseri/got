package com.sa.bbva.got.gotApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sa.bbva.got.gotApi.model.TramiteDetalle;
import com.sa.bbva.got.gotApi.model.TramiteDetalleKey;

@RepositoryRestResource
public interface TramiteDetalleRepository extends CrudRepository<TramiteDetalle, Integer> {
    TramiteDetalle findById(TramiteDetalleKey id);

}
