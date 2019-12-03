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
import ar.com.bbva.got.dto.TipoTramiteDTO;
import ar.com.bbva.got.mappers.TipoTramiteMapper;
import ar.com.bbva.got.model.Sector;
import ar.com.bbva.got.model.SectorKey;
import ar.com.bbva.got.model.TipoTramite;
import ar.com.bbva.got.service.parametria.TipoTramiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/parametria/tipoTramite")
@Api(value = "parametria", description = "Parametria/TipoTramite Operations in GOT")
public class TipoTramiteController {

    private TipoTramiteService tipoTramiteService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setTipoTramiteService(TipoTramiteService tipoTramiteService) {
        this.tipoTramiteService = tipoTramiteService;
    }



    @ApiOperation(value = "View a list of available tipoTramite", response = Iterable.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
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
    
}
