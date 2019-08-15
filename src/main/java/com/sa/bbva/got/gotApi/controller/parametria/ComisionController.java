package com.sa.bbva.got.gotApi.controller.parametria;

import java.util.Date;

import com.sa.bbva.got.gotApi.bean.StatusResponse;
import com.sa.bbva.got.gotApi.model.Comision;
import com.sa.bbva.got.gotApi.service.parametria.ComisionService;

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
@RequestMapping("/parametria/comision")
@Api(value = "parametria", description = "Parametria/Comision Operations in GOT")
public class ComisionController {

    private ComisionService comisionService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setComisionService(ComisionService comisionService) {
        this.comisionService = comisionService;
    }

    @ApiOperation(value = "View a list of available comision", response = Iterable.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> list(Model model) {
        try {
            Iterable<Comision> comisionList = comisionService.listAll();
            ResponseEntity<?> response = new ResponseEntity<>(comisionList, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Search a comision with an ID", response = Comision.class)
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> showComision(@PathVariable Integer id, Model model) {
        try {
            Comision comision = comisionService.getById(id);
            ResponseEntity<?> response = new ResponseEntity<>(comision, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Add a comision")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveComision(@RequestBody Comision comision) {
        try {
            comision.setId(0);
            comisionService.save(comision);
            StatusResponse status = new StatusResponse("ok", "Comision saved successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Comision not saved", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Update a comision")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> updateComision(@PathVariable Integer id, @RequestBody Comision comision) {
        try {
            Comision stored = comisionService.getById(id);
            if (null != comision.getParam1()) {
                stored.setParam1(comision.getParam1());
            }
            if (null != comision.getParam2()) {
                stored.setParam2(comision.getParam2());
            }
            if (null != comision.getParam3()) {
                stored.setParam3(comision.getParam3());
            }
            if (null != comision.getParam4()) {
                stored.setParam4(comision.getParam4());
            }
            if (null != comision.getParam5()) {
                stored.setParam5(comision.getParam5());
            }
            if (null != comision.getUsuModif()) {
                stored.setUsuModif(comision.getUsuModif());
            } else {
                stored.setUsuModif("system");
            }
            if (null != comision.getFechaModif()) {
                stored.setFechaModif(comision.getFechaModif());
            } else {
                stored.setFechaModif(new Date());
            }
            comisionService.save(stored);
            StatusResponse status = new StatusResponse("ok", "Comision updated successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Comision not saved", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Delete a comision")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            comisionService.delete(id);
            StatusResponse status = new StatusResponse("ok", "Comision deleted successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Comision not deleted", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

}
