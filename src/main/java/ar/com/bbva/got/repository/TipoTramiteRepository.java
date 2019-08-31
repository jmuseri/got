package ar.com.bbva.got.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ar.com.bbva.got.model.Sector;
import ar.com.bbva.got.model.TipoTramite;

@RepositoryRestResource
public interface TipoTramiteRepository extends CrudRepository<TipoTramite, Integer> {
    
	Iterable<TipoTramite> findAllByActivoIsTrue();
    
    Iterable<TipoTramite> findAllByActivoAndSectorInicial(boolean activo, Sector sector);
    
}
