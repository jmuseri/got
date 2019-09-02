package ar.com.bbva.got.response.dto;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AutorizadoDTO {

	private static final long serialVersionUID = -5445678639117753177L;

	private Integer id;
	private String apellido;
	private String nombre;
	private Integer nroClienteEmpresa;
	private String nroDocumento;
	private String tipoDoc;
	
}
