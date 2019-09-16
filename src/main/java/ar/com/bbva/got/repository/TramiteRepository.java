package ar.com.bbva.got.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ar.com.bbva.got.model.EstadoTramite;
import ar.com.bbva.got.model.Sector;
import ar.com.bbva.got.model.TipoTramite;
import ar.com.bbva.got.model.Tramite;

@RepositoryRestResource
public interface TramiteRepository extends CrudRepository<Tramite, Integer> {

    List<Tramite> findBySectorActual(Sector sectorActual);

    List<Tramite> findByEstado(EstadoTramite estado);
    
    List<Tramite> findByNroClienteEmpresaAndEstadoAndTipoTramite(Integer nroClienteEmpresa, EstadoTramite estado, TipoTramite tipoTramite);
    
    List<Tramite> findByNroClienteEmpresaAndEstado(Integer nroClienteEmpresa, EstadoTramite estado);
    
    List<Tramite> findByNroClienteEmpresaAndTipoTramite(Integer nroClienteEmpresa, TipoTramite tipoTramite);
    
    List<Tramite> findByNroClienteEmpresa(Integer nroClienteEmpresa);
    
}
