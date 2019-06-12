package com.sa.bbva.got.controller.funcional;

import com.sa.bbva.got.bean.StatusResponse;
import com.sa.bbva.got.model.TramiteDetalle;
import com.sa.bbva.got.service.funcional.TramiteDetalleService;

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
@RequestMapping("/funcional/tramiteDetalle")
@Api(value = "funcional", description = "Funcional/TramiteDetalle Operations in GOT")
public class TramiteDetalleController {

    private TramiteDetalleService tramiteDetalleController;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setTramiteDetalleService(TramiteDetalleService tramiteDetalleController) {
        this.tramiteDetalleController = tramiteDetalleController;
    }

    @ApiOperation(value = "View a list of available tramiteDetalle", response = Iterable.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> list(Model model) {
        try {
            Iterable<TramiteDetalle> tramiteDetalleList = tramiteDetalleController.listAll();
            ResponseEntity<?> response = new ResponseEntity<>(tramiteDetalleList, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Search a tramiteDetalle with an ID", response = TramiteDetalle.class)
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> showTramiteDetalle(@PathVariable Integer id, Model model) {
        try {
            TramiteDetalle tramiteDetalle = tramiteDetalleController.getById(id);
            ResponseEntity<?> response = new ResponseEntity<>(tramiteDetalle, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Add a tramiteDetalle")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveTramiteDetalle(@RequestBody TramiteDetalle tramiteDetalle) {
        try {
            tramiteDetalleController.save(tramiteDetalle);
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
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> updateTramiteDetalle(@PathVariable Integer id, @RequestBody TramiteDetalle tramiteDetalle) {
        try {
            TramiteDetalle stored = tramiteDetalleController.getById(id);
            // if (null != tramiteDetalle.getTramite()) {
            //     stored.setTramite(tramiteDetalle.getTramite());
            // }
            if (null != tramiteDetalle.getCampo()) {
                stored.setCampo(tramiteDetalle.getCampo());
            }
            if (null != tramiteDetalle.getValor()) {
                stored.setValor(tramiteDetalle.getValor());
            }
            if (null != tramiteDetalle.getUsuAlta()) {
                stored.setUsuAlta(tramiteDetalle.getUsuAlta());
            }
            if (null != tramiteDetalle.getFechaAlta()) {
                stored.setFechaAlta(tramiteDetalle.getFechaAlta());
            }
            if (null != tramiteDetalle.getUsuModif()) {
                stored.setUsuModif(tramiteDetalle.getUsuModif());
            }
            if (null != tramiteDetalle.getFechaModif()) {
                stored.setFechaModif(tramiteDetalle.getFechaModif());
            }
            tramiteDetalleController.save(stored);
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
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            tramiteDetalleController.delete(id);
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
