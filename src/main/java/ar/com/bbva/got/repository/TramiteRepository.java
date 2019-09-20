package ar.com.bbva.got.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import ar.com.bbva.got.model.EstadoTramite;
import ar.com.bbva.got.model.Sector;
import ar.com.bbva.got.model.Tramite;

@RepositoryRestResource
public interface TramiteRepository extends CrudRepository<Tramite, Integer> {

    
    List<Tramite> findByNroClienteEmpresaAndEstadoAndTipoTramiteId(Integer nroClienteEmpresa, EstadoTramite estado, Integer tipoTramiteId);
    
    List<Tramite> findByNroClienteEmpresaAndEstado(Integer nroClienteEmpresa, EstadoTramite estado);
    
    List<Tramite> findByNroClienteEmpresaAndTipoTramiteId(Integer nroClienteEmpresa, Integer tipoTramiteId);
    
    List<Tramite> findByNroClienteEmpresa(Integer nroClienteEmpresa);
    
    
    List<Tramite> findByCuitEmpresaAndEstadoAndTipoTramiteId(String cuitEmpresa, EstadoTramite estado, Integer tipoTramiteId);
    
    List<Tramite> findByCuitEmpresaAndEstado(String cuitEmpresa, EstadoTramite estado);
    
     List<Tramite> findByCuitEmpresaAndTipoTramiteId(String cuitEmpresa, Integer tipoTramiteId);
    
    List<Tramite> findByCuitEmpresa(String cuit);
    
//    //1 Param
    List<Tramite> findBySectorActual(Sector sectorActual);
    List<Tramite> findByEstado(EstadoTramite estado);
    List<Tramite> findBySectorActualId(String sectorActualId);
    List<Tramite> findByAutorizadoAutorizadoNroDocumento(String autorizadoAutorizadoNroDocumento);
//    
//    // 2 Params
//    List<Tramite> findByEstadoAndTipoTramiteId(EstadoTramite estado, Integer tipoTramiteId);
//    List<Tramite> findByEstadoAndSectorActualId(EstadoTramite estado, String sectorActualId);
//    List<Tramite> findByEstadoAndAutorizadoAutorizadoNroDocumento(EstadoTramite estado, String autorizadoAutorizadoNroDocumento);
//    List<Tramite> findByTipoTramiteIdAndSectorActualId(Integer tipoTramiteId, String sectorActualId);
//    List<Tramite> findByTipoTramiteIdAndAutorizadoAutorizadoNroDocumento(Integer tipoTramiteId, String autorizadoAutorizadoNroDocumento);
//    List<Tramite> findBySectorActualIdAndAutorizadoAutorizadoNroDocumento(String sectorActualId, String autorizadoAutorizadoNroDocumento);
//    
//    //3 Params 
//    List<Tramite> findByEstadoAndTipoTramiteIdAndSectorActualId(EstadoTramite estado, Integer tipoTramiteId, String sectorActualId);
//    List<Tramite> findByEstadoAndTipoTramiteIdAndAutorizadoAutorizadoNroDocumento(EstadoTramite estado, Integer tipoTramiteId, String autorizadoAutorizadoNroDocumento);
//    List<Tramite> findByEstadoAndSectorActualIdAndAutorizadoAutorizadoNroDocumento(EstadoTramite estado, String sectorActualId, String autorizadoAutorizadoNroDocumento);
//    List<Tramite> findByTipoTramiteIdAndSectorActualIdAndAutorizadoAutorizadoNroDocumento(Integer tipoTramiteId, String sectorActualId, String autorizadoAutorizadoNroDocumento);
//    
//    //4 params
    
    @Query("SELECT t FROM Tramite t WHERE "
    		+ "(:estado is null or t.estado = :estado) "
    		+ "and (:tipoTramiteId is null or t.tipoTramite.id = :tipoTramiteId)"
    		+ "and (:sectorActualId is null or t.sectorActual.id.sector = :sectorActualId)")
    		//+ "and (:autorizadoAutorizadoNroDocumento is null or t.autorizado.autorizado.nroDocumento = :autorizadoAutorizadoNroDocumento)")
    List<Tramite> findByEstadoAndTipoTramiteIdAndSectorActualId(
    			@Param("estado")EstadoTramite estado, 
    			@Param("tipoTramiteId")Integer tipoTramiteId, 
    			@Param("sectorActualId")String sectorActualId/*, 
    			@Param("autorizadoAutorizadoNroDocumento")String autorizadoAutorizadoNroDocumento*/);
    
}
