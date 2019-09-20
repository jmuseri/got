package ar.com.bbva.got.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ar.com.bbva.got.model.MotivoRechazo;

@RepositoryRestResource
public interface MotivoRechazoRepository extends CrudRepository<MotivoRechazo, Integer> {
        
    Iterable<MotivoRechazo> findAllByTipoTramiteId(Integer tipoTramiteId);
    
}
