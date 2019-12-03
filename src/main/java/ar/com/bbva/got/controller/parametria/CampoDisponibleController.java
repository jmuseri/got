package ar.com.bbva.got.controller.parametria;

import java.text.ParseException;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ar.com.bbva.got.bean.StatusResponse;
import ar.com.bbva.got.dto.CampoDisponibleDTO;
import ar.com.bbva.got.mappers.CampoDisponibleMapper;
import ar.com.bbva.got.model.TipoTramite;
import ar.com.bbva.got.service.parametria.TipoTramiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/parametria/campoDisponible")
@Api(value = "parametria", description = "Parametria/CampoDisponible Operations in GOT")
public class CampoDisponibleController {

    private TipoTramiteService tipoTramiteService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setEstadoTramiteService(TipoTramiteService tipoTramiteService) {
        this.tipoTramiteService = tipoTramiteService;
    }

    @ApiOperation(value = "View a list of available camposDisponibles", response = Iterable.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = ""
            		+ "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "listByTipoTramite/{tipoTramiteId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> CampoDisponibleList(@PathVariable Integer tipoTramiteId) throws ParseException {
        
    	try {
            
    		TipoTramite tipoTramite = tipoTramiteService.getById(tipoTramiteId);
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
    

}
