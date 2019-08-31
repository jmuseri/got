package ar.com.bbva.got.controller.funcional;

import java.util.Date;

import ar.com.bbva.got.bean.StatusResponse;
import ar.com.bbva.got.model.Autorizado;
import ar.com.bbva.got.service.funcional.AutorizadoService;
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
@RequestMapping("/funcional/autorizado")
@Api(value = "funcional", description = "Funcional/Autorizado Operations in GOT")
public class AutorizadoController {

    private AutorizadoService autorizadoService;
    // private TramiteAutorizadoService tramiteAutorizadoService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public void setAutorizadoService(AutorizadoService autorizadoService) {
        this.autorizadoService = autorizadoService;
    }

    /*
     * @Autowired public void setTramiteAutorizadoService(TramiteAutorizadoService
     * tramiteAutorizadoService) { this.tramiteAutorizadoService =
     * tramiteAutorizadoService; }
     */

    @ApiOperation(value = "View a list of available autorizado", response = Iterable.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> list(@RequestParam(value = "cliente", required = false) Integer clienteId, Model model) {
        try {
            Iterable<Autorizado> autorizadoList;
            if (clienteId != null) {
                autorizadoList = autorizadoService.listByNroClienteEmpresa(clienteId);
            } else {
                autorizadoList = autorizadoService.listAll();
            }
            ResponseEntity<?> response = new ResponseEntity<>(autorizadoList, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Add an autorizado")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> saveAutorizado(@RequestBody Autorizado autorizado) {
        try {
            autorizadoService.save(autorizado);
            StatusResponse status = new StatusResponse("ok", "Autorizado saved successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Autorizado not saved", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Update an autorizado")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> updateAutorizado(@PathVariable Integer id, @RequestBody Autorizado autorizado) {
        try {
            if (null == autorizado || null == id) {
                StatusResponse status = new StatusResponse("error", "Error Input data", null);
                ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
                return response;
            }
            Autorizado stored = autorizadoService.getById(id);
            if (null == stored) {
                StatusResponse status = new StatusResponse("error", "Autorizado doesn't exist", null);
                ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
                return response;
            }
            if (null != autorizado.getNroClienteEmpresa()) {
                stored.setNroClienteEmpresa(autorizado.getNroClienteEmpresa());
            }
            if (null != autorizado.getTipoDocumento()) {
                stored.setTipoDocumento(autorizado.getTipoDocumento());
            }
            if (null != autorizado.getNroDocumento()) {
                stored.setNroDocumento(autorizado.getNroDocumento());
            }
            if (null != autorizado.getNombre()) {
                stored.setNombre(autorizado.getNombre());
            }
            if (null != autorizado.getApellido()) {
                stored.setApellido(autorizado.getApellido());
            }
            if (null != autorizado.getUsuModif()) {
                stored.setUsuModif(autorizado.getUsuModif());
            } else {
                stored.setUsuModif("system");
            }
            if (null != autorizado.getFechaModif()) {
                stored.setFechaModif(autorizado.getFechaModif());
            } else {
                stored.setFechaModif(new Date());
            }
            autorizadoService.save(stored);
            StatusResponse status = new StatusResponse("ok", "Autorizado updated successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Autorizado not saved", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Search an autorizado with an ID", response = Autorizado.class)
    @RequestMapping(value = "/show/{id}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> showAutorizado(@PathVariable Integer id, Model model) {
        try {
            Autorizado autorizado = autorizadoService.getById(id);
            ResponseEntity<?> response = new ResponseEntity<>(autorizado, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Exception Error", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

    @ApiOperation(value = "Delete an autorizado")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            // tramiteAutorizadoService.deleteByIdAutorizadoId(id);
            autorizadoService.delete(id);
            StatusResponse status = new StatusResponse("ok", "Autorizado deleted successfully", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Autorizado not deleted", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }

}
