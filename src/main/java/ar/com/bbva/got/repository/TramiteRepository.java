package ar.com.bbva.got.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ar.com.bbva.got.model.EstadoTramite;
import ar.com.bbva.got.model.Sector;
import ar.com.bbva.got.model.Tramite;

@RepositoryRestResource
public interface TramiteRepository extends CrudRepository<Tramite, Integer> {

    List<Tramite> findBySectorActual(Sector sectorActual);

    List<Tramite> findByEstado(EstadoTramite estado);
    
}