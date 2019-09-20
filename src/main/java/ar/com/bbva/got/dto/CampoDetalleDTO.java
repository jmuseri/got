package ar.com.bbva.got.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CampoDetalleDTO implements Serializable {

	private static final long serialVersionUID = -5445678639117753177L;

	@ApiModelProperty(notes = "The nombre")
    private String nombre;
	
	@ApiModelProperty(notes = "The valor")
    private String valor;
   
	
	@ApiModelProperty(notes = "The Descripcion")
    private String descripcion;
	
}
