package com.sa.bbva.got.repository;

import com.sa.bbva.got.model.Autorizado;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface AutorizadoRepository extends CrudRepository<Autorizado, Integer> {
}
