package com.sa.bbva.got.gotApi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.sa.bbva.got.gotApi.model.Sector;

@RepositoryRestResource
public interface SectorRepository extends CrudRepository<Sector, Integer> {

    Iterable<Sector> findAllByActivoIsTrue();

}
