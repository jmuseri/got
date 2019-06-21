package com.sa.bbva.got.repository;

import java.util.List;

import com.sa.bbva.got.model.EstadoTramite;
import com.sa.bbva.got.model.Sector;
import com.sa.bbva.got.model.Tramite;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TramiteRepository extends CrudRepository<Tramite, Integer> {

    List<Tramite> findBySectorActual(Sector sectorActual);

    List<Tramite> findByEstado(EstadoTramite estado);
    
}
