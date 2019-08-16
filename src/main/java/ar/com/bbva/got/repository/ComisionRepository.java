package ar.com.bbva.got.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ar.com.bbva.got.model.Comision;

@RepositoryRestResource
public interface ComisionRepository extends CrudRepository<Comision, Integer> {
}
