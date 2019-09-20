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
    
    List<Tramite> findByNroClienteEmpresaAndEstadoAndTipoTramiteId(Integer nroClienteEmpresa, EstadoTramite estado, Integer tipoTramiteId);
    
    List<Tramite> findByNroClienteEmpresaAndEstado(Integer nroClienteEmpresa, EstadoTramite estado);
    
    List<Tramite> findByNroClienteEmpresaAndTipoTramiteId(Integer nroClienteEmpresa, Integer tipoTramiteId);
    
    List<Tramite> findByNroClienteEmpresa(Integer nroClienteEmpresa);
    
    
    List<Tramite> findByCuitEmpresaAndEstadoAndTipoTramiteId(String cuitEmpresa, EstadoTramite estado, Integer tipoTramiteId);
    
    List<Tramite> findByCuitEmpresaAndEstado(String cuitEmpresa, EstadoTramite estado);
    
     List<Tramite> findByCuitEmpresaAndTipoTramiteId(String cuitEmpresa, Integer tipoTramiteId);
    
    List<Tramite> findByCuitEmpresa(String cuit);
    
    
    
}
