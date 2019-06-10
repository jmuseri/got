package com.sa.bbva.got.controller.parametria;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import com.sa.bbva.got.bean.StatusResponse;
import com.sa.bbva.got.model.TipoTramiteCampo;
import com.sa.bbva.got.service.parametria.TipoTramiteCampoService;
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
@RequestMapping("/parametria/tipoTramiteCampo")
@Api(value = "parametria", description = "Parametria/TipoTramiteCampo Operations in GOT")
public class TipoTramiteCampoController {

    private TipoTramiteCampoService tipoTramiteCampoService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setTipoTramiteService(TipoTramiteCampoService tipoTramiteCampoService) {
        this.tipoTramiteCampoService = tipoTramiteCampoService;
    }

    @ApiOperation(value = "View a list of available tipoTramiteCampo", response = Iterable.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")

    public ResponseEntity<?> list(HttpServletRequest req,
            @RequestParam(value = "activo", required = false) boolean activo) throws ParseException {
        try {
            if (!activo) {
                Iterable<TipoTramiteCampo> tipoTramiteList = tipoTramiteCampoService.listAll();
                ResponseEntity<?> response = new ResponseEntity<>(tipoTramiteList, HttpStatus.OK);
                return response;
            } else {
                Iterable<TipoTramiteCampo> tipoTramiteList = tipoTramiteCampoService.listActive();
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

    @ApiOperation(value = "Search a tipoTramiteCampo with an ID", response = TipoTramiteCampo.class)
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> showTipoTramite(@PathVariable Integer id, Model model) {
        try {
            TipoTramiteCampo tipoTramiteCampo = tipoTramiteCampoService.getById(id);
            ResponseEntity<?> response = new ResponseEntity<>(tipoTramiteCampo, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Add a tipoTramiteCampo")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveTipoTramite(@RequestBody TipoTramiteCampo tipoTramiteCampo) {
        try {
            tipoTramiteCampoService.save(tipoTramiteCampo);
            StatusResponse status = new StatusResponse("ok", "TipoTramiteCampo saved successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "TipoTramiteCampo not saved", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Update a tipoTramiteCampo")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> updateTipoTramite(@PathVariable Integer id, @RequestBody TipoTramiteCampo tipoTramiteCampo) {
        try {
            TipoTramiteCampo stored = tipoTramiteCampoService.getById(id);
            if (null != tipoTramiteCampo.getTipoTramite()) {
                stored.setTipoTramite(tipoTramiteCampo.getTipoTramite());
            }
            if (null != tipoTramiteCampo.getCampoDisponible()) {
                stored.setCampoDisponible(tipoTramiteCampo.getCampoDisponible());
            }
            
            stored.setObligatorio(tipoTramiteCampo.isObligatorio());
            stored.setActivo(tipoTramiteCampo.isActivo());

            if (null != tipoTramiteCampo.getNombre()) {
                stored.setNombre(tipoTramiteCampo.getNombre());
            }
            if (null != tipoTramiteCampo.getLeyenda()) {
                stored.setLeyenda(tipoTramiteCampo.getLeyenda());
            }
            if (null != tipoTramiteCampo.getUsuAlta()) {
                stored.setUsuAlta(tipoTramiteCampo.getUsuAlta());
            }
            if (null != tipoTramiteCampo.getFechaAlta()) {
                stored.setFechaAlta(tipoTramiteCampo.getFechaAlta());
            }
            if (null != tipoTramiteCampo.getUsuModif()) {
                stored.setUsuModif(tipoTramiteCampo.getUsuModif());
            }
            if (null != tipoTramiteCampo.getFechaModif()) {
                stored.setFechaModif(tipoTramiteCampo.getFechaModif());
            }
            tipoTramiteCampoService.save(stored);
            StatusResponse status = new StatusResponse("ok", "TipoTramiteCampo updated successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "TipoTramiteCampo not saved", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Delete a tipoTramiteCampo")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            tipoTramiteCampoService.delete(id);
            StatusResponse status = new StatusResponse("ok", "TipoTramiteCampo deleted successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "TipoTramiteCampo not deleted", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

}
