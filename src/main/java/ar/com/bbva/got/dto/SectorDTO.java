package ar.com.bbva.got.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class SectorDTO implements Serializable {

	private static final long serialVersionUID = -5445678639117753177L;

	@ApiModelProperty(notes = "The sector canal")
    private String canal;
	
	@ApiModelProperty(notes = "The sector sector")
    private String sector;
	
	@ApiModelProperty(notes = "The sector description")
    private String descripcion;
	
	@ApiModelProperty(notes = "The sector activo")
    private boolean activo;
    
}
