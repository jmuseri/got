package com.sa.bbva.got.controller.parametria;

import com.sa.bbva.got.bean.StatusResponse;
import com.sa.bbva.got.model.EstadoTramite;
import com.sa.bbva.got.service.parametria.EstadoTramiteService;
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
@RequestMapping("/estadoTramite")
@Api(value = "parametria", description = "Parametria/EstadoTramite Operations in GOT")
public class EstadoTramiteController {

    private EstadoTramiteService estadoTramiteService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setEstadoTramiteService(EstadoTramiteService estadoTramiteService) {
        this.estadoTramiteService = estadoTramiteService;
    }

    @ApiOperation(value = "View a list of available estadoTramite", response = Iterable.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> list(Model model) {
        try {
            Iterable<EstadoTramite> list = estadoTramiteService.listAll();
            ResponseEntity<?> response = new ResponseEntity<>(list, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Search an estadoTramite with an ID", response = EstadoTramite.class)
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> showEstadoTramite(@PathVariable Integer id, Model model) {
        try {
            EstadoTramite estadoTramite = estadoTramiteService.getById(id);
            ResponseEntity<?> response = new ResponseEntity<>(estadoTramite, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Add an estadoTramite")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveEstadoTramite(@RequestBody EstadoTramite estadoTramite) {
        try {
            estadoTramiteService.save(estadoTramite);
            StatusResponse status = new StatusResponse("ok", "EstadoTramite saved successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "EstadoTramite not saved", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Update an estadoTramite")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> updateEstadoTramite(@PathVariable Integer id, @RequestBody EstadoTramite estadoTramite) {
        try {
            EstadoTramite stored = estadoTramiteService.getById(id);
            if (null != estadoTramite.getNombre()) {
                stored.setNombre(estadoTramite.getNombre());
            }
            if (null != estadoTramite.getDescripcion()) {
                stored.setDescripcion(estadoTramite.getDescripcion());
            }
            if (null != estadoTramite.getUsuAlta()) {
                stored.setUsuAlta(estadoTramite.getUsuAlta());
            }
            if (null != estadoTramite.getFechaAlta()) {
                stored.setFechaAlta(estadoTramite.getFechaAlta());
            }
            if (null != estadoTramite.getUsuModif()) {
                stored.setUsuModif(estadoTramite.getUsuModif());
            }
            if (null != estadoTramite.getFechaModif()) {
                stored.setFechaModif(estadoTramite.getFechaModif());
            }
            estadoTramiteService.save(stored);
            StatusResponse status = new StatusResponse("ok", "EstadoTramite updated successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "EstadoTramite not saved", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Delete an estadoTramite")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            estadoTramiteService.delete(id);
            StatusResponse status = new StatusResponse("ok", "EstadoTramite deleted successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "EstadoTramite not deleted", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

}
