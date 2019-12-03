package ar.com.bbva.got.controller.parametria;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.bbva.got.bean.StatusResponse;
import ar.com.bbva.got.dto.MotivoRechazoDTO;
import ar.com.bbva.got.mappers.MotivoRechazoMapper;
import ar.com.bbva.got.model.MotivoRechazo;
import ar.com.bbva.got.service.funcional.MotivoRechazoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/parametria/motivoRechazo")
@Api(value = "parametria", description = "Parametria/Comision Operations in GOT")
public class MotivoRechazoController {

    private MotivoRechazoService motivoRechazoService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setMotivoRechazoService(MotivoRechazoService motivoRechazoService) {
        this.motivoRechazoService = motivoRechazoService;
    }



    @ApiOperation(value = "View a list of available MotivoRechazo", response = Iterable.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
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
  
    
    
}
