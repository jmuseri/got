package ar.com.bbva.got.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import ar.com.bbva.got.model.SectorKey;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AltaTramiteDTO implements Serializable {
	 
	private static final long serialVersionUID = 2602223305984673722L;

	private Integer idTipoTramite;

    private Integer nroClienteEmpresa;

    private List<Integer> idAutorizados;

    private SectorKey sectorAlta;

    private List<CampoDetalleDTO> detalle;
    private String cuentaCobro;
    
    private String UsuarioAlta;

//    private EstadoTramite estado;
//    private Date fechaFinalizacion;
//    private Date fechaInicio;
//    private Date fechaVencimiento;

}
