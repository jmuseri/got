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
import ar.com.bbva.got.dto.AutorizadoDTO;
import ar.com.bbva.got.dto.CampoDetalleDTO;
import ar.com.bbva.got.dto.CampoDisponibleDTO;
import ar.com.bbva.got.dto.MotivoRechazoDTO;
import ar.com.bbva.got.dto.TipoTramiteDTO;
import ar.com.bbva.got.dto.TramiteDTO;
import ar.com.bbva.got.dto.TramiteDetalleDTO;
import ar.com.bbva.got.mappers.AutorizadoMapper;
import ar.com.bbva.got.mappers.CampoDisponibleMapper;
import ar.com.bbva.got.mappers.MotivoRechazoMapper;
import ar.com.bbva.got.mappers.TipoTramiteMapper;
import ar.com.bbva.got.mappers.TramiteDetalleMapper;
import ar.com.bbva.got.mappers.TramiteMapper;
import ar.com.bbva.got.model.Autorizado;
import ar.com.bbva.got.model.EstadoTramite;
import ar.com.bbva.got.model.MotivoRechazo;
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
import ar.com.bbva.got.service.funcional.MotivoRechazoService;
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
	
	
	@Autowired
	private MotivoRechazoService motivoRechazoService; 
	
    //Comentario commit lea!!!
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    

    @ApiOperation(value = "View a list of available tipoTramite", response = Iterable.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "tipoTramites", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listTipoTramites(HttpServletRequest req,
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
    public ResponseEntity<?> CampoDisponibleList(@PathVariable Integer id) throws ParseException {
        
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
    
    @ApiOperation(value = "View a list of available Autorizados", response = Iterable.class)
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
        		autorizado.setActivo(true);
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
    
    
    
    @ApiOperation(value = "Delete an autorizado")
    @RequestMapping(value = "/autorizado/{autorizadoId}/delete/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> deleteAutorizado(@PathVariable Integer autorizadoId) {
        try {
        	Autorizado autorizado = autorizadoService.getById(autorizadoId);
        	StatusResponse status =null;
        	if (null != autorizado) {
        		autorizado.setActivo(false);
        		autorizadoService.save(autorizado);
        		status = new StatusResponse("ok", "Autorizado deleted successfully", null);
        	}else {
        		status = new StatusResponse("error", "Autorizado not deleted", "Autorizado no encontrado.");
        	}
       	
            
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Autorizado not deleted", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }
    
    
    @ApiOperation(value = "Crear tramite")
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
        	tramite.setEstado(EstadoTramite.PENDIENTE_FIRMA);
        	
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
    
    @ApiOperation(value = "Gestionar tramite")
    @RequestMapping(value = "/tramites/{id}/gestionar", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> gestionarTramite(@PathVariable Integer id,
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
    
    @ApiOperation(value = "Finalizar tramite")
    @RequestMapping(value = "/tramites/{id}/finalizar", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> finalizarTramite(@PathVariable Integer id,
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
    
    @ApiOperation(value = "Rechazar tramite")
    @RequestMapping(value = "/tramites/{id}/rechazar", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> rechazarTramite(@PathVariable Integer id,
    										 @RequestParam(value = "motivoRechazoId", required = false) Integer motivoRechazoId,
    										  @RequestParam(value = "usuario", required = false) String usuario) {
        try {
        	       	
        	Tramite tramite = tramiteService.getById(id);
        	tramite.setEstado(EstadoTramite.RECHAZADO);

        	MotivoRechazo motivoRechazo= null; 
        	if (null!=motivoRechazoId) 
        		motivoRechazoService.getById(motivoRechazoId);

        	tramite.setMotivoRechazo(motivoRechazo);
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
    
    
    
    @ApiOperation(value = "Activar tramite")
    @RequestMapping(value = "/tramites/{id}/activar", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> activarTramite(@PathVariable Integer id,
    										  @RequestParam(value = "usuario", required = false) String usuario) {
        try {
        	       	
        	Tramite tramite = tramiteService.getById(id);
        	tramite.setEstado(EstadoTramite.ACTIVO);
        	tramite.setUsuModif(usuario);
        	tramite.setFechaModif(new Date());
        	tramiteService.save(tramite);
        	
            StatusResponse status = new StatusResponse("ok", "Tramite en estado Activo", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
            
            
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Tramite no actualizado", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }
    
    
    
    @ApiOperation(value = "Eliminar tramite")
    @RequestMapping(value = "/tramites/{id}/eliminar", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> deleteTramite(@PathVariable Integer id) {
        try {
            tramiteService.delete(id);
            StatusResponse status = new StatusResponse("ok", "Tramite deleted successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Tramite not deleted", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }
    
    
    
    
    
    
    @ApiOperation(value = "Mostrar detalle Tramite")
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
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "/tramites/listByCuit/{cuit}", method = RequestMethod.GET, produces = "application/json")
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
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "/tramites/buscar", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listarTramites(HttpServletRequest req,
    		@RequestParam(value = "estadoTramite", required = false) String estado,
    		@RequestParam(value = "idTipoTramite", required = false) Integer idTipoTramite,
    		@RequestParam(value = "sector", required = false) String idSector,
    		@RequestParam(value = "DniAutorizado", required = false) String DniAutorizado) throws ParseException {
        
    	try {
            
        	
        	List<TramiteDTO> responseList = new ArrayList<TramiteDTO>();
        	
        	Iterable<Tramite> tramiteList = tramiteService.buscarTramites(estado, idTipoTramite, idSector, DniAutorizado);
        	        	
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
    
    
    
    @ApiOperation(value = "View a list of available MotivoRechazo", response = Iterable.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "motivosRechazo", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listMotivosRechazo(HttpServletRequest req,
            @RequestParam(value = "tipoTramiteId", required = true) Integer tipoTramiteId) throws ParseException {
        
    	try {
    		
    		
    		
        	List<MotivoRechazoDTO> responseList = new ArrayList<MotivoRechazoDTO>();
        	
        	Iterable<MotivoRechazo> motivoRechazoList = motivoRechazoService.listByTipoTramiteId(tipoTramiteId);
        	
        	for (MotivoRechazo motRechazo : motivoRechazoList) {
        		MotivoRechazoDTO response = MotivoRechazoMapper.modelToDTO(motRechazo);
        		
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
    @RequestMapping(value = "/tramites/detalle/add", method = RequestMethod.POST, produces = "application/json")
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
    @RequestMapping(value = "/tramites/detalle/update/{tramiteId}/{tipoTramiteCampoId}/{campoDisponibleId}", method = RequestMethod.POST, produces = "application/json")
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
    @RequestMapping(value = "/tramites/detalle/delete/{tramiteId}/{tipoTramiteCampoId}/{campoDisponibleId}", method = RequestMethod.POST, produces = "application/json")
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
    
}
