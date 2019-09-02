package ar.com.bbva.got.dto;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonInclude;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TipoTramiteDTO implements Serializable {

	private static final long serialVersionUID = -5445678639117753177L;
	
	@ApiModelProperty(notes = "The database generated tipoTramite ID")
    private Integer id;

    @ApiModelProperty(notes = "The tipoTramite descripcion")
    private String descripcion;

    @ApiModelProperty(notes = "The tipoTramite cobraComision")
    private boolean cobraComision;

    @ApiModelProperty(notes = "The tipoTramite comision")
    private ComisionDTO comision;

    @ApiModelProperty(notes = "The tipoTramite requiereDocumentacion")
    private boolean requiereDocumentacion;

    @ApiModelProperty(notes = "The tipoTramite activo")
    private boolean activo;

    @ApiModelProperty(notes = "The tipoTramite autorizado")
    private boolean autorizado;

    @ApiModelProperty(notes = "The tramite sectorInicial")
    private SectorDTO sectorInicial;

    @ApiModelProperty(notes = "The CampoDisponible sector")
    private Set<CampoDisponibleDTO> campos;

    @ApiModelProperty(notes = "The tipoTramite horasResolucion")
    private Long horasResolucion;

    @ApiModelProperty(notes = "The tipoTramite horasVencimiento")
    private Long horasVencimiento;
    
}
