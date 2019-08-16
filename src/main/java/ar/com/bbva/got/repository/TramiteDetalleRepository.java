package ar.com.bbva.got.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ar.com.bbva.got.model.TramiteDetalle;
import ar.com.bbva.got.model.TramiteDetalleKey;

@RepositoryRestResource
public interface TramiteDetalleRepository extends CrudRepository<TramiteDetalle, Integer> {
    TramiteDetalle findById(TramiteDetalleKey id);

}
