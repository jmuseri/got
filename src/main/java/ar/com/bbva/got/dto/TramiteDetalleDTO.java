package ar.com.bbva.got.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TramiteDetalleDTO implements Serializable {

	private static final long serialVersionUID = -5445678639117753177L;

	
	@ApiModelProperty(notes = "The tipoTramite Id")
    private Integer idTipoTramite;
	
	
	@ApiModelProperty(notes = "The Tramite ID")
    private Integer tramiteId;
	
	
	@ApiModelProperty(notes = "The CampoDisponible ID")
	private String campoDisponibleId;
	
	
	@ApiModelProperty(notes = "The valor")
    private String valor;
	
    @ApiModelProperty(notes = "The creator user")
    private String usuAlta;

    @ApiModelProperty(notes = "The creation date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date fechaAlta;

    @ApiModelProperty(notes = "The update user")
    private String usuModif;

    @ApiModelProperty(notes = "The update date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date fechaModif;
	
}
