package ar.com.bbva.got.controller.funcional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ar.com.bbva.got.bean.StatusResponse;
import ar.com.bbva.got.dto.AutorizadoDTO;
import ar.com.bbva.got.mappers.AutorizadoMapper;
import ar.com.bbva.got.model.Autorizado;
import ar.com.bbva.got.service.funcional.AutorizadoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/funcional/autorizado")
@Api(value = "funcional", description = "Funcional/Autorizado Operations in GOT")
public class AutorizadoController {

    private AutorizadoService autorizadoService;

    @Autowired
    public void setAutorizadoService(AutorizadoService autorizadoService) {
        this.autorizadoService = autorizadoService;
    }
    
    @ApiOperation(value = "View a list of available Autorizados", response = Iterable.class)
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Successfully retrieved list"),
            @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
            @ApiResponse(code = 403, message = ""
            		+ "Accessing the resource you were trying to reach is forbidden"),
            @ApiResponse(code = 404, message = "The resource you were trying to reach is not found") })
    @RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> listAutorizados(HttpServletRequest req,
            @RequestParam(value = "nroClienteEmpresa", required = false) Integer nroClienteEmpresa,
            @RequestParam(value = "cuitEmpresa", required = false) String cuitEmpresa) {
        
		List<AutorizadoDTO> responseList = new ArrayList<AutorizadoDTO>();
        
		Iterable<Autorizado> listAutorizados = autorizadoService.listByNroClienteEmpresaOrCuitEmpresa(nroClienteEmpresa, cuitEmpresa);
		
		for (Autorizado autorizado : listAutorizados) {
			AutorizadoDTO response = AutorizadoMapper.modelToDTO(autorizado);
			responseList.add(response);
		}
		
        ResponseEntity<?> response = new ResponseEntity<>(responseList, HttpStatus.OK);
        return response;
    }
    
    @ApiOperation(value = "Add autorizados")
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> addAutorizados(@RequestBody List<AutorizadoDTO> listAutorizadoDTO,
    										@RequestParam(value = "usuario", required = false) String usuario) {

    	for (AutorizadoDTO autorizadoDTO : listAutorizadoDTO) {
    		Autorizado autorizado = AutorizadoMapper.DTOtoModel(autorizadoDTO);
    		autorizado.setActivo(true);
    		autorizado.setUsuAlta(usuario);
    		autorizado.setUsuModif(usuario);
    		autorizado.setFechaAlta(new Date());
    		autorizado.setFechaModif(new Date());
        	autorizadoService.save(autorizado);
		}
    	
        StatusResponse status = new StatusResponse("ok", "Alta de autorizado realizada", null);
        ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
        return response;
    }
    
    @ApiOperation(value = "Delete an autorizado")
    @RequestMapping(value = "/{autorizadoId}/delete/", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity<?> deleteAutorizado(@PathVariable Integer autorizadoId) {
    	
    	Autorizado autorizado = autorizadoService.getById(autorizadoId);
    	StatusResponse status =null;
    	
    	if (null != autorizado) {
    		autorizado.setActivo(false);
    		autorizadoService.save(autorizado);
    		status = new StatusResponse("ok", "Autorizado deleted successfully", null);
    	} else {
    		status = new StatusResponse("error", "Autorizado not deleted", "Autorizado no encontrado.");
    	}
        
        ResponseEntity<?> response = new ResponseEntity<>(status, HttpStatus.OK);
        return response;
    }
    
    @ApiOperation(value = "Search an autorizado  by document", response = Autorizado.class)
    @RequestMapping(value = "/show/{tipoDocumento}/{numeroDocumento}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> showAutorizado(@PathVariable String tipoDocumento, @PathVariable String numeroDocumento, Model model) {
    	
        Autorizado autorizado = autorizadoService.getByTipoAndNroDocumento(tipoDocumento, numeroDocumento);
        ResponseEntity<?> response = new ResponseEntity<>(autorizado, HttpStatus.OK);
        return response;
    }
}
