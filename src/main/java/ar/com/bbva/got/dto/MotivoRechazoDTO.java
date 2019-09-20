package ar.com.bbva.got.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class MotivoRechazoDTO implements Serializable{

	private static final long serialVersionUID = -956041686816430819L;
	
	@ApiModelProperty(notes = "The database generated motivoRechazo ID")
    private Integer id;
	
	@ApiModelProperty(notes = "The tramite tipoTramite")
    private Integer idTipoTramite;
	
	@ApiModelProperty(notes = "The motivoRechazo description")
    private String descripcion;


}
