package ar.com.bbva.got.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AutorizadoDTO implements Serializable {
    
	private static final long serialVersionUID = -956041686816430819L;
	
	@ApiModelProperty(notes = "The database generated autorizado ID")
    private Integer id;

    @ApiModelProperty(notes = "The autorizado nroclienteEmpresa")
    private Integer nroClienteEmpresa;
    
    @ApiModelProperty(notes = "The autorizado cuitEpresa")
    private String cuitEmpresa;

    @ApiModelProperty(notes = "The autorizado tipoDocumento")
    private String tipoDocumento;

    @ApiModelProperty(notes = "The autorizado nroDocumento")
    private String nroDocumento;

    @ApiModelProperty(notes = "The autorizado nombre")
    private String nombre;

    @ApiModelProperty(notes = "The autorizado apellido")
    private String apellido;

    @ApiModelProperty(notes = "The autorizado sexo")
    private String sexo;
    

    @ApiModelProperty(notes = "The autorizado estado")
    private boolean activo;

    
}
