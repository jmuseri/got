package com.sa.bbva.got.gotApi.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sa.bbva.got.gotApi.model.EstadoTramite;
import com.sa.bbva.got.gotApi.model.Sector;
import com.sa.bbva.got.gotApi.model.Tramite;

@RepositoryRestResource
public interface TramiteRepository extends CrudRepository<Tramite, Integer> {

    List<Tramite> findBySectorActual(Sector sectorActual);

    List<Tramite> findByEstado(EstadoTramite estado);
    
}
