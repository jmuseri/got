package com.sa.bbva.got.repository;

import com.sa.bbva.got.model.Comision;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ComisionRepository extends CrudRepository<Comision, Integer> {
}
