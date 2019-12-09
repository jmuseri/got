package ar.com.bbva.got.controller.funcional;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.bbva.got.bean.StatusResponse;
import ar.com.bbva.got.dto.AltaTramiteDTO;
import ar.com.bbva.got.dto.CampoDetalleDTO;
import ar.com.bbva.got.dto.TramiteDTO;
import ar.com.bbva.got.dto.TramiteDetalleDTO;
import ar.com.bbva.got.mappers.TramiteDetalleMapper;
import ar.com.bbva.got.mappers.TramiteMapper;
import ar.com.bbva.got.model.Autorizado;
import ar.com.bbva.got.model.EstadoTramite;
import ar.com.bbva.got.model.Sector;
import ar.com.bbva.got.model.TipoTramite;
import ar.com.bbva.got.model.TipoTramiteCampo;
import ar.com.bbva.got.model.TipoTramiteComision;
import ar.com.bbva.got.model.TipoTramiteComisionKey;
import ar.com.bbva.got.model.Tramite;
import ar.com.bbva.got.model.TramiteAutorizado;
import ar.com.bbva.got.model.TramiteAutorizadoKey;
import ar.com.bbva.got.model.TramiteDetalle;
import ar.com.bbva.got.model.TramiteDetalleKey;
import ar.com.bbva.got.service.funcional.AutorizadoService;
import ar.com.bbva.got.service.funcional.TipoTramiteComisionService;
import ar.com.bbva.got.service.funcional.TramiteAutorizadoService;
import ar.com.bbva.got.service.funcional.TramiteDetalleService;
import ar.com.bbva.got.service.funcional.TramiteService;
import ar.com.bbva.got.service.parametria.SectorService;
import ar.com.bbva.got.service.parametria.TipoTramiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/funcional/tramite/")
@Api(value = "funcional", description = "funcional Operations in GOT")
public class TramiteController {

	@Autowired
    private TipoTramiteService tipoTramiteService;
    
	@Autowired
    private TipoTramiteComisionService tipoTramiteComisionService;

	
	@Autowired
    private TramiteService tramiteService;
	
	@Autowired
    private AutorizadoService autorizadoService;
	
	@Autowired
    private SectorService sectorService;
	
	@Autowired
    private TramiteAutorizadoService tramiteAutorizadoService;
	
	@Autowired
	private TramiteDetalleService tramiteDetalleService; 
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    


    
    @ApiOperation(value = "Crear tramite")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> addTramite(@RequestBody AltaTramiteDTO altaTramiteDTO) {

    	Tramite tramite = new Tramite();
    	
    	tramite.setNroClienteEmpresa(altaTramiteDTO.getNroClienteEmpresa());
    	tramite.setCuitEmpresa(altaTramiteDTO.getCuitEmpresa());
    	tramite.setCuentaCobro(altaTramiteDTO.getCuentaCobro());
    	tramite.setAreaNegocio(altaTramiteDTO.getAreaNegocio());
    	
    	TipoTramite tipoTramite = tipoTramiteService.getById(altaTramiteDTO.getIdTipoTramite());
    	
    	if (tipoTramite == null) {
    		logger.error("Tipo de tramite no encontrado");
            StatusResponse statusResponse = new StatusResponse("error", "Tramite not saved", "Tipo de tramite no encontrado");
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
    	}
    	tramite.setTipoTramite(tipoTramite);
    	tramite.setEstado(EstadoTramite.PENDIENTE_FIRMA);
    	
    	
        Sector sectorAlta = sectorService.getById(altaTramiteDTO.getSectorAlta().getSector(),altaTramiteDTO.getSectorAlta().getCanal());
        
        if (sectorAlta == null) {
    		logger.error("Sector no encontrado");
            StatusResponse statusResponse = new StatusResponse("error", "Tramite not saved", "Sector no encontrado");
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
    	} 
    	tramite.setSectorActual(sectorAlta);
    	tramite.setSectorInicio(sectorAlta);
    	
    	List<TramiteAutorizado> listaAutorizados = new ArrayList<TramiteAutorizado>();
    	
    	for (Integer idAutorizado : altaTramiteDTO.getIdAutorizados()) {
    		
    		TramiteAutorizado tramiteAutorizado = new TramiteAutorizado();
    		Autorizado autorizado = autorizadoService.getById(idAutorizado);
    		
    		if (autorizado == null) {
        		logger.error("Autorizado no encontrado");
                StatusResponse statusResponse = new StatusResponse("error", "Tramite not saved", "Autorizado no encontrado");
                ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
                return response;
        	}
    		
    		if (!autorizado.getNroClienteEmpresa().equals(altaTramiteDTO.getNroClienteEmpresa())) {
    			logger.error("Autorizado no corresponde a la empresa");
                StatusResponse statusResponse = new StatusResponse("error", "Tramite not saved", "Autorizado no corresponde a la empresa");
                ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
                return response;
    		}
    		
    		TramiteAutorizadoKey tramiteAutorizadoKey = new TramiteAutorizadoKey();
    		tramiteAutorizadoKey.setAutorizadoId(idAutorizado);
    		tramiteAutorizadoKey.setTramiteId(0);
    		
    		tramiteAutorizado.setId(tramiteAutorizadoKey);
    		tramiteAutorizado.setAutorizado(autorizado);
    		tramiteAutorizado.setFechaAlta(new Date());
    		tramiteAutorizado.setUsuAlta(altaTramiteDTO.getUsuarioAlta());
    		tramiteAutorizado.setFinalizoTramite(false);
    		
    		
    		listaAutorizados.add(tramiteAutorizado);
		}
    	
    	tramite.setUsuAlta(altaTramiteDTO.getUsuarioAlta());
    	tramite.setUsuModif(altaTramiteDTO.getUsuarioAlta());
    	tramite.setFechaAlta(new Date());
    	tramite.setFechaInicio(new Date());
    	tramite.setFechaModif(new Date());
    	
    	tramite = tramiteService.save(tramite);
    	
    	for (TramiteAutorizado tramiteAutorizado : listaAutorizados) {
    		tramiteAutorizado.getId().setTramiteId(tramite.getId());
		}
    	
    	tramiteAutorizadoService.save(listaAutorizados);
    	
    	List<TramiteDetalle> listaDetalles = new ArrayList<TramiteDetalle>();
    	for (CampoDetalleDTO campoDetalleDTO : altaTramiteDTO.getDetalle()) {
    		TramiteDetalle tramiteDetalle = new TramiteDetalle();
    		
    		TipoTramiteCampo tipoTramiteCampo = getTipoTramiteCampoByName(campoDetalleDTO.getNombre(), tipoTramite.getCampos());
    		
    		if (tipoTramiteCampo == null) {
    			logger.error("Campo ingresado no corresponde al tipo de tramite");
                StatusResponse statusResponse = new StatusResponse("error", "Tramite not saved", "Campo ingresado no corresponde al tipo de tramite");
                ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
                return response;
    		}
    		
    		TramiteDetalleKey tramiteDetalleKey = new TramiteDetalleKey();
    		tramiteDetalleKey.setTramiteId(tramite.getId());
    		tramiteDetalleKey.setTipoTramiteCampoId(tipoTramiteCampo.getId());
    		
    		tramiteDetalle.setId(tramiteDetalleKey);
    		tramiteDetalle.setFechaAlta(new Date());
    		tramiteDetalle.setFechaModif(new Date());
    		tramiteDetalle.setUsuAlta(altaTramiteDTO.getUsuarioAlta());
    		tramiteDetalle.setUsuModif(altaTramiteDTO.getUsuarioAlta());
    		tramiteDetalle.setValor(campoDetalleDTO.getValor());
    		
    		listaDetalles.add(tramiteDetalle);
		}
    	
    	tramiteDetalleService.save(listaDetalles);
    	
      	
        StatusResponse status = new StatusResponse("ok", "Alta de Tramite realizada", tramite.getId().toString());
        ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
        return response;
    }

    private TipoTramiteCampo getTipoTramiteCampoByName (String nombreCampo, Set<TipoTramiteCampo> campos) {
		
		for (TipoTramiteCampo tipoTramiteCampo : campos) {
			if (tipoTramiteCampo.getNombre().equals(nombreCampo)) {
				return tipoTramiteCampo;
			}
		}
		return null;
	} 
    
    
    
    @ApiOperation(value = "Mostrar detalle Tramite")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getTramite(@PathVariable Integer id) {
        
    	try {
        	       	
        	Tramite tramite = tramiteService.getById(id);
        	TramiteDTO dto = TramiteMapper.modelToDTO(tramite);
        	dto.setCodigoComision(this.getCodigoComision(tramite.getTipoTramite().getId(), tramite.getAreaNegocio()));
        	
        	
        	
        	ResponseEntity<?> response = new ResponseEntity<>(dto, HttpStatus.OK);
            return response;
            
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Tramite no actualizado", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }
    
    
 
    @ApiOperation(value = "show tramites list for the given nroClienteEmpresa")
    @RequestMapping(value = "/list/{nroClienteEmpresa}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listTramites(HttpServletRequest req,
    		@PathVariable Integer nroClienteEmpresa,
    		@RequestParam(value = "estadoTramite", required = false) String estado,
    		@RequestParam(value = "idTipoTramite", required = false) Integer idTipoTramite,
    		@RequestParam(value = "sectorInicioId", required = false) String sectorInicioId
    		) throws ParseException {
    	try {
            
        	
        	List<TramiteDTO> responseList = new ArrayList<TramiteDTO>();
        	
        	Iterable<Tramite> tramiteList = tramiteService.listByEmpresaEstadoAndTipoTramiteAndSectorInicio(nroClienteEmpresa, estado, idTipoTramite, sectorInicioId);
        	        	
        	for (Tramite tramite : tramiteList) {
        		TramiteDTO response = TramiteMapper.modelToDTO(tramite);
        		response.setCodigoComision(this.getCodigoComision(tramite.getTipoTramite().getId(), tramite.getAreaNegocio()));
        		responseList.add(response);
			}
        	
            ResponseEntity<?> response = new ResponseEntity<>(responseList, HttpStatus.OK);
            return response;
        	
        	
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }
    
    
    
    
    
    @ApiOperation(value = "show tramites list for the given cuit")
    @RequestMapping(value = "/listByCuit/{cuit}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listTramitesByCUIT(HttpServletRequest req,
    		@PathVariable String cuit,
    		@RequestParam(value = "estadoTramite", required = false) String estado,
    		@RequestParam(value = "idTipoTramite", required = false) Integer idTipoTramite,
    		@RequestParam(value = "sectorInicioId", required = false) String sectorInicioId) throws ParseException {
        
    	try {
            
        	
        	List<TramiteDTO> responseList = new ArrayList<TramiteDTO>();
        	
        	Iterable<Tramite> tramiteList = tramiteService.listByCuitEmpresaEstadoAndTipoTramiteAndSectorInicio(cuit, estado, idTipoTramite,sectorInicioId);
        	        	
        	for (Tramite tramite : tramiteList) {
        		TramiteDTO response = TramiteMapper.modelToDTO(tramite);
        		response.setCodigoComision(this.getCodigoComision(tramite.getTipoTramite().getId(), tramite.getAreaNegocio()));
        		responseList.add(response);
			}
        	
            ResponseEntity<?> response = new ResponseEntity<>(responseList, HttpStatus.OK);
            return response;
        	
        	
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }
    
    
    
    /*
     * 
     * 
     * Busqueda de tramites por tipo y nro. documento del autorizado (solo estos dos que sean requeridos) mas 
cuit, tipo y doc empresa, estado y tipo de tramite. 
     */
    
    
    
    @ApiOperation(value = "show tramites list for the given params")
    @RequestMapping(value = "/listByAutorizado/{tipoDocumento}/{numeroDocumento}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listarTramitesByAutorizado(HttpServletRequest req,
    		@PathVariable String numeroDocumento,
    		@PathVariable String tipoDocumento,
    		@RequestParam(value = "cuit", required = false) String cuit,
    		@RequestParam(value = "estadoTramite", required = false) String estado,
    		@RequestParam(value = "idTipoTramite", required = false) Integer idTipoTramite,
    		@RequestParam(value = "sector", required = false) String idSector
    		) throws ParseException {
        
    	try {
            
        	
        	List<TramiteDTO> responseList = new ArrayList<TramiteDTO>();
        	
        	Iterable<Tramite> tramiteList = tramiteService.buscarTramites(cuit, estado, idTipoTramite, idSector, numeroDocumento, tipoDocumento);
        	        	
        	for (Tramite tramite : tramiteList) {
        		TramiteDTO response = TramiteMapper.modelToDTO(tramite);
        		response.setCodigoComision(this.getCodigoComision(tramite.getTipoTramite().getId(), tramite.getAreaNegocio()));
        		responseList.add(response);
			}
        	
            ResponseEntity<?> response = new ResponseEntity<>(responseList, HttpStatus.OK);
            return response;
        	
        	
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }
    
    
    
    
    
    @ApiOperation(value = "show tramites list for the given params")
    @RequestMapping(value = "/listByUsuario/{usuario}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listarTramitesByUsuario(HttpServletRequest req,
    		@PathVariable String usuario,
    		@RequestParam(value = "numeroDocumentoAutorizado", required = false) String numeroDocumento,
    		@RequestParam(value = "tipoDocumentoAutorizado", required = false) String tipoDocumento,
    		@RequestParam(value = "cuit", required = false) String cuit,
    		@RequestParam(value = "estadoTramite", required = false) String estado,
    		@RequestParam(value = "idTipoTramite", required = false) Integer idTipoTramite,
    		@RequestParam(value = "sector", required = false) String idSector
    		) throws ParseException {
        
    	try {
            
        	
        	List<TramiteDTO> responseList = new ArrayList<TramiteDTO>();
        	
        	Iterable<Tramite> tramiteList = tramiteService.buscarTramites(usuario, cuit, estado, idTipoTramite, idSector, numeroDocumento, tipoDocumento);
        	        	
        	for (Tramite tramite : tramiteList) {
        		TramiteDTO response = TramiteMapper.modelToDTO(tramite);
        		response.setCodigoComision(this.getCodigoComision(tramite.getTipoTramite().getId(), tramite.getAreaNegocio()));
        		responseList.add(response);
			}
        	
            ResponseEntity<?> response = new ResponseEntity<>(responseList, HttpStatus.OK);
            return response;
        	
        	
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }
    
    
    
    
    @ApiOperation(value = "Add a tramiteDetalle")
    @RequestMapping(value = "/detalle/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> addTramiteDetalle(@RequestBody TramiteDetalleDTO tramiteDetalleDTO) {
        try {
        	
        	
        	TramiteDetalle tramiteDetalle = TramiteDetalleMapper.DTOtoModel(tramiteDetalleDTO);
        	
            tramiteDetalleService.save(tramiteDetalle);
            StatusResponse status = new StatusResponse("ok", "TramiteDetalle saved successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "TramiteDetalle not saved", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Update a tramiteDetalle")
    @RequestMapping(value = "/detalle/update/{tramiteId}/{tipoTramiteCampoId}/{campoDisponibleId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> updateTramiteDetalle(@PathVariable Integer tramiteId,
            @PathVariable Integer tipoTramiteCampoId, @PathVariable String campoDisponibleId,
            @RequestBody TramiteDetalleDTO tramiteDetalle) {
        try {
            TramiteDetalle stored = this.tramiteDetalleService
                    .getById(new TramiteDetalleKey(tramiteId, tipoTramiteCampoId, campoDisponibleId));
            if (null != tramiteDetalle.getValor()) {
                stored.setValor(tramiteDetalle.getValor());
            }
            if (null != tramiteDetalle.getUsuModif()) {
                stored.setUsuModif(tramiteDetalle.getUsuModif());
            } else {
                stored.setUsuModif("system");
            }
            if (null != tramiteDetalle.getFechaModif()) {
                stored.setFechaModif(tramiteDetalle.getFechaModif());
            } else {
                stored.setFechaModif(new Date());
            }
            tramiteDetalleService.save(stored);
            StatusResponse status = new StatusResponse("ok", "TramiteDetalle updated successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "TramiteDetalle not saved", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

 

    @ApiOperation(value = "Delete a tramiteDetalle")
    @RequestMapping(value = "/detalle/delete/{tramiteId}/{tipoTramiteCampoId}/{campoDisponibleId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> deleteTramiteDetalle(@PathVariable Integer tramiteId,
            @PathVariable Integer tipoTramiteCampoId, @PathVariable String campoDisponibleId, Model model) {
        try {
            tramiteDetalleService.delete(new TramiteDetalleKey(tramiteId, tipoTramiteCampoId, campoDisponibleId));
            StatusResponse status = new StatusResponse("ok", "TramiteDetalle deleted successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "TramiteDetalle not deleted", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }
    
    

    
   //TODO sacar y poner esta logica en el mapper.
    private String getCodigoComision (Integer idTipoTramite, Integer areaNegocio){
    	TipoTramiteComisionKey tipoTramiteComisionid= new TipoTramiteComisionKey(idTipoTramite, areaNegocio);
    	TipoTramiteComision comision = tipoTramiteComisionService.getById(tipoTramiteComisionid);
    	return comision!=null? comision.getCodigoComision() : null;
    	
    }
    
    

}
