package ar.com.bbva.got.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class CampoDisponibleDTO implements Serializable {

	private static final long serialVersionUID = -5445678639117753177L;

	@ApiModelProperty(notes = "The database campoDisponible ID", required = true)
    private Integer id;
   
	@ApiModelProperty(notes = "The campoDisponible name", required = true)
    private String nombre;
    
	@ApiModelProperty(notes = "The campoDisponible description")
    private String descripcion;
    
	@ApiModelProperty(notes = "The campoDisponible tipoDato", required = true)
    private String tipoDato;
    
	@ApiModelProperty(notes = "The campoDisponible activo")
    private boolean activo;
    
    @ApiModelProperty(notes = "The campoDisponible obligatorio")
    private boolean obligatorio;

    @ApiModelProperty(notes = "The campoDisponible leyenda")
    private boolean leyenda;

}
