package ar.com.bbva.got.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ar.com.bbva.got.model.TipoTramiteComision;
import ar.com.bbva.got.model.TipoTramiteComisionKey;

@RepositoryRestResource
public interface TipoTramiteComisionRepository extends CrudRepository<TipoTramiteComision, Integer> {
	TipoTramiteComision findById(TipoTramiteComisionKey id);

}
