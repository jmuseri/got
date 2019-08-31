package ar.com.bbva.got.controller.parametria;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.bbva.got.bean.StatusResponse;
import ar.com.bbva.got.model.Sector;
import ar.com.bbva.got.model.SectorKey;
import ar.com.bbva.got.service.parametria.SectorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/parametria/sector")
@Api(value = "parametria", description = "Parametria/Sector Operations in GOT")
public class SectorController {

    private SectorService sectorService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setSectorService(SectorService sectorService) {
        this.sectorService = sectorService;
    }

    @ApiOperation(value = "View a list of available sectors", response = Iterable.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")

    public ResponseEntity<?> list(HttpServletRequest req, 
    							  @RequestParam(value = "activo", required = true) boolean activo) throws ParseException {
        try {
            if (!activo) {
                Iterable<Sector> sectorList = sectorService.listAll();
                ResponseEntity<?> response = new ResponseEntity<>(sectorList, HttpStatus.OK);
                return response;
            } else {
                Iterable<Sector> sectorList = sectorService.listActive();
                ResponseEntity<?> response = new ResponseEntity<>(sectorList, HttpStatus.OK);
                return response;
            }
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Search a sector with an ID", response = Sector.class)
    @RequestMapping(value = "/show", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> showSector(HttpServletRequest req, 
			  							@RequestParam(value = "canal", required = true) String idCanal,
			  							@RequestParam(value = "sector", required = true) String idSector) {
        try {
            
        	SectorKey id = new SectorKey();
        	id.setCanal(idCanal);
        	id.setSector(idSector);
        	
        	Sector sector = sectorService.getById(id);
            ResponseEntity<?> response = new ResponseEntity<>(sector, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Add a sector")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveSector(@RequestBody Sector sector) {
        try {
        	
        	SectorKey id = new SectorKey();
        	id.setCanal(sector.getId().getCanal());
        	id.setSector(sector.getId().getSector());
        	
            sector.setId(id);
            sectorService.save(sector);
            StatusResponse status = new StatusResponse("ok", "Sector saved successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Sector not saved", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Update a sector")
    @RequestMapping(value = "/update", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> updateSector(HttpServletRequest req, 
										  @RequestParam(value = "canal", required = true) String idCanal,
										  @RequestParam(value = "sector", required = true) String idSector, 
										  @RequestBody Sector sector) {
        try {
        	
        	SectorKey id = new SectorKey();
        	id.setCanal(sector.getId().getCanal());
        	id.setSector(sector.getId().getSector());
        	
            Sector stored = sectorService.getById(id);
            
            stored.setActivo(sector.isActivo());
            if (null != sector.getDescripcion()) {
                stored.setDescripcion(sector.getDescripcion());
            }
            if (null != sector.getUsuModif()) {
                stored.setUsuModif(sector.getUsuModif());
            } else {
                stored.setUsuModif("system");
            }
            if (null != sector.getFechaModif()) {
                stored.setFechaModif(sector.getFechaModif());
            } else {
                stored.setFechaModif(new Date());
            }
            sectorService.save(stored);
            StatusResponse status = new StatusResponse("ok", "Sector updated successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Sector not saved", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Delete a sector")
    @RequestMapping(value = "/delete}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> delete(HttpServletRequest req, 
			  						@RequestParam(value = "canal", required = true) String idCanal,
			  						@RequestParam(value = "sector", required = true) String idSector) {
        try {
        	
        	SectorKey id = new SectorKey();
        	id.setCanal(idCanal);
        	id.setSector(idSector);
        	
            sectorService.delete(id);
            StatusResponse status = new StatusResponse("ok", "Sector deleted successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Sector not deleted", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

}
