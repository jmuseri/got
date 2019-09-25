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

    
	
	
	
	
    @Query("SELECT t FROM Tramite t WHERE "
    		+ "(:nroClienteEmpresa is null or t.nroClienteEmpresa = :nroClienteEmpresa) "
    		+ " and (:estado is null or t.estado = :estado) "
    		+ " and (:tipoTramiteId is null or t.tipoTramite.id = :tipoTramiteId)"
    		+ " and (:sectorInicioId is null or t.sectorInicio.id.sector = :sectorInicioId)")
    List<Tramite> findByNroClienteEmpresaAndEstadoAndTipoTramiteIdAndSectorInicioId(
    		@Param("nroClienteEmpresa")Integer nroClienteEmpresa, 
    		@Param("estado")EstadoTramite estado, 
    		@Param("tipoTramiteId")Integer tipoTramiteId,
    		@Param("sectorInicioId")String sectorInicioId);
    
    @Query("SELECT t FROM Tramite t WHERE "
    		+ "(:cuitEmpresa is null or t.cuitEmpresa = :cuitEmpresa) "
    		+ " and (:estado is null or t.estado = :estado) "
    		+ " and (:tipoTramiteId is null or t.tipoTramite.id = :tipoTramiteId)"
    		+ " and (:sectorInicioId is null or t.sectorInicio.id.sector = :sectorInicioId)")
    List<Tramite> findByCuitEmpresaAndEstadoAndTipoTramiteIdAndSectorInicioId(
    		@Param("cuitEmpresa")String cuitEmpresa,
    		@Param("estado")EstadoTramite estado, 
    		@Param("tipoTramiteId")Integer tipoTramiteId,
    		@Param("sectorInicioId")String sectorInicioId);

    
    List<Tramite> findBySectorActual(Sector sectorActual);
    List<Tramite> findByEstado(EstadoTramite estado);


    @Query("SELECT t FROM Tramite t "
    		+ "join t.autorizado ta "
    		+ "join ta.autorizado aut "
    		+ "WHERE "
    		+ "(:estado is null or t.estado = :estado) "
    		+ "and (:tipoTramiteId is null or t.tipoTramite.id = :tipoTramiteId) "
    		+ "and (:sectorInicioId is null or t.sectorInicio.id.sector = :sectorInicioId) "
    		+ "and  (:autorizadoAutorizadoNroDocumento is null or aut.nroDocumento  = :autorizadoAutorizadoNroDocumento) ")
    List<Tramite> findByEstadoAndTipoTramiteIdAndSectorInicioId(
    			@Param("estado")EstadoTramite estado, 
    			@Param("tipoTramiteId")Integer tipoTramiteId, 
    			@Param("sectorInicioId")String sectorInicioId, 
    			@Param("autorizadoAutorizadoNroDocumento")String autorizadoAutorizadoNroDocumento);
    

    
    
    
}
