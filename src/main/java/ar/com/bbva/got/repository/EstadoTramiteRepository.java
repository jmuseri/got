package ar.com.bbva.got.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ar.com.bbva.got.model.EstadoTramite;

@RepositoryRestResource
public interface EstadoTramiteRepository extends CrudRepository<EstadoTramite, Integer> {
}
