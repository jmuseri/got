package ar.com.bbva.got.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ar.com.bbva.got.model.Autorizado;

@RepositoryRestResource
public interface AutorizadoRepository extends CrudRepository<Autorizado, Integer> {
    Iterable<Autorizado> findByNroClienteEmpresa(Integer clienteId);
    Iterable<Autorizado> findByNroClienteEmpresaAndActivoIsTrueOrCuitEmpresaAndActivoIsTrue(Integer nroClienteEmpresa, String cuitEmpresa);
    
    List<Autorizado> findByNroDocumento(String nroDocumento);
    
}
