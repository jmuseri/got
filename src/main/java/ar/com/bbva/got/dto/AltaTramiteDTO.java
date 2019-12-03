package ar.com.bbva.got.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AltaTramiteDTO implements Serializable {
	 
	private static final long serialVersionUID = 2602223305984673722L;

	private Integer idTipoTramite;

    private Integer nroClienteEmpresa;
    
    private String cuitEmpresa;

    private List<Integer> idAutorizados;
    
    private Integer areaNegocio;

    private SectorDTO sectorAlta;

    private List<CampoDetalleDTO> detalle;
    private String cuentaCobro;
    
    private String UsuarioAlta;

//    private EstadoTramite estado;
//    private Date fechaFinalizacion;
//    private Date fechaInicio;
//    private Date fechaVencimiento;

}
