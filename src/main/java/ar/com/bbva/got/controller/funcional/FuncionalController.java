package ar.com.bbva.got.controller.funcional;

import java.text.ParseException;
import java.util.ArrayList;
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
import ar.com.bbva.got.model.Autorizado;
import ar.com.bbva.got.model.Comision;
import ar.com.bbva.got.model.Sector;
import ar.com.bbva.got.model.SectorKey;
import ar.com.bbva.got.model.TipoTramite;
import ar.com.bbva.got.response.dto.AutorizadoDTO;
import ar.com.bbva.got.response.dto.CampoDisponibleDTO;
import ar.com.bbva.got.response.dto.RequestAutorizadoDTO;
import ar.com.bbva.got.response.dto.TipoTramiteDTO;
import ar.com.bbva.got.response.mappers.AutorizadoMapper;
import ar.com.bbva.got.response.mappers.CampoDisponibleMapper;
import ar.com.bbva.got.response.mappers.RequestAutorizadoMapper;
import ar.com.bbva.got.response.mappers.TipoTramiteMapper;
import ar.com.bbva.got.service.funcional.AutorizadoService;
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
    private AutorizadoService autorizadoService;
    
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    
//    public void setTipoTramiteService(TipoTramiteService tipoTramiteService) {
//        this.tipoTramiteService = tipoTramiteService;
//    }

    @ApiOperation(value = "View a list of available tipoTramite", response = Iterable.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "tipoTramite/list", method = RequestMethod.GET, produces = "application/json")
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
    
    @ApiOperation(value = "View a list of available tipoTramite", response = Iterable.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = ""
            		+ "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "tipoTramite/{id}/camposDisponibles", method = RequestMethod.GET, produces = "application/json")
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
    
    @ApiOperation(value = "Add autorizado")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveAutorizado(@RequestBody RequestAutorizadoDTO autorizadoDTO) {
        try {
        	
        	
        	Autorizado autorizado = RequestAutorizadoMapper.DTOtoModel(autorizadoDTO);
        	autorizadoService.save(autorizado);
        	
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

}
