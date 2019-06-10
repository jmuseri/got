package com.sa.bbva.got.controller.parametria;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import com.sa.bbva.got.bean.StatusResponse;
import com.sa.bbva.got.model.TipoTramite;
import com.sa.bbva.got.service.parametria.TipoTramiteService;
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
    public ResponseEntity<?> list(HttpServletRequest req,
            @RequestParam(value = "activo", required = false) boolean activo) throws ParseException {
        try {
            if (!activo) {
                Iterable<TipoTramite> tipoTramiteList = tipoTramiteService.listAll();
                ResponseEntity<?> response = new ResponseEntity<>(tipoTramiteList, HttpStatus.OK);
                return response;
            } else {
                Iterable<TipoTramite> tipoTramiteList = tipoTramiteService.listActive();
                ResponseEntity<?> response = new ResponseEntity<>(tipoTramiteList, HttpStatus.OK);
                return response;
            }
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Search a tipoTramite with an ID", response = TipoTramite.class)
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> showTipoTramite(@PathVariable Integer id, Model model) {
        try {
            TipoTramite tipoTramite = tipoTramiteService.getById(id);
            ResponseEntity<?> response = new ResponseEntity<>(tipoTramite, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Add a tipoTramite")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveTipoTramite(@RequestBody TipoTramite tipoTramite) {
        try {
            tipoTramiteService.save(tipoTramite);
            StatusResponse status = new StatusResponse("ok", "TipoTramite saved successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "TipoTramite not saved", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Update a tipoTramite")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> updateTipoTramite(@PathVariable Integer id, @RequestBody TipoTramite tipoTramite) {
        try {
            TipoTramite stored = tipoTramiteService.getById(id);
            if (null != tipoTramite.getDescripcion()) {
                stored.setDescripcion(tipoTramite.getDescripcion());
            }
            if (null != tipoTramite.getComision()) {
                stored.setComision(tipoTramite.getComision());
            }
            
            stored.setCobraComision(tipoTramite.isCobraComision());
            stored.setRequiereDocumentacion(tipoTramite.isRequiereDocumentacion());
            stored.setActivo(tipoTramite.isActivo());
            stored.setAutorizado(tipoTramite.isAutorizado());

            if (null != tipoTramite.getSectorInicial()) {
                stored.setSectorInicial(tipoTramite.getSectorInicial());
            }

            if (null != tipoTramite.getHorasResolucion()) {
                stored.setHorasResolucion(tipoTramite.getHorasResolucion());
            }
            if (null != tipoTramite.getHorasVencimiento()) {
                stored.setHorasVencimiento(tipoTramite.getHorasVencimiento());
            }
            if (null != tipoTramite.getUsuAlta()) {
                stored.setUsuAlta(tipoTramite.getUsuAlta());
            }
            if (null != tipoTramite.getFechaAlta()) {
                stored.setFechaAlta(tipoTramite.getFechaAlta());
            }
            if (null != tipoTramite.getUsuModif()) {
                stored.setUsuModif(tipoTramite.getUsuModif());
            }
            if (null != tipoTramite.getFechaModif()) {
                stored.setFechaModif(tipoTramite.getFechaModif());
            }
            tipoTramiteService.save(stored);
            StatusResponse status = new StatusResponse("ok", "TipoTramite updated successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "TipoTramite not saved", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Delete a tipoTramite")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            tipoTramiteService.delete(id);
            StatusResponse status = new StatusResponse("ok", "TipoTramite deleted successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "TipoTramite not deleted", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Search a comision-tipoTramite with an ID", response = TipoTramite.class)
    @RequestMapping(value = "/comision/show/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> showTComisionipoTramite(@PathVariable Integer id, Model model) {
        try {
            TipoTramite tipoTramite = tipoTramiteService.getById(id);
            ResponseEntity<?> response = new ResponseEntity<>(tipoTramite.comision, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Add a comision to tipoTramite")
    @RequestMapping(value = "/comision/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveTipoTramite(@RequestBody TipoTramite tipoTramite) {
        try {
            if (!tipoTramite.comision || null == tipoTramite.comision) {
                StatusResponse status = new StatusResponse("error", "Comision void", null);
                ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
                return response;
            }
            TipoTramite savedTipoTramite = tipoTramiteService.getById(tipoTramite.id);
            if (!savedTipoTramite) {
                StatusResponse status = new StatusResponse("error", "TipoTramite doesn't exist", null);
                ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
                return response;
            }
            if (savedTipoTramite.comision && null != savedTipoTramite.comision) {
                StatusResponse status = new StatusResponse("error", "Comision in TipoTramite already exists", null);
                ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
                return response;
            }
            Comision comision = comisionService.getById(tipoTramite.comision.id);
            if (!comision || null == comision) {
                StatusResponse status = new StatusResponse("error", "Comision doesn't exist", null);
                ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
                return response;
            }
            savedTipoTramite.comision = comision;
            tipoTramiteService.save(savedTipoTramite);
            StatusResponse status = new StatusResponse("ok", "Comision to TipoTramite added successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Comision to TipoTramite not added", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Delete a comision in tipoTramite")
    @RequestMapping(value = "/comision/delete/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> deleteComision(@PathVariable Integer id) {
        try {
            TipoTramite savedTipoTramite = tipoTramiteService.getById(tipoTramite.id);
            if (!savedTipoTramite) {
                StatusResponse status = new StatusResponse("error", "TipoTramite doesn't exist", null);
                ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
                return response;
            }
            if (|savedTipoTramite.comision || null != savedTipoTramite.comision) {
                StatusResponse status = new StatusResponse("error", "Comision in TipoTramite doesn't exist", null);
                ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
                return response;
            }           
            savedTipoTramite.comision = null;
            tipoTramiteService.save(saveTipoTramite);
            StatusResponse status = new StatusResponse("ok", "Comision in TipoTramite deleted successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "TipoTramite not deleted", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

}
