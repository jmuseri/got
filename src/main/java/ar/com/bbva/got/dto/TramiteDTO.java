package ar.com.bbva.got.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

import ar.com.bbva.got.model.EstadoTramite;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class TramiteDTO implements Serializable{

	private static final long serialVersionUID = -956041686816430819L;
	
	@ApiModelProperty(notes = "The database generated autorizado ID")
    private Integer id;
	
	@ApiModelProperty(notes = "The tramite tipoTramite")
    private Integer idTipoTramite;

    @ApiModelProperty(notes = "The tramite clienteId")
    private Integer nroClienteEmpresa;
    
    @ApiModelProperty(notes = "The tramite cuitEmpresa")
    private String cuitEmpresa;

    @ApiModelProperty(notes = "The autorizados")
    private List<AutorizadoDTO> autorizado;

    @ApiModelProperty(notes = "The tramite sectorInicio")
    private SectorDTO sectorInicio;

    @ApiModelProperty(notes = "The tramite sectorActual")
    private SectorDTO sectorActual;

    @ApiModelProperty(notes = "The tramite detalle")
    private List<CampoDetalleDTO> detalle;

    @ApiModelProperty(notes = "The tramite cuentaCobro")
    private String cuentaCobro;

    @ApiModelProperty(notes = "The tramite estado")
    private EstadoTramite estado;

    @ApiModelProperty(notes = "The finalization date", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date fechaFinalizacion;

    @ApiModelProperty(notes = "The init date", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date fechaInicio;

    @ApiModelProperty(notes = "The expiry date", required = true)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private Date fechaVencimiento;

}
