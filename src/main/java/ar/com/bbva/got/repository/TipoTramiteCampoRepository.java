package ar.com.bbva.got.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ar.com.bbva.got.model.TipoTramiteCampo;
import ar.com.bbva.got.model.TipoTramiteCampoKey;

@RepositoryRestResource
public interface TipoTramiteCampoRepository extends CrudRepository<TipoTramiteCampo, Integer> {
    Iterable<TipoTramiteCampo> findAllByActivoIsTrue();

    TipoTramiteCampo findById(TipoTramiteCampoKey id);

}
