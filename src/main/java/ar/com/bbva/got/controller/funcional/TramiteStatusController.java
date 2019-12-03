package ar.com.bbva.got.controller.funcional;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.bbva.got.bean.StatusResponse;
import ar.com.bbva.got.model.EstadoTramite;
import ar.com.bbva.got.model.MotivoRechazo;
import ar.com.bbva.got.model.Tramite;
import ar.com.bbva.got.model.TramiteAutorizado;
import ar.com.bbva.got.service.funcional.MotivoRechazoService;
import ar.com.bbva.got.service.funcional.TramiteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/funcional/tramite")
@Api(value = "funcional", description = "funcional Operations in GOT")
public class TramiteStatusController {


	
	@Autowired
    private TramiteService tramiteService;
	
	
	@Autowired
	private MotivoRechazoService motivoRechazoService; 
	
    //Comentario commit lea!!!
    private final Logger logger = LoggerFactory.getLogger(this.getClass());



      
    @ApiOperation(value = "Gestionar tramite")
    @RequestMapping(value = "/{id}/gestionar", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> gestionarTramite(@PathVariable Integer id,
    										   @RequestParam(value = "usuario", required = false) String usuario) {
        try {
        	       	
        	Tramite tramite = tramiteService.getById(id);
        	tramite.setEstado(EstadoTramite.GESTION);
        	tramite.setUsuModif(usuario);
        	tramite.setFechaModif(new Date());
        	tramiteService.save(tramite);
        	
          	
            StatusResponse status = new StatusResponse("ok", "Tramite en estado Gestionar", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
            
            
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Tramite no actualizado", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }
    
    @ApiOperation(value = "Finalizar tramite")
    @RequestMapping(value = "/{id}/finalizar", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> finalizarTramite(@PathVariable Integer id,
    										   @RequestParam(value = "usuario", required = false) String usuario,
    										   @RequestParam(value = "idAutorizado", required = false) Integer idAutorizado) {
        try {
        	       	
        	Tramite tramite = tramiteService.getById(id);
        	
        	tramite.setEstado(EstadoTramite.FINALIZADO);
        	tramite.setUsuModif(usuario);
        	tramite.setFechaModif(new Date());
        	
          	
        	if (idAutorizado!=null) {
        		Set<TramiteAutorizado> autorizados = tramite.getAutorizado();
        		Optional<TramiteAutorizado> autorizadoFinalizador = autorizados.stream().
        			    filter(p -> p.getId().getAutorizadoId().equals(idAutorizado)).findFirst();
        		
        		TramiteAutorizado trAut= autorizadoFinalizador.get();
        		trAut.setFinalizoTramite(true);
        		
        	}

        	tramiteService.save(tramite);
        	
            StatusResponse status = new StatusResponse("ok", "Tramite en estado Finalizar", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
            
            
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Tramite no actualizado", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }
    
    
    
    @ApiOperation(value = "Cancelar tramite")
    @RequestMapping(value = "/{id}/cancelar", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> cancelarTramite(@PathVariable Integer id,
    										   @RequestParam(value = "usuario", required = false) String usuario) {
        try {
        	       	
        	Tramite tramite = tramiteService.getById(id);
        	tramite.setEstado(EstadoTramite.CANCELADO);
        	tramite.setUsuModif(usuario);
        	tramite.setFechaModif(new Date());
        	tramiteService.save(tramite);
        	
          	
            StatusResponse status = new StatusResponse("ok", "Tramite en estado Finalizar", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
            
            
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Tramite no actualizado", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }
    
    
    @ApiOperation(value = "Rechazar tramite")
    @RequestMapping(value = "/{id}/rechazar", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> rechazarTramite(@PathVariable Integer id,
    										 @RequestParam(value = "motivoRechazoId", required = false) Integer motivoRechazoId,
    										  @RequestParam(value = "usuario", required = false) String usuario) {
        try {
        	       	
        	Tramite tramite = tramiteService.getById(id);
        	tramite.setEstado(EstadoTramite.RECHAZADO);

        	MotivoRechazo motivoRechazo= null; 
        	if (null!=motivoRechazoId) 
        		motivoRechazoService.getById(motivoRechazoId);

        	tramite.setMotivoRechazo(motivoRechazo);
        	tramite.setUsuModif(usuario);
        	tramite.setFechaModif(new Date());
        	tramiteService.save(tramite);
        	
            StatusResponse status = new StatusResponse("ok", "Tramite en estado Rechazar", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
            
            
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Tramite no actualizado", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }
    
    
    
    @ApiOperation(value = "Activar tramite")
    @RequestMapping(value = "/{id}/activar", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> activarTramite(@PathVariable Integer id,
    										  @RequestParam(value = "usuario", required = false) String usuario) {
        try {
        	       	
        	Tramite tramite = tramiteService.getById(id);
        	tramite.setEstado(EstadoTramite.ACTIVO);
        	tramite.setUsuModif(usuario);
        	tramite.setFechaModif(new Date());
        	tramiteService.save(tramite);
        	
            StatusResponse status = new StatusResponse("ok", "Tramite en estado Activo", null);
            ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
            return response;
            
            
        } catch (Exception e) {
            logger.error("", e);
            StatusResponse statusResponse = new StatusResponse("error", "Tramite no actualizado", e.getMessage());
            ResponseEntity<?> response = new ResponseEntity<>(statusResponse, HttpStatus.INTERNAL_SERVER_ERROR);
            return response;
        }
    }
    
    
    
    @ApiOperation(value = "Eliminar tramite")
    @RequestMapping(value = "/{id}/eliminar", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> deleteTramite(@PathVariable Integer id) {
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
    
    
    
      
    

}
