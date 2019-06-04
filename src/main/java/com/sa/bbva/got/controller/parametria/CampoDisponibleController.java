package com.sa.bbva.got.controller.parametria;

import com.sa.bbva.got.bean.StatusResponse;
import com.sa.bbva.got.model.CampoDisponible;
import com.sa.bbva.got.service.parametria.CampoDisponibleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/campoDisponible")
@Api(value = "parametria", description = "Parametria/CampoDisponible Operations in GOT")
public class CampoDisponibleController {

    private CampoDisponibleService campoDisponibleService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setEstadoTramiteService(CampoDisponibleService campoDisponibleService) {
        this.campoDisponibleService = campoDisponibleService;
    }

    @ApiOperation(value = "View a list of available campoDisponible", response = Iterable.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> list(Model model) {
        try {
            Iterable<CampoDisponible> comisionList = campoDisponibleService.listAll();
            ResponseEntity<?> response = new ResponseEntity<>(comisionList, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Search a campoDisponible with an ID", response = CampoDisponible.class)
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> showEstadoTramite(@PathVariable Integer id, Model model) {
        try {
            CampoDisponible comision = campoDisponibleService.getById(id);
            ResponseEntity<?> response = new ResponseEntity<>(comision, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Add a campoDisponible")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveEstadoTramite(@RequestBody CampoDisponible campoDisponible) {
        try {
            campoDisponibleService.save(campoDisponible);
            StatusResponse status = new StatusResponse("ok", "CampoDisponible saved successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "CampoDisponible not saved", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Update a campoDisponible")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> updateEstadoTramite(@PathVariable Integer id,
            @RequestBody CampoDisponible campoDisponible) {
        try {
            CampoDisponible stored = campoDisponibleService.getById(id);
            if (null != campoDisponible.getNombre()) {
                stored.setNombre(campoDisponible.getNombre());
            }
            if (null != campoDisponible.getDescripcion()) {
                stored.setDescripcion(campoDisponible.getDescripcion());
            }
            if (null != campoDisponible.getTipoDato()) {
                stored.setTipoDato(campoDisponible.getTipoDato());
            }
            stored.setActivo(campoDisponible.isActivo());
            if (null != campoDisponible.getUsuAlta()) {
                stored.setUsuAlta(campoDisponible.getUsuAlta());
            }
            if (null != campoDisponible.getFechaAlta()) {
                stored.setFechaAlta(campoDisponible.getFechaAlta());
            }
            if (null != campoDisponible.getUsuModif()) {
                stored.setUsuModif(campoDisponible.getUsuModif());
            }
            if (null != campoDisponible.getFechaModif()) {
                stored.setFechaModif(campoDisponible.getFechaModif());
            }
            campoDisponibleService.save(stored);
            StatusResponse status = new StatusResponse("ok", "CampoDisponible updated successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "CampoDisponible not saved", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Delete a campoDisponible")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            campoDisponibleService.delete(id);
            StatusResponse status = new StatusResponse("ok", "CampoDisponible deleted successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "CampoDisponible not deleted", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

}
