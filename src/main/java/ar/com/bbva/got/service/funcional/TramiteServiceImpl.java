package ar.com.bbva.got.service.funcional;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.bbva.got.model.EstadoTramite;
import ar.com.bbva.got.model.Sector;
import ar.com.bbva.got.model.Tramite;
import ar.com.bbva.got.repository.TramiteRepository;

@Service
public class TramiteServiceImpl implements TramiteService {
	private static final String USUARIO_FEMP="FNC";

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private TramiteRepository tramiteRepository;
   
    @Autowired
    public void setRepository(TramiteRepository tramiteRepository) {
        this.tramiteRepository = tramiteRepository;
    }

    @Override
    public Iterable<Tramite> listAll() {
        logger.debug("listAll called");
        return this.tramiteRepository.findAll();
    }

    @Override
    public List<Tramite> listBySectorActual(Sector sectorActual) {
        logger.debug("listBySector called");
        return this.tramiteRepository.findBySectorActual(sectorActual);
    }

    @Override
    public List<Tramite> listByEstado(EstadoTramite estado) {
        logger.debug("listByEstado called");
        return this.tramiteRepository.findByEstado(estado);
        
    }
    
	@Override
	public List<Tramite> listByEmpresaEstadoAndTipoTramiteAndSectorInicio(Integer nroClienteEmpresa, String estado,
			Integer idTipoTramite, String sectorInicioId) {
		logger.debug("listByCuitEstadoAndTipoTramite called");
		EstadoTramite estadoTramite = estado==null?null:EstadoTramite.valueOf(estado);
		return this.tramiteRepository.findByNroClienteEmpresaAndEstadoAndTipoTramiteIdAndSectorInicioId(
				nroClienteEmpresa, estadoTramite, idTipoTramite, sectorInicioId);
	}

	@Override
	public List<Tramite> listByCuitEmpresaEstadoAndTipoTramiteAndSectorInicio(String cuitEmpresa, String estado,
			Integer idTipoTramite, String sectorInicioId) {
		logger.debug("listByCuitEstadoAndTipoTramite called");
		EstadoTramite estadoTramite = estado==null?null:EstadoTramite.valueOf(estado);
		return this.tramiteRepository.findByCuitEmpresaAndEstadoAndTipoTramiteIdAndSectorInicioId(cuitEmpresa,
				estadoTramite, idTipoTramite, sectorInicioId);
	}
    
    

    @Override
    public Tramite getById(Integer id) {
        logger.debug("getById called");
        return this.tramiteRepository.findById(id).orElse(null);
    }

    @Override
    public Tramite save(Tramite tramite) {
        logger.debug("save called");
        return this.tramiteRepository.save(tramite);
    }

    @Override
    public void save(List<Tramite> tramite) {
        logger.debug("save called");
        tramiteRepository.saveAll(tramite);
    }

    @Override
    public void delete(Integer id) {
        logger.debug("delete called");
        this.tramiteRepository.deleteById(id);
    }

	@Override
	public List<Tramite> buscarTramites(String cuit, String estado, Integer idTipoTramite, String idSector, String DniAutorizado, String tipoDocAutorizado) {
        EstadoTramite estadoTramite = estado==null?null:EstadoTramite.valueOf(estado);
        
		return this.tramiteRepository.findByCuitAndEstadoAndTipoTramiteIdAndSectorInicioId(cuit, estadoTramite,
				idTipoTramite, idSector,DniAutorizado, tipoDocAutorizado);

	}
	
	
	
	/**
	 * Al ser utilizado para AACC - Areas Centrales - este metodo va a traer solo los estados visibles par areas centrales.
	 */
	@Override
	public List<Tramite> buscarTramites(String usuario, String cuit, String estado, Integer idTipoTramite, String idSector, String DniAutorizado, String tipoDocAutorizado) {
		
		return this.buscarTramites(cuit, estado, idTipoTramite, idSector, DniAutorizado, tipoDocAutorizado).stream()
				.filter(tramite -> (usuario.equals(tramite.getUsuModif()) || usuario.equals(USUARIO_FEMP) || null == tramite.getUsuModif()))
				.filter(tramite -> (EstadoTramite.ACTIVO.equals(tramite.getEstado())
						|| EstadoTramite.FINALIZADO.equals(tramite.getEstado())
						|| EstadoTramite.GESTION.equals(tramite.getEstado())))
				.collect(Collectors.toList());

	}
	
	

}
