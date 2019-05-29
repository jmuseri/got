package com.sa.bbva.got.repositories;

import com.sa.bbva.got.model.Sector;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface SectorRepository extends CrudRepository<Sector, Integer> {
}
