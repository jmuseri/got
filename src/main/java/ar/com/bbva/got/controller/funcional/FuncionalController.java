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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.bbva.got.bean.StatusResponse;
import ar.com.bbva.got.dto.AltaTramiteDTO;
import ar.com.bbva.got.dto.AutorizadoDTO;
import ar.com.bbva.got.dto.CampoDetalleDTO;
import ar.com.bbva.got.dto.CampoDisponibleDTO;
import ar.com.bbva.got.dto.TipoTramiteDTO;
import ar.com.bbva.got.dto.TramiteDTO;
import ar.com.bbva.got.mappers.AutorizadoMapper;
import ar.com.bbva.got.mappers.CampoDisponibleMapper;
import ar.com.bbva.got.mappers.TipoTramiteMapper;
import ar.com.bbva.got.mappers.TramiteMapper;
import ar.com.bbva.got.model.Autorizado;
import ar.com.bbva.got.model.EstadoTramite;
import ar.com.bbva.got.model.Sector;
import ar.com.bbva.got.model.SectorKey;
import ar.com.bbva.got.model.TipoTramite;
import ar.com.bbva.got.model.TipoTramiteCampo;
import ar.com.bbva.got.model.Tramite;
import ar.com.bbva.got.model.TramiteAutorizado;
import ar.com.bbva.got.model.TramiteAutorizadoKey;
import ar.com.bbva.got.model.TramiteDetalle;
import ar.com.bbva.got.model.TramiteDetalleKey;
import ar.com.bbva.got.service.funcional.AutorizadoService;
import ar.com.bbva.got.service.funcional.TramiteAutorizadoService;
import ar.com.bbva.got.service.funcional.TramiteDetalleService;
import ar.com.bbva.got.service.funcional.TramiteService;
import ar.com.bbva.got.service.parametria.SectorService;
import ar.com.bbva.got.service.parametria.TipoTramiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/funcional")
@Api(value = "funcional", description = "funcional Operations in GOT")
public class FuncionalController {

	@Autowired
    private TipoTramiteService tipoTramiteService;
    
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
	
    //Comentario commit lea!!!
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    
//    public void setTipoTramiteService(TipoTramiteService tipoTramiteService) {
//        this.tipoTramiteService = tipoTramiteService;
//    }

    @ApiOperation(value = "View a list of available tipoTramite", response = Iterable.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "tipoTramites", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> list(HttpServletRequest req,
            @RequestParam(value = "activo", required = false) boolean activo,
            @RequestParam(value = "canal", required = false) String idCanal,
            @RequestParam(value = "sector", required = false) String idSector) throws ParseException {
        
    	try {
            
    		
    		SectorKey id = new SectorKey();
        	id.setCanal(idCanal);
        	id.setSector(idSector);
        	
        	Sector sector = new Sector();
        	sector.setId(id);
        	
        	List<TipoTramiteDTO> responseList = new ArrayList<TipoTramiteDTO>();
        	
        	Iterable<TipoTramite> tipoTramiteList = tipoTramiteService.listByActiveAndSector(activo, sector);
        	
        	for (TipoTramite tipoTramite : tipoTramiteList) {
        		TipoTramiteDTO response = TipoTramiteMapper.modelToDTO(tipoTramite);
        		
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
    
    @ApiOperation(value = "View a list of available camposDisponibles", response = Iterable.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = ""
            		+ "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "tipoTramites/{id}/camposDisponibles", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listCampoDisponible(@PathVariable Integer id) throws ParseException {
        
    	try {
            
    		TipoTramite tipoTramite = tipoTramiteService.getById(id);
        	Set<CampoDisponibleDTO> responseDto = CampoDisponibleMapper.modelToDTO(tipoTramite.getCampos()); 
    		
            ResponseEntity<?> response = new ResponseEntity<>(responseDto, HttpStatus.OK);
            return response;
        	
        	
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }
    
    @ApiOperation(value = "View a list of available tipoTramite", response = Iterable.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = ""
            		+ "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "autorizados", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listAutorizados(HttpServletRequest req,
            @RequestParam(value = "nroClienteEmpresa", required = false) Integer nroClienteEmpresa,
            @RequestParam(value = "cuitEmpresa", required = false) String cuitEmpresa) throws ParseException {
        
    	try {
    		
    		List<AutorizadoDTO> responseList = new ArrayList<AutorizadoDTO>();
            
    		Iterable<Autorizado> listAutorizados = autorizadoService.listByNroClienteEmpresaOrCuitEmpresa(nroClienteEmpresa, cuitEmpresa);
    		
    		for (Autorizado autorizado : listAutorizados) {
    			AutorizadoDTO response = AutorizadoMapper.modelToDTO(autorizado);
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
    
    @ApiOperation(value = "Add autorizados")
    @RequestMapping(value = "/autorizados/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> addAutorizados(@RequestBody List<AutorizadoDTO> listAutorizadoDTO,
    										@RequestParam(value = "usuario", required = false) String usuario) {
        try {
        	
        	for (AutorizadoDTO autorizadoDTO : listAutorizadoDTO) {
        		Autorizado autorizado = AutorizadoMapper.DTOtoModel(autorizadoDTO);
        		autorizado.setUsuAlta(usuario);
        		autorizado.setUsuModif(usuario);
        		autorizado.setFechaAlta(new Date());
        		autorizado.setFechaModif(new Date());
            	autorizadoService.save(autorizado);
			}
        	
            StatusResponse status = new StatusResponse("ok", "Alta de autorizado realizada", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
            
            
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Autorizado no insertado", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }
    
    @ApiOperation(value = "Add tramite")
    @RequestMapping(value = "/tramites/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> addTramite(@RequestBody AltaTramiteDTO altaTramiteDTO) {
        try {
        	
        	
        	Tramite tramite = new Tramite();
        	
        	tramite.setNroClienteEmpresa(altaTramiteDTO.getNroClienteEmpresa());
        	tramite.setCuitEmpresa(altaTramiteDTO.getCuitEmpresa());
        	tramite.setCuentaCobro(altaTramiteDTO.getCuentaCobro());
        	
        	TipoTramite tipoTramite = tipoTramiteService.getById(altaTramiteDTO.getIdTipoTramite());
        	
        	if (tipoTramite == null) {
        		logger.error("Tipo de tramite no encontrado");
                StatusResponse statusResponse = new StatusResponse("error", "Tramite not saved", "Tipo de tramite no encontrado");
                ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
                return response;
        	}
        	tramite.setTipoTramite(tipoTramite);
        	tramite.setEstado(EstadoTramite.ACTIVO);
        	
            Sector sectorAlta = sectorService.getById(altaTramiteDTO.getSectorAlta());
            
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
            
            
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Tramite no insertado", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    private static TipoTramiteCampo getTipoTramiteCampoByName (String nombreCampo, Set<TipoTramiteCampo> campos) {
		
		for (TipoTramiteCampo tipoTramiteCampo : campos) {
			if (tipoTramiteCampo.getNombre().equals(nombreCampo)) {
				return tipoTramiteCampo;
			}
		}
		return null;
	} 
    
    @ApiOperation(value = "Gestionar tramites")
    @RequestMapping(value = "/tramites/{id}/gestionar", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> gestionarTramites(@PathVariable Integer id,
    										   @RequestParam(value = "usuario", required = false) String usuario) {
        try {
        	       	
        	Tramite tramite = tramiteService.getById(id);
        	tramite.setEstado(EstadoTramite.GESTION);
        	tramite.setUsuModif(usuario);
        	tramite.setFechaModif(new Date());
        	tramiteService.save(tramite);
        	
          	
            StatusResponse status = new StatusResponse("ok", "Tramite en estado Gestionar", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
            
            
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Tramite no actualizado", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }
    
    @ApiOperation(value = "Finalizar tramites")
    @RequestMapping(value = "/tramites/{id}/finalizar", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> finalizarTramites(@PathVariable Integer id,
    										   @RequestParam(value = "usuario", required = false) String usuario) {
        try {
        	       	
        	Tramite tramite = tramiteService.getById(id);
        	tramite.setEstado(EstadoTramite.FINALIZADO);
        	tramite.setUsuModif(usuario);
        	tramite.setFechaModif(new Date());
        	tramiteService.save(tramite);
        	
          	
            StatusResponse status = new StatusResponse("ok", "Tramite en estado Finalizar", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
            
            
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Tramite no actualizado", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }
    
    @ApiOperation(value = "Rechazar tramites")
    @RequestMapping(value = "/tramites/{id}/rechazar", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> rechazarTramites(@PathVariable Integer id,
    										  @RequestParam(value = "usuario", required = false) String usuario) {
        try {
        	       	
        	Tramite tramite = tramiteService.getById(id);
        	tramite.setEstado(EstadoTramite.RECHAZADO);
        	tramite.setUsuModif(usuario);
        	tramite.setFechaModif(new Date());
        	tramiteService.save(tramite);
        	
            StatusResponse status = new StatusResponse("ok", "Tramite en estado Rechazar", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
            
            
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Tramite no actualizado", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }
    
    @ApiOperation(value = "show tramite detail")
    @RequestMapping(value = "/tramites/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getTramite(@PathVariable Integer id) {
        
    	try {
        	       	
        	Tramite tramite = tramiteService.getById(id);
        	TramiteDTO dto = TramiteMapper.modelToDTO(tramite);
        	
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
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "/tramites/list/{nroClienteEmpresa}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> list(HttpServletRequest req,
    		@PathVariable Integer nroClienteEmpresa,
    		@RequestParam(value = "estadoTramite", required = false) String estado,
    		@RequestParam(value = "idTipoTramite", required = false) Integer idTipoTramite) throws ParseException {
        
    	try {
            
        	
        	List<TramiteDTO> responseList = new ArrayList<TramiteDTO>();
        	
        	//Iterable<Tramite> tramiteList = tramiteService.listByEmpresaEstadoAndTipoTramite(nroClienteEmpresa, estado, tipoTramite);
        	Iterable<Tramite> tramiteList = tramiteService.listAll();
        	
        	for (Tramite tramite : tramiteList) {
        		TramiteDTO response = TramiteMapper.modelToDTO(tramite);
        		
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
    
    
}
