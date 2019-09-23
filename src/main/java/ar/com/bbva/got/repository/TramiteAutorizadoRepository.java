package ar.com.bbva.got.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.transaction.annotation.Transactional;

import ar.com.bbva.got.model.TramiteAutorizado;
import ar.com.bbva.got.model.TramiteAutorizadoKey;

@RepositoryRestResource
public interface TramiteAutorizadoRepository extends CrudRepository<TramiteAutorizado, Integer> {
    TramiteAutorizado findById(TramiteAutorizadoKey id);

    @Transactional
    void deleteByIdAutorizadoId(Integer id);
    
    List<TramiteAutorizado> findByidAutorizadoId(Integer autorizadoId);

    
}
