package com.sa.bbva.got.gotApi.controller.funcional;

import java.text.ParseException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.sa.bbva.got.gotApi.bean.StatusResponse;
import com.sa.bbva.got.gotApi.model.EstadoTramite;
import com.sa.bbva.got.gotApi.model.Sector;
import com.sa.bbva.got.gotApi.model.Tramite;
import com.sa.bbva.got.gotApi.model.TramiteAutorizado;
import com.sa.bbva.got.gotApi.model.TramiteAutorizadoKey;
import com.sa.bbva.got.gotApi.model.TramiteDetalle;
import com.sa.bbva.got.gotApi.model.TramiteDetalleKey;
import com.sa.bbva.got.gotApi.service.funcional.TramiteAutorizadoService;
import com.sa.bbva.got.gotApi.service.funcional.TramiteDetalleService;
import com.sa.bbva.got.gotApi.service.funcional.TramiteService;

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
@RequestMapping("/funcional/tramite")
@Api(value = "funcional", description = "Funcional/Tramite Operations in GOT")
public class TramiteController {

    private TramiteService tramiteService;
    private TramiteDetalleService tramiteDetalleService;
    private TramiteAutorizadoService tramiteAutorizadoService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setTramiteService(TramiteService tramiteService) {
        this.tramiteService = tramiteService;
    }

    @Autowired
    public void setTramiteDetalleService(TramiteDetalleService tramiteDetalleService) {
        this.tramiteDetalleService = tramiteDetalleService;
    }

    @Autowired
    public void setTramiteAutorizadoService(TramiteAutorizadoService tramiteAutorizadoService) {
        this.tramiteAutorizadoService = tramiteAutorizadoService;
    }

    /*
     * Tramite
     */
    @ApiOperation(value = "View a list of available tramite", response = Iterable.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> list(HttpServletRequest req, Model model,
            @RequestParam(value = "activo", required = false) boolean activo,
            @RequestParam(value = "sector", required = false) Integer sectorId) throws ParseException {
        try {
            if (null != sectorId && sectorId != 0) {
                Sector sectorActual = new Sector();
                sectorActual.setId(sectorId);
                Iterable<Tramite> tramiteList = tramiteService.listBySectorActual(sectorActual);
                ResponseEntity<?> response = new ResponseEntity<>(tramiteList, HttpStatus.OK);
                return response;
            }
            if (!activo) {
                Iterable<Tramite> tramiteList = tramiteService.listAll();
                ResponseEntity<?> response = new ResponseEntity<>(tramiteList, HttpStatus.OK);
                return response;
            }
            EstadoTramite estado = new EstadoTramite();
            estado.setId(1);
            Iterable<Tramite> tramiteList = tramiteService.listByEstado(estado);
            ResponseEntity<?> response = new ResponseEntity<>(tramiteList, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Add a tramite")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveTramite(@RequestBody Tramite tramite) {
        try {
            tramite.setId(0);
            tramiteService.save(tramite);
            StatusResponse status = new StatusResponse("ok", "Tramite saved successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Tramite not saved", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Update a tramite")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> updateTramite(@PathVariable Integer id, @RequestBody Tramite tramite) {
        try {
            Tramite stored = tramiteService.getById(id);
            if (null != tramite.getTipoTramite()) {
                stored.setTipoTramite(tramite.getTipoTramite());
            }
            if (null != tramite.getClienteId()) {
                stored.setClienteId(tramite.getClienteId());
            }
            if (null != tramite.getSectorInicio()) {
                stored.setSectorInicio(tramite.getSectorInicio());
            }
            if (null != tramite.getSectorActual()) {
                stored.setSectorActual(tramite.getSectorActual());
            }
            if (null != tramite.getDetalle()) {
                stored.setDetalle(tramite.getDetalle());
            }
            if (null != tramite.getCuentaCobro()) {
                stored.setCuentaCobro(tramite.getCuentaCobro());
            }
            if (null != tramite.getEstado()) {
                stored.setEstado(tramite.getEstado());
            }
            if (null != tramite.getFechaFinalizacion()) {
                stored.setFechaFinalizacion(tramite.getFechaFinalizacion());
            }
            if (null != tramite.getFechaInicio()) {
                stored.setFechaInicio(tramite.getFechaInicio());
            }
            if (null != tramite.getFechaVencimiento()) {
                stored.setFechaVencimiento(tramite.getFechaVencimiento());
            }
            if (null != tramite.getUsuModif()) {
                stored.setUsuModif(tramite.getUsuModif());
            } else {
                stored.setUsuModif("system");
            }
            if (null != tramite.getFechaModif()) {
                stored.setFechaModif(tramite.getFechaModif());
            } else {
                stored.setFechaModif(new Date());
            }
            tramiteService.save(stored);
            StatusResponse status = new StatusResponse("ok", "Tramite updated successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Tramite not saved", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Search a tramite with an ID", response = Tramite.class)
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> showTramite(@PathVariable Integer id, Model model) {
        try {
            Tramite tramite = tramiteService.getById(id);
            ResponseEntity<?> response = new ResponseEntity<>(tramite, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Delete a tramite")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            tramiteService.delete(id);
            StatusResponse status = new StatusResponse("ok", "Tramite deleted successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Tramite not deleted", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    /*
     * Tramite-Detalle
     */
    @ApiOperation(value = "View a list of available tramiteDetalle", response = Iterable.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "/detalle/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listAllTramiteDetalle(Model model) {
        try {
            Iterable<TramiteDetalle> tramiteDetalleList = tramiteDetalleService.listAll();
            ResponseEntity<?> response = new ResponseEntity<>(tramiteDetalleList, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Search a tramiteDetalle with a Tramite ID", response = TramiteDetalle.class)
    @RequestMapping(value = "/detalle/list/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listTramiteDetalle(@PathVariable Integer id, Model model) {
        try {
            Tramite tramite = tramiteService.getById(id);
            if (null == tramite || null == tramite.getDetalle()) {
                ResponseEntity<?> response = new ResponseEntity<>(null, HttpStatus.OK);
                return response;
            }
            Iterable<TramiteDetalle> tramiteDetalle = tramite.getDetalle();
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
    @RequestMapping(value = "/detalle/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveTramiteDetalle(@RequestBody TramiteDetalle tramiteDetalle) {
        try {
            tramiteDetalleService.save(tramiteDetalle);
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
    @RequestMapping(value = "/detalle/update/{tramiteId}/{tipoTramiteCampoId}/{campoDisponibleId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> updateTramiteDetalle(@PathVariable Integer tramiteId,
            @PathVariable Integer tipoTramiteCampoId, @PathVariable Integer campoDisponibleId,
            @RequestBody TramiteDetalle tramiteDetalle) {
        try {
            TramiteDetalle stored = this.tramiteDetalleService
                    .getById(new TramiteDetalleKey(tramiteId, tipoTramiteCampoId, campoDisponibleId));
            if (null != tramiteDetalle.getValor()) {
                stored.setValor(tramiteDetalle.getValor());
            }
            if (null != tramiteDetalle.getUsuModif()) {
                stored.setUsuModif(tramiteDetalle.getUsuModif());
            } else {
                stored.setUsuModif("system");
            }
            if (null != tramiteDetalle.getFechaModif()) {
                stored.setFechaModif(tramiteDetalle.getFechaModif());
            } else {
                stored.setFechaModif(new Date());
            }
            tramiteDetalleService.save(stored);
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

    @ApiOperation(value = "Search a tramiteDetalle with an ID")
    @RequestMapping(value = "/detalle/show/{tramiteId}/{tipoTramiteCampoId}/{campoDisponibleId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> showTramiteDetalle(@PathVariable Integer tramiteId,
            @PathVariable Integer tipoTramiteCampoId, @PathVariable Integer campoDisponibleId, Model model) {
        try {
            TramiteDetalle tramiteDetalleSaved = this.tramiteDetalleService
                    .getById(new TramiteDetalleKey(tramiteId, tipoTramiteCampoId, campoDisponibleId));
            ResponseEntity<?> response = new ResponseEntity<>(tramiteDetalleSaved, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "TramiteDetalle not shown", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Delete a tramiteDetalle")
    @RequestMapping(value = "/detalle/delete/{tramiteId}/{tipoTramiteCampoId}/{campoDisponibleId}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> deleteTramiteDetalle(@PathVariable Integer tramiteId,
            @PathVariable Integer tipoTramiteCampoId, @PathVariable Integer campoDisponibleId, Model model) {
        try {
            tramiteDetalleService.delete(new TramiteDetalleKey(tramiteId, tipoTramiteCampoId, campoDisponibleId));
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

    /*
     * Tramite-Autorizado
     */
    @ApiOperation(value = "Search a tramiteAutorizado with a Tramite ID", response = TramiteDetalle.class)
    @RequestMapping(value = "/autorizado/list/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listTramiteAutorizado(@PathVariable Integer id, Model model) {
        try {
            Tramite tramite = tramiteService.getById(id);
            if (null == tramite || null == tramite.getDetalle()) {
                ResponseEntity<?> response = new ResponseEntity<>(null, HttpStatus.OK);
                return response;
            }
            Iterable<TramiteAutorizado> autorizado = tramite.getAutorizado();
            ResponseEntity<?> response = new ResponseEntity<>(autorizado, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Add a tramiteAutorizado")
    @RequestMapping(value = "/autorizado/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveTramiteAutorizado(@RequestBody TramiteAutorizado tramiteAutorizado) {
        try {
            tramiteAutorizadoService.save(tramiteAutorizado);
            StatusResponse status = new StatusResponse("ok", "TramiteAutorizado saved successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "TramiteAutorizado not saved", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Delete a tramiteAutorizado")
    @RequestMapping(value = "/autorizado/delete/{tramiteId}/{autorizadoId}}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> deleteTramiteAutorizado(@PathVariable Integer tramiteId,
            @PathVariable Integer autorizadoId, Model model) {
        try {
            tramiteAutorizadoService.delete(new TramiteAutorizadoKey(tramiteId, autorizadoId));
            StatusResponse status = new StatusResponse("ok", "TramiteAutorizado deleted successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "TramiteAutorizado not deleted",
                    e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

}
